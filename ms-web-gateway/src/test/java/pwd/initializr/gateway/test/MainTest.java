package pwd.initializr.gateway.test;

import java.util.regex.Pattern;

/**
 * pwd.initializr.gateway.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-01 16:27
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class MainTest {

    public static void main(String[] args) {
        String path = "/account/api/admin/session";
        boolean matches = Pattern.matches("(.*)", path);
        System.out.println(matches);
    }
}
