package pwd.initializr.common.shell;

import pwd.initializr.common.utils.ArrayUtils;

/**
 * pwd.initializr.common.shell@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-25 22:08
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ShellOnWindows extends ShellDefault {
    String[] command = { "cmd", "/c"};

  @Override
  String[] getCommandArray() {
    String[] linuxCommandArray = getWindowsCommandArray();
    return ArrayUtils.addAll(command,linuxCommandArray);
  }

  @Override
  String[] getCommandForResultArray() {
    String[] linuxCommandForResultArray = getWindowsCommandForResultArray();
    return ArrayUtils.addAll(command,linuxCommandForResultArray);
  }

  String[] getWindowsCommandArray(){
   return new String[]{"date"};
  }

  String[] getWindowsCommandForResultArray(){
    return new String[]{"date"};
  }
}
