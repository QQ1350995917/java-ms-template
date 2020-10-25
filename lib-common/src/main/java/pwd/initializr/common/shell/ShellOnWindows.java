package pwd.initializr.common.shell;

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

  @Override
  String[] getCommandArray() {
    String[] command = { "cmd", "/c", "" };
    return new String[0];
  }
}
