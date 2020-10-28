package pwd.initializr.monitor.rpc;

import java.io.Serializable;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>当前登录用户信息</h1>
 *
 * date 2020-10-28 16:59
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface ILoggedStat extends Serializable {

    String  getUser();
    String  getTty();
    String  getFrom();
    String  getLogin();
    String  getIdle();
    String  getJcpu();
    String  getPcpu();
    String  getWhat();
}
