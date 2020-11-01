package pwd.initializr.monitor.rpc;

import java.io.Serializable;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>主机信息</h1>
 *
 * date 2020-10-28 13:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface IHost extends Serializable {

  /**
   * 逻辑组名 联合主键
   */
  String getGroupName();

  /**
   * 主机名 联合主键 network node hostname
   */
  String getNodeName();

  /**
   * 发行版本ID
   */
  String getDistributeId();

  /**
   *
   * @return
   */
  String getDistributeName();

  /**
   *
   * @return
   */
  String getDistributeIdLike();

  /**
   *
   * @return
   */
  String getDistributeVersion();

  /**
   *
   * @return
   */
  String getDistributeCodeName();

  /**
   *
   * @return
   */
  String getDistributeDescription();

  /**
   * operating system
   */
  String getOperatingSystem();

  /**
   * machine hardware name
   * x86_64
   */
  String getMachine();

  /**
   * 操作系统启动时间点
   *
   * 2020-09-17 15:43:39
   */
  String getSystemUpSince();

  /**
   * kernel name
   */
  String getKernelName();

  /**
   * kernel release
   */
  String getKernelRelease();

  /**
   * kernel version
   */
  String getKernelVersion();

  /**
   * processor type or "unknown"
   */
  String getProcessor();

  /**
   * hardware platform or "unknown"
   */
  String getHardwarePlatform();

}
