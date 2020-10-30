package pwd.initializr.common.shell;

import pwd.initializr.common.utils.ArrayUtil;

/**
 * pwd.initializr.common.shell@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-25 22:09
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ShellOnLinux extends ShellDefault {

  String[] command = {"/bin/sh", "-c"};

  @Override
  protected String[] getCommandArray() {
    String[] linuxCommandArray = getLinuxCommandArray();
    return this.getCommandArray(linuxCommandArray);
  }

  @Override
  protected String[] getCommandArray(String[] commands) {
    return ArrayUtil.addAll(command,commands);
  }

  @Override
  protected String[] getCommandForResultArray() {
    String[] linuxCommandForResultArray = getLinuxCommandForResultArray();
    return this.getCommandForResultArray(linuxCommandForResultArray);
  }

  @Override
  protected String[] getCommandForResultArray(String[] commands) {
    return ArrayUtil.addAll(command,commands);
  }

  protected String[] getLinuxCommandArray(){
    return new String[]{"date"};
  }

  protected String[] getLinuxCommandForResultArray(){
    return new String[]{"date"};
  }

}
