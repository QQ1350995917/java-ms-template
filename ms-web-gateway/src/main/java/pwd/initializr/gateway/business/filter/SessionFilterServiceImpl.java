package pwd.initializr.gateway.business.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Resource;
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
import pwd.initializr.gateway.persistence.entity.RouterVersionEntity;
import pwd.initializr.gateway.persistence.entity.SessionEntity;
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

  private static final Map<String, String> withoutTokenUrl = new HashMap<>();
  /**
   * 管理员url中path特征
   */
  public static String adminPath = "/api/admin";
  /**
   * 管理员登录页面
   */
  public static String adminLogin = "/account/api/admin/session";
  /**
   * 用户登录页面
   */
  public static String userLogin = "/account/api/session";

  static {
    withoutTokenUrl.put("/account/api/admin/session", "PUT");
    withoutTokenUrl.put("/account/api/session", "PUT");
    withoutTokenUrl.put("/book/api/book", "GET");
  }

  @Resource
  private SessionDao sessionDao;
  @Resource
  private RedisTemplate<String, String> redisTemplate;

  private Set<SessionBO> whiteList = new LinkedHashSet<>();
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
      long remoteVersion = Long.parseLong(body);
      Long localVersion = sessionDao.queryVersion();
      log.info("最新版本{},本地版本{},最新版本大于本地版本则更新", remoteVersion, localVersion);
      if (remoteVersion > localVersion) {
        log.info("开始更新");
        // 公共Redis库中有新版本的数据同步到本地
        this.whiteList = upgradeLocalSessionWhiteList(remoteVersion);
      }
    }
  }

  @Override
  public void run(ApplicationArguments args) {
    sessionDao.createTable();

    // 查询中央仓库版本
    Long remoteVersion = this.getRemoteVersion();
    Long localVersion = sessionDao.queryVersion();
    if (remoteVersion > localVersion) {
      // 公共Redis库中有新版本的数据同步到本地
      this.whiteList = upgradeLocalSessionWhiteList(remoteVersion);
    } else if (remoteVersion < localVersion) {
      // 本地库中有新版本数据同步到公共Redis库
      this.whiteList = upgradeRemoteSessionWhiteList(localVersion);
    } else {
      // remoteVersion == localVersion to do nothing
    }
  }

  public Mono<SessionBO> list() {
    return Mono.empty();
  }

  public Mono<Integer> update(SessionBO sessionBO) {
    return Mono.just(sessionDao.update(this.convertBoToEntity(sessionBO)));
  }

  public Mono<Integer> delete(Long id) {
    return Mono.just(sessionDao.deleteById(id));
  }

  public Mono<Long> create(SessionBO sessionBO) {
    sessionDao.create(sessionBO);
    return Mono.just(sessionBO.getId());
  }

  private Set<SessionBO> upgradeLocalSessionWhiteList(Long remoteVersion){
    String sessionFilterWhiteListJsonString = redisTemplate.opsForValue().get(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_KEY_NAME);
    LinkedHashSet<SessionBO> sessionBOS = JSON.parseObject(sessionFilterWhiteListJsonString,
        new TypeReference<LinkedHashSet<SessionBO>>() {
        });
    if (sessionBOS != null){
      List<SessionEntity> collect = sessionBOS.stream()
          .filter(sessionEntity -> {
            sessionEntity.setVersion(remoteVersion);
            return true;
          })
          .map(this::convertBoToEntity)
          .collect(Collectors.toList());
      sessionDao.delete();
      sessionDao.createByBatch(collect);
    }
    return sessionBOS;
  }

  private Set<SessionBO> upgradeRemoteSessionWhiteList(Long localVersion){
    Set<SessionBO> collect = new LinkedHashSet<>();
    try {
      if (lock()) {
        // 二次识别本地版本是否大于远端版本
        if (localVersion > this.getRemoteVersion()) {
          List<SessionEntity> sessionEntities = sessionDao.queryAll();
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

  private void publish(Long serialNumber) {
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

  private Long getRemoteVersion() {
    String version = redisTemplate.opsForValue().get(GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_VERSION_NAME);
    if (StringUtils.isBlank(version)) {
      return 0L;
    }
    return Long.parseLong(version);
  }

  public boolean skipToken(String path, String method) {
    // TODO 更新时候的并发问题

    return whiteList.stream().filter(sessionBO -> sessionBO.getMethod().equals(method))
        .anyMatch(sessionBO -> Pattern.matches(sessionBO.getExpression(), path));
  }
}
