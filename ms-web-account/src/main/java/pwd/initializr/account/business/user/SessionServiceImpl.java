package pwd.initializr.account.business.user;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.persistence.dao.AccountEntity;
import pwd.initializr.account.persistence.mapper.AccountMapper;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.utils.StringUtil;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 15:43
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class SessionServiceImpl implements SessionService {

  @Value("account_login_prefix")
  private static String SESSION_PREFIX = "sso_identify_";

  @Autowired
  private AccountMapper accountMapper;
  @Autowired
  private RedisClient redisClient;

  @Override
  public String login(Account account) {
    AccountEntity accountEntity = accountMapper
        .findByIdentifyAndPassword(account.getIdentify(), account.getPassword());
    if (accountEntity == null) {
      return null;
    }
    Account result = new Account();
    BeanUtils.copyProperties(accountEntity, result);
    this.createSession(result);
    // TODO 加密混淆或者md5等操作
    return result.getId() + "";
  }

  @Override
  public Account info(String sessionId) {
    String session = this.getSession(sessionId);
    if (session == null) {
      return null;
    }
    return JSON.parseObject(session, Account.class);
  }

  @Override
  public void logout(String sessionId) {
    this.delSession(sessionId); // TODO 会不会有通配符安全问题？
  }

  @Override
  public void createSession(Account account) {
    // TODO 详细设计Session
    String key = StringUtils.join(new String[]{SESSION_PREFIX, String.valueOf(account.getId())});
    redisClient.set(key, JSON.toJSONString(account));
  }

  @Override
  public String getSession(String sessionId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, sessionId});
    return redisClient.get(key);
  }

  @Override
  public void delSession(String sessionId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, sessionId});
    redisClient.del(key);
  }
}
