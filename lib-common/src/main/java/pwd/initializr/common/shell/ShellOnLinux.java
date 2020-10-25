package pwd.initializr.common.shell;

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

  public static void main(String[] args) {
    ShellOnLinux shellOnLinux = new ShellOnLinux();
    shellOnLinux.exec();

  }

  @Override
  String[] getCommandArray() {
    String[] command = {"/bin/sh", "-c", "cd /Users/pwd/workspace", "ls -al",};
    return command;
  }
}
