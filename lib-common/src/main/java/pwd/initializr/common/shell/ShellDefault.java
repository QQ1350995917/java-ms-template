package pwd.initializr.common.shell;

import java.io.IOException;
import org.apache.commons.io.IOUtils;

/**
 * pwd.initializr.common.shell@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-25 22:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class ShellDefault implements Shell {

  @Override
  public void exec() {
    try {
      Process process = Runtime.getRuntime().exec(getCommandArray());
      String result = IOUtils.toString(process.getInputStream());
      System.out.println(result);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  abstract String[] getCommandArray();

}
