package pwd.initializr.common.test.utils;

import java.util.ArrayList;
import pwd.initializr.common.utils.ArrayUtil;

/**
 * pwd.initializr.common.test.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-30 13:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ArrayUtilTest {

    public static void main(String[] args) {
        String[] command = {"/bin/sh", "-c"};
        String[] command1 = {"cat /proc/cpuinfo"};

        String[] strings = ArrayUtil.addAll(command, command1);
        System.out.println();
    }
}
