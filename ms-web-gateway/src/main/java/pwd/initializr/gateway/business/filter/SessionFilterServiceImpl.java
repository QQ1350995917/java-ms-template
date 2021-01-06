package pwd.initializr.gateway.business.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.gateway.persistence.dao.SessionDao;
import pwd.initializr.gateway.persistence.dao.SessionVersionDao;
import pwd.initializr.gateway.persistence.entity.SessionEntity;
import pwd.initializr.gateway.persistence.entity.SessionVersionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.list@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-10 09:30
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
@Slf4j
public class SessionFilterServiceImpl implements ApplicationRunner, MessageListener {

  private static Set<SessionBO> whiteList = new TreeSet<>(((o1, o2) ->Integer.compare(o2.getWeight(), o1.getWeight())));

  @Resource
  private SessionDao sessionDao;
  @Resource
  private SessionVersionDao sessionVersionDao;
  @Resource
  private RedisTemplate<String, String> redisTemplate;

  @Value("${gateway.filter.global.session.token.skip.all:false}")
  private Boolean skipSessionFilter = false;
  @Value("${gateway.filter.global.session.in.redis.sync.topic}")
  public String GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_SYNC_TOPIC;
  @Value("${gateway.filter.global.session.in.redis.locker.name}")
  public String GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_LOCKER_NAME;
  @Value("${gateway.filter.global.session.in.redis.locked.milliseconds}")
  public Integer GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_LOCKER_MILLISECONDS;
  @Value("${gateway.filter.global.session.in.redis.version.name}")
  public String GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_VERSION_NAME;
  @Value("${gateway.filter.global.session.in.redis.key.name}")
  public String GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_KEY_NAME;

  public Boolean getSkipSessionFilter() {
    return skipSessionFilter;
  }

  @Override
  public void onMessage(Message message, byte[] bytes) {
    //消息体
    String body = new String(message.getBody());
    //渠道名称
    String topic = new String(bytes);
    log.info("接收到消息：{}", body);
    log.info("由{}渠道发送而来", topic);
    if (GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_SYNC_TOPIC.equals(topic)) {
      int remoteVersion = Integer.parseInt(body);
      int localVersion = getLocalVersion();
      log.info("最新版本{},本地版本{},最新版本大于本地版本则更新", remoteVersion, localVersion);
      if (remoteVersion > localVersion) {
        log.info("开始更新");
        // 公共Redis库中有新版本的数据同步到本地
        whiteList = upgradeLocalSessionWhiteList(remoteVersion);
      }
    }
  }

  @Override
  public void run(ApplicationArguments args) {
    sessionDao.createTable();
    sessionVersionDao.createTable();
    // 查询中央仓库版本
    int remoteVersion = this.getRemoteVersion();
    int localVersion = getLocalVersion();
    if (remoteVersion > localVersion) {
      // 公共Redis库中有新版本的数据同步到本地
      whiteList = upgradeLocalSessionWhiteList(remoteVersion);
    } else if (remoteVersion < localVersion) {
      // 本地库中有新版本数据同步到公共Redis库
      whiteList = upgradeRemoteSessionWhiteList(localVersion);
    } else {
      whiteList = this.sessionDao.queryAll().stream().map(this::convertEntityToBo).collect(
          Collectors.toSet());
    }
  }

  public Flux<SessionBO> list() {
    return Mono.just(whiteList).flatMapMany(Flux::fromIterable);
  }

  public Mono<Integer> update(SessionBO sessionBO) {
    if (whiteList.stream().anyMatch(
        bo -> bo.getId().equals(sessionBO.getId())
            && bo.getWeight().equals(sessionBO.getWeight())
            && Objects.equals(bo, sessionBO))) {
      return Mono.just(-1);
    }
    int localVersion = getLocalVersion();
    int remoteVersion = getRemoteVersion();
    int expiredVersion = localVersion + 1;
    boolean editable = false;
    if (remoteVersion < expiredVersion) {
      try {
        if (lock()) {
          // 二次识别本地版本是否大于远端版本
          if (expiredVersion > this.getRemoteVersion()) {
            whiteList.stream()
                .map(bo -> {
                  if (bo.getId().equals(sessionBO.getId())){
                    bo.setWeight(sessionBO.getWeight());
                    bo.setMethod(sessionBO.getMethod());
                    bo.setExpression(sessionBO.getExpression());
                    bo.setCreateTime(new Date().toString());
                  }
                  return bo;
                })
                .forEach(bo -> bo.setVersion(expiredVersion));
            writeUpgradePublic(expiredVersion);
            editable = true;
          }
        }
      } catch (Exception e) {
        log.error(e.getMessage());
      } finally {
        releaseLock();
      }
    }
    if (editable) {
      upgradeLocalSessionWhiteList(expiredVersion);
    }
    return Mono.just(editable ? expiredVersion : localVersion);
  }

  public Mono<Integer> delete(Long id) {
    int localVersion = getLocalVersion();
    int remoteVersion = getRemoteVersion();
    int expiredVersion = localVersion + 1;
    boolean editable = false;
    if (remoteVersion < expiredVersion) {
      try {
        if (lock()) {
          // 二次识别本地版本是否大于远端版本
          if (expiredVersion > this.getRemoteVersion()) {
            Iterator<SessionBO> iterator = whiteList.iterator();
            if (iterator.hasNext()) {
              SessionBO next = iterator.next();
              if (next.getId().equals(id)) {
                whiteList.remove(next);
              }
            }
            writeUpgradePublic(expiredVersion);
            sessionDao.deleteById(id);
            editable = true;
          }
        }
      } catch (Exception e) {
        log.error(e.getMessage());
      } finally {
        releaseLock();
      }
    }
    if (editable) {
      upgradeLocalSessionWhiteList(expiredVersion);
    }
    return Mono.just(editable ? expiredVersion : localVersion);
  }

  public boolean contains(SessionBO sessionBO){
    return whiteList.contains(sessionBO);
  }

  public Mono<Integer> create(SessionBO sessionBO) {
    sessionBO.setCreateTime(new Date().toString());
    int localVersion = getLocalVersion();
    int remoteVersion = getRemoteVersion();
    int expiredVersion = localVersion + 1;
    boolean editable = false;
    if (remoteVersion < expiredVersion) {
      try {
        if (lock()) {
          // 二次识别本地版本是否大于远端版本
          if (expiredVersion > this.getRemoteVersion()) {
            if (!whiteList.contains(sessionBO)) {
              Long maxId = getMaxIdInWhiteList();
              sessionBO.setId(maxId + 1);
              whiteList.add(sessionBO);
              whiteList.stream().forEach(bo -> bo.setVersion(expiredVersion));
              writeUpgradePublic(expiredVersion);
              editable = true;
            }
          }
        }
      } catch (Exception e) {
        log.error(e.getMessage());
      } finally {
        releaseLock();
      }
    }
    if (editable) {
      upgradeLocalSessionWhiteList(expiredVersion);
    }
    return Mono.just(editable ? expiredVersion : localVersion);
  }

  private Set<SessionBO> upgradeLocalSessionWhiteList(Integer remoteVersion){
    String sessionFilterWhiteListJsonString = redisTemplate.opsForValue().get(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_KEY_NAME);
    LinkedHashSet<SessionBO> sessionBOS = JSON.parseObject(sessionFilterWhiteListJsonString,
        new TypeReference<LinkedHashSet<SessionBO>>() {
        });
    sessionVersionDao.delete();
    sessionVersionDao.create(new SessionVersionEntity(remoteVersion,new Date().toString()));
    if (sessionBOS != null){
      List<SessionEntity> collect = sessionBOS.stream()
          .filter(sessionEntity -> {
            sessionEntity.setVersion(remoteVersion);
            return true;
          })
          .map(this::convertBoToEntity)
          .collect(Collectors.toList());
      sessionDao.delete();
      if (collect.size() > 0) {
        sessionDao.createByBatch(collect);
      }
    }
    return sessionBOS;
  }

  private Set<SessionBO> upgradeRemoteSessionWhiteList(Integer localVersion){
    Set<SessionBO> collect = new LinkedHashSet<>();
    try {
      if (lock()) {
        // 二次识别本地版本是否大于远端版本
        if (localVersion > this.getRemoteVersion()) {
          Set<SessionEntity> sessionEntities = sessionDao.queryAll();
          // 首先写入数据
          redisTemplate.opsForValue().set(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_KEY_NAME,JSON.toJSONString(sessionEntities));
          // 其次更新版本
          redisTemplate.opsForValue()
              .set(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_VERSION_NAME, String.valueOf(localVersion));
          // 最后记录状态
          collect = sessionEntities.stream().map(this::convertEntityToBo)
              .collect(Collectors.toSet());
        }
      }
      // 通知其他节点有新版本路由数据产生
      publish(localVersion);
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      releaseLock();
    }
    return collect;
  }


  private boolean lock() {
    Boolean locked = redisTemplate.opsForValue()
        .setIfAbsent(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_LOCKER_NAME, "locked",
            GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_LOCKER_MILLISECONDS,
            TimeUnit.MILLISECONDS);
    return locked != null && locked;
  }

  private void publish(Integer serialNumber) {
    redisTemplate.convertAndSend(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_SYNC_TOPIC, serialNumber);
  }

  private void releaseLock() {
    redisTemplate.delete(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_LOCKER_NAME);
  }

  private SessionEntity convertBoToEntity(SessionBO bo){
    SessionEntity sessionEntity = new SessionEntity();
    BeanUtils.copyProperties(bo,sessionEntity);
    return sessionEntity;
  }

  private SessionBO convertEntityToBo(SessionEntity entity){
    SessionBO sessionBO = new SessionBO();
    BeanUtils.copyProperties(entity,sessionBO);
    return sessionBO;
  }

  public Integer getRemoteVersion() {
    String version = redisTemplate.opsForValue().get(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_VERSION_NAME);
    if (StringUtils.isBlank(version)) {
      return 0;
    }
    return Integer.parseInt(version);
  }

  private Long getMaxIdInWhiteList(){
    Long maxId = 0L;
    Iterator<SessionBO> iterator = whiteList.iterator();
    while (iterator.hasNext()) {
      SessionBO next = iterator.next();
      if (next.getId() > maxId) {
        maxId = next.getId();
      }
    }
    return maxId;
  }

  private void writeUpgradePublic(Integer expiredVersion){
    // 首先写入数据
    redisTemplate.opsForValue().set(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_KEY_NAME,JSON.toJSONString(whiteList));
    // 其次更新版本
    redisTemplate.opsForValue()
        .set(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_VERSION_NAME, String.valueOf(expiredVersion));
    // 最后发布版本
    publish(expiredVersion);
  }

  public Integer getLocalVersion(){
    SessionVersionEntity sessionVersionEntity = sessionVersionDao.query();
    if (sessionVersionEntity == null) {
      sessionVersionEntity = new SessionVersionEntity();
    }
    return sessionVersionEntity.getVersion();
  }

  public boolean skipToken(String path, String method) {
    // TODO 更新时候的并发问题
    return whiteList.stream().filter(sessionBO -> sessionBO.getMethod().equals(method))
        .anyMatch(sessionBO -> Pattern.matches(sessionBO.getExpression(), path));
  }
}
