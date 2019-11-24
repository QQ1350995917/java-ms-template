package pwd.initializr.account.business.user;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;
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

  @Value("${account_login_prefix:sso_identify_}")
  private static String SESSION_PREFIX;

  @Autowired
  private AccountMapper accountMapper;
  @Autowired
  private UserService userService;

  @Autowired
  private RedisClient redisClient;

  @Override
  public String genToken(UserAccount userAccount) {
    User user = userAccount.getUser();
    String token = JWT.create().withAudience(String.valueOf(user.getId()))
        .sign(Algorithm.HMAC256(user.getPhoneNumber()));
    return token;
  }

  @Override
  public UserAccount login(Account account) {
    AccountEntity accountEntity = accountMapper
        .findByIdentifyAndPassword(account.getIdentify(), account.getPassword());
    if (accountEntity == null) {
      return null;
    }
    Account accountResult = new Account();
    BeanUtils.copyProperties(accountEntity, accountResult);
    User user = userService.findByUserId(accountEntity.getUserId());
    List<Account> accountList = new LinkedList<>();
    accountList.add(accountResult);
    UserAccount userAccount = new UserAccount(user,accountList);
    this.saveSession(userAccount);
    return userAccount;
  }

  @Override
  public Account info(String userId) {
    String session = this.getSession(userId);
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
  public void saveSession(UserAccount userAccount) {
    // TODO 详细设计Session
    User user = userAccount.getUser();
    String key = StringUtils.join(new String[]{SESSION_PREFIX, String.valueOf(user.getId())});
    redisClient.set(key, JSON.toJSONString(user));
  }

  @Override
  public String getSession(String userId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, userId});
    return redisClient.get(key);
  }

  @Override
  public void delSession(String userId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, userId});
    redisClient.del(key);
  }
}
