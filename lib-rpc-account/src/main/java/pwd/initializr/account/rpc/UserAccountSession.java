package pwd.initializr.account.rpc;

/**
 * pwd.initializr.account.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-11 21:29
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserAccountSession {

  Long getId();

  Long getUserId();

  String getLoginName();

  Integer getType();

  Integer getStatus();

}
