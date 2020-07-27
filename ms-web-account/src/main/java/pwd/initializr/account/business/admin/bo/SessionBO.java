package pwd.initializr.account.business.admin.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.account.rpc.RPCSession;

/**
 * pwd.initializr.account.business.admin.bo@ms-web-initializr
 *
 * <h1>服务层逻辑对象封装：管理员会话信息</h1>
 *
 * date 2020-05-30 14:24
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SessionBO extends RPCSession {

  public SessionBO(Long uid, String uName, Long accountId, String accountName,
      Long timestamp) {
    super(uid, uName, accountId, accountName, timestamp);
  }
}
