package pwd.initializr.gateway.business.router;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
import pwd.initializr.gateway.persistence.dao.RouterDao;
import pwd.initializr.gateway.persistence.entity.RouterEntity;
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
    private RouterDao routerDao;

    @Override
    public void run(ApplicationArguments args) {
        routerDao.createRouterTable();
        RouterEntity routerEntity = routerDao.query();
        if (routerEntity == null) {
            routerEntity = new RouterEntity();
        }

        Long lastVersion = this.getVersionInRedis();
        if (lastVersion > routerEntity.getSerialNumber()) {
            // 公共Redis库中有新版本的数据同步到本地
            upgradeLocalRouter(lastVersion);
        } else if (lastVersion < routerEntity.getSerialNumber()) {
            // 本地库中有新版本数据同步到公共Redis库
            upgradeRemoteRouter(routerEntity);
        } else {
            // lastVersion == this.routerEntity to do nothing
        }
    }


    /**
     * <h2>删除路由</h2>
     * date 2020-12-23 22:28
     *
     * @param serialNumber 路由版本号
     * @param id 路由ID
     * @return reactor.core.publisher.Mono<java.lang.String>
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    public Mono<Long> delete(Long serialNumber, String id) {
        Long versionInRedis = getVersionInRedis();
        if (versionInRedis < serialNumber + 1) {
            RouterEntity routerEntity = routerDao.query();
            if (routerEntity != null) {
                List<RouteDefinition> routeDefinitions = JSON
                    .parseObject(routerEntity.getRouterJson(),
                        new TypeReference<List<RouteDefinition>>() {
                        });
                Iterator<RouteDefinition> iterator = routeDefinitions.iterator();
                boolean removable = false;
                while (iterator.hasNext()) {
                    RouteDefinition next = iterator.next();
                    if (next.getId().equals(id)) {
                        iterator.remove();
                        removable = true;
                    }
                }
                if (removable) {
                    RouterEntity routerEntity1 = new RouterEntity(serialNumber + 1,
                        JSON.toJSONString(iterator), new Date().toString());
                    boolean upgradeRemoteRouter = upgradeRemoteRouter(routerEntity1);
                    if (upgradeRemoteRouter) {
                        this.publisher.publishEvent(new RefreshRoutesEvent(this));
                        return Mono.just(serialNumber + 1);
                    }
                }
            }
        }
        return Mono.just(serialNumber);
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
        RouterEntity routerEntity = routerDao.query();
        if (routerEntity == null) {
            return this.routeLocator.getRouteDefinitions()
                .collectList()
                .map(routeDefinitions -> new RouteDefinitionBO(0L, routeDefinitions));
        } else {
            return this.routeLocator.getRouteDefinitions()
                .collectList()
                .map(routeDefinitions -> new RouteDefinitionBO(routerEntity.getSerialNumber(),
                    routeDefinitions));
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * <h2>更新路由</h2>
     * date 2020-12-23 22:25
     *
     * @param serialNumber 路由版本
     * @param definition 路由对象
     * @return reactor.core.publisher.Mono<org.springframework.cloud.gateway.route.RouteDefinition>
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    public Mono<Long> update(Long serialNumber, RouteDefinition definition) {

        return create(serialNumber, definition);
    }

    /**
     * <h2>新增路由</h2>
     * date 2020-12-23 22:22
     *
     * @param serialNumber 路由版本
     * @param definition 路由对象
     * @return reactor.core.publisher.Mono<org.springframework.cloud.gateway.route.RouteDefinition>
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    public Mono<Long> create(Long serialNumber, RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return Mono.just(++serialNumber);
    }

    private Long getVersionInRedis() {
        String version = redisTemplate.opsForValue().get(GATEWAY_ROUTES_IN_REDIS_VERSION_NAME);
        if (StringUtils.isBlank(version)) {
            return 0L;
        }
        return Long.parseLong(version);
    }


    private void upgradeLocalRouter(Long lastVersion) {
        routeLocator
            .getRouteDefinitions()
            .collectList()
            .map(routeDefinitions -> JSON.toJSONString(routeDefinitions))
            .map(routeDefinitionsJson -> new RouterEntity(lastVersion, routeDefinitionsJson,
                new Date().toString()))
            .doOnNext(lastVersionRouterEntity -> {
                this.upgradeLocalRouter(lastVersionRouterEntity);
            });
    }

    /**
     * <h2>更新本地数据库中的路由</h2>
     * date 2020-12-26 17:19
     *
     * @param routerEntity 远端仓库路由版本
     * @return void
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void upgradeLocalRouter(RouterEntity routerEntity) {
        this.routerDao.delete();
        this.routerDao.create(routerEntity);
    }


    /**
     * <h2>使用本地路由更新远端路由</h2>
     * date 2020-12-26 17:22
     *
     * @param routerEntity 期望更新远端仓库的本地路由
     * @return boolean
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    private boolean upgradeRemoteRouter(RouterEntity routerEntity) {
        boolean result = false;
        try {
            if (lock()) {
                // 二次识别本地版本是否大于远端版本
                if (routerEntity.getSerialNumber() > this.getVersionInRedis()) {
                    redisTemplate.opsForValue().set(GATEWAY_ROUTES_IN_REDIS_VERSION_NAME,
                        String.valueOf(routerEntity.getSerialNumber()));
                    List<RouteDefinition> routeDefinitions = JSON
                        .parseObject(routerEntity.getRouterJson(),
                            new TypeReference<List<RouteDefinition>>() {
                            });
                    routeDefinitions.forEach(routeDefinition -> routeDefinitionWriter
                        .save(Mono.just(routeDefinition)));
                    result = true;
                }
            }
            publish(routerEntity.getSerialNumber());
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            releaseLock();
        }
        return result;
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

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //消息体
        String body = new String(message.getBody());
        //渠道名称
        String topic = new String(pattern);
        log.info("接收到消息：{}", body);
        log.info("由{}渠道发送而来", topic);
    }

    private boolean lock() {
        Boolean locked = redisTemplate.opsForValue()
            .setIfAbsent(GATEWAY_ROUTES_IN_REDIS_LOCKER_NAME, "locked",
                GATEWAY_ROUTES_IN_REDIS_LOCKER_MILLISECONDS,
                TimeUnit.MILLISECONDS);
        return locked != null && locked;
    }

    private void releaseLock() {
        redisTemplate.delete(GATEWAY_ROUTES_IN_REDIS_LOCKER_NAME);
    }


}
