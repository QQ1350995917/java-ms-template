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
public interface IHostLoggedStat extends Serializable {

    /**
     * 逻辑组名
     */
    String getGroupName();

    /**
     * 主机名 network node hostname
     */
    String getNodeName();

    String  getUser();
    String  getTty();
    String  getFrom();
    String  getLogin();
    String  getIdle();
    String  getJcpu();
    String  getPcpu();
    String  getWhat();
}