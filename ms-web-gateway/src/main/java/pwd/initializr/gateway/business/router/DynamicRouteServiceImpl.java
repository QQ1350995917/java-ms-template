package pwd.initializr.gateway.business.router;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.gateway.business.router.bo.RouteDefinitionBO;
import pwd.initializr.gateway.persistence.dao.RouterDefinitionDao;
import pwd.initializr.gateway.persistence.dao.RouterVersionDao;
import pwd.initializr.gateway.persistence.entity.RouterDefinitionEntity;
import pwd.initializr.gateway.persistence.entity.RouterVersionEntity;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.business.router@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-23 22:20
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
@Slf4j
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware, ApplicationRunner,
    MessageListener {

  @Value("${gateway.router.in.redis.sync.topic}")
  public String GATEWAY_ROUTES_IN_REDIS_SYNC_TOPIC;
  @Value("${gateway.router.in.redis.locker.name}")
  public String GATEWAY_ROUTES_IN_REDIS_LOCKER_NAME;
  @Value("${gateway.router.in.redis.locked.milliseconds}")
  public Integer GATEWAY_ROUTES_IN_REDIS_LOCKER_MILLISECONDS;
  @Value("${gateway.router.in.redis.version.name}")
  public String GATEWAY_ROUTES_IN_REDIS_VERSION_NAME;
  @Resource
  protected RouteDefinitionLocator routeLocator;
  @Resource
  private RouteDefinitionWriter routeDefinitionWriter;
  @Resource
  private RedisTemplate<String, String> redisTemplate;

  private ApplicationEventPublisher publisher;
  @Resource
  private RouterVersionDao routerVersionDao;
  @Resource
  private RouterDefinitionDao routerDefinitionDao;

  @Resource
  private RedisRouteDefinitionRepository redisRouteDefinitionRepository;
  /**
   * <h2>新增或者替换路由</h2>
   * date 2020-12-23 22:22
   *
   * @param localVersion 路由版本
   * @param definition 路由对象
   * @return reactor.core.publisher.Mono<org.springframework.cloud.gateway.route.RouteDefinition>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public Mono<Long> createOrUpdate(Long localVersion, RouteDefinition definition) {
    Long remoteVersion = getRemoteVersion();
    Long expiredVersion = localVersion + 1;
    boolean editable = false;
    if (remoteVersion < expiredVersion) {
      try {
        if (lock()) {
          // 二次识别本地版本是否大于远端版本
          if (expiredVersion > this.getRemoteVersion()) {
            // 首先写入数据
            // TODO 具体如何写入
            routeDefinitionWriter.save(Mono.just(definition));
            // 其次更新版本
            redisTemplate.opsForValue()
                .set(GATEWAY_ROUTES_IN_REDIS_VERSION_NAME, String.valueOf(expiredVersion));
            RouterDefinitionEntity routerDefinitionEntity = new RouterDefinitionEntity();
            BeanUtils.copyProperties(definition, routerDefinitionEntity);
            upgradeLocalRouterByCreateOrUpdate(expiredVersion, routerDefinitionEntity);
            editable = true;
            // 最后通知其他节点有新版本路由数据产生
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            publish(localVersion);
          }
        }
      } catch (Exception e) {
        log.error(e.getMessage());
      } finally {
        releaseLock();
      }
    }
    return Mono.just(editable ? expiredVersion : localVersion);
  }

  private Long getRemoteVersion() {
    String version = redisTemplate.opsForValue().get(GATEWAY_ROUTES_IN_REDIS_VERSION_NAME);
    if (StringUtils.isBlank(version)) {
      return 0L;
    }
    return Long.parseLong(version);
  }

  private boolean lock() {
    Boolean locked = redisTemplate.opsForValue()
        .setIfAbsent(GATEWAY_ROUTES_IN_REDIS_LOCKER_NAME, "locked",
            GATEWAY_ROUTES_IN_REDIS_LOCKER_MILLISECONDS,
            TimeUnit.MILLISECONDS);
    return locked != null && locked;
  }

  @Transactional(rollbackFor = RuntimeException.class)
  public void upgradeLocalRouterByCreateOrUpdate(Long expiredVersion,
      RouterDefinitionEntity routerDefinitionEntity) {
    this.routerVersionDao.delete();
    this.routerVersionDao.create(new RouterVersionEntity(expiredVersion, new Date().toString()));
    this.routerDefinitionDao.replace(routerDefinitionEntity);
  }

  /**
   * <h2>公布有新版本的数据已经发送到Redis库中</h2>
   * date 2020-12-26 16:59
   *
   * @param serialNumber 版本号
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  private void publish(Long serialNumber) {
    redisTemplate.convertAndSend(GATEWAY_ROUTES_IN_REDIS_SYNC_TOPIC, serialNumber);
  }

  private void releaseLock() {
    redisTemplate.delete(GATEWAY_ROUTES_IN_REDIS_LOCKER_NAME);
  }

  /**
   * <h2>删除路由</h2>
   * date 2020-12-23 22:28
   *
   * @param localVersion 路由版本号
   * @param id 路由ID
   * @return reactor.core.publisher.Mono<java.lang.String>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public Mono<Long> delete(Long localVersion, String id) {
    Long remoteVersion = getRemoteVersion();
    Long expiredVersion = localVersion + 1;
    boolean editable = false;
    if (remoteVersion < expiredVersion) {
      try {
        if (lock()) {
          // 二次识别本地版本是否大于远端版本
          if (expiredVersion > this.getRemoteVersion()) {
            // 首先写入数据
            routeDefinitionWriter.delete(Mono.just(id));

            // 其次更新版本
            redisTemplate.opsForValue()
                .set(GATEWAY_ROUTES_IN_REDIS_VERSION_NAME, String.valueOf(expiredVersion));
            this.upgradeLocalRouterByDelete(expiredVersion, id);
            editable = true;
            // 最后通知其他节点有新版本路由数据产生
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            publish(localVersion);
          }
        }
      } catch (Exception e) {
        log.error(e.getMessage());
      } finally {
        releaseLock();
      }
    }
    return Mono.just(editable ? expiredVersion : localVersion);
  }

  @Transactional(rollbackFor = RuntimeException.class)
  public void upgradeLocalRouterByDelete(Long expiredVersion, String id) {
    this.routerVersionDao.delete();
    this.routerVersionDao.create(new RouterVersionEntity(expiredVersion, new Date().toString()));
    this.routerDefinitionDao.deleteById(id);
  }

  /**
   * <h2>路由列表</h2>
   * date 2020-12-26 17:02
   *
   * @return reactor.core.publisher.Mono<pwd.initializr.gateway.business.router.bo.RouteDefinitionBO>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public Mono<RouteDefinitionBO> list() {
    RouterVersionEntity routerVersionEntity = routerVersionDao.query();
    if (routerVersionEntity == null) {
      return this.routeLocator.getRouteDefinitions()
          .collectList()
          .map(routeDefinitions -> new RouteDefinitionBO(0L, routeDefinitions));
    } else {
      return this.routeLocator.getRouteDefinitions()
          .collectList()
          .map(routeDefinitions -> new RouteDefinitionBO(routerVersionEntity.getVersion(),
              routeDefinitions));
    }
  }

  @Override
  public void onMessage(Message message, byte[] pattern) {
    //消息体
    String body = new String(message.getBody());
    //渠道名称
    String topic = new String(pattern);
    log.info("接收到消息：{}", body);
    log.info("由{}渠道发送而来", topic);
  }

  @Override
  public void run(ApplicationArguments args) {
    routerVersionDao.createTable();
    routerDefinitionDao.createTable();

    // 进程启动后主动同步一次
    RouterVersionEntity localRouterVersionEntity = routerVersionDao.query();
    if (localRouterVersionEntity == null) {
      localRouterVersionEntity = new RouterVersionEntity();
    }
    // 查询中央仓库版本
    Long remoteVersion = this.getRemoteVersion();
    if (remoteVersion > localRouterVersionEntity.getVersion()) {
      // 公共Redis库中有新版本的数据同步到本地
      upgradeLocalRouter(remoteVersion);
    } else if (remoteVersion < localRouterVersionEntity.getVersion()) {
      // 本地库中有新版本数据同步到公共Redis库
      upgradeRemoteRouter(localRouterVersionEntity.getVersion());
    } else {
      // lastVersion == this.routerVersionEntity to do nothing
    }
  }

  private void upgradeLocalRouter(final Long remoteVersion) {
    redisRouteDefinitionRepository
        .getRouteDefinitions()
        .map(this::parse)
        .collectList()
        .doOnNext(remoteRouterVersionEntities -> upgradeLocalRouter(remoteVersion,
            remoteRouterVersionEntities)).subscribe();
  }

  /**
   * <h2>使用本地路由更新远端路由</h2>
   * date 2020-12-26 17:22
   *
   * @param localVersion 期望更新远端仓库的本地路由
   * @return boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  private boolean upgradeRemoteRouter(Long localVersion) {
    boolean result = false;
    try {
      if (lock()) {
        // 二次识别本地版本是否大于远端版本
        if (localVersion > this.getRemoteVersion()) {
          List<RouterDefinitionEntity> routerDefinitionEntities = routerDefinitionDao.queryAll();
          // 首先写入数据
          routerDefinitionEntities.forEach(routeDefinition -> routeDefinitionWriter
              .save(Mono.just(routeDefinition)));
          // 其次更新版本
          redisTemplate.opsForValue()
              .set(GATEWAY_ROUTES_IN_REDIS_VERSION_NAME, String.valueOf(localVersion));
          // 最后记录状态
          result = true;
        }
      }
      // 通知其他节点有新版本路由数据产生
      publish(localVersion);
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      releaseLock();
    }
    return result;
  }

  /**
   * <h2>更新本地数据库中的路由</h2>
   * date 2020-12-26 17:19
   *
   * @param remoteVersion 中央仓库路由版本
   * @param routerDefinitionEntities 中央仓库路由
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @Transactional(rollbackFor = RuntimeException.class)
  public void upgradeLocalRouter(Long remoteVersion,
      List<RouterDefinitionEntity> routerDefinitionEntities) {
    this.routerVersionDao.delete();
    this.routerVersionDao.create(new RouterVersionEntity(remoteVersion, new Date().toString()));

    this.routerDefinitionDao.deleteAll();
    this.routerDefinitionDao.createByBatch(routerDefinitionEntities);
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  private RouterDefinitionEntity parse(RouteDefinition routeDefinition) {
    RouterDefinitionEntity routerDefinitionEntity = new RouterDefinitionEntity();
    BeanUtils.copyProperties(routeDefinition, routerDefinitionEntity);
    return routerDefinitionEntity;
  }


}
