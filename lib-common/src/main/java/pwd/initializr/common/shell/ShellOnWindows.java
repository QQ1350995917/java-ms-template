package pwd.initializr.common.shell;

import pwd.initializr.common.utils.ArrayUtil;

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
  protected String[] getCommandArray() {
    return new String[0];
  }

  @Override
  protected String[] getCommandArray(String[] commands) {
    return new String[0];
  }

  @Override
  protected String[] getCommandForResultArray() {
    return new String[0];
  }

  @Override
  protected String[] getCommandForResultArray(String[] commands) {
    return new String[0];
  }

  protected String[] getWindowsCommandArray(){
   return new String[]{"date"};
  }

  protected String[] getWindowsCommandForResultArray(){
    return new String[]{"date"};
  }
}
