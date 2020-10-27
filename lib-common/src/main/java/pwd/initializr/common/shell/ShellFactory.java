package pwd.initializr.common.shell;

import org.apache.commons.lang.StringUtils;

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
public class ShellFactory {

    public static Shell getShell() {
        String property = System.getProperty("os.name");
        if (StringUtils.isBlank(property)) {
            return null;
        }
        if (property.equalsIgnoreCase("linux")) {
            return new ShellOnLinux();
        }
        if (property.equalsIgnoreCase("windows")) {
            return new ShellOnWindows();
        }
        if (property.equalsIgnoreCase("mac")) {
            return new ShellOnMacOS();
        }
        return null;
    }
}
