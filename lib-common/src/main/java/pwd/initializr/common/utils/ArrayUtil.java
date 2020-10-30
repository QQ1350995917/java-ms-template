package pwd.initializr.common.utils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-27 20:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ArrayUtil extends org.apache.commons.lang.ArrayUtils{

    public static String[] addAll(String[] strings0, String[] strings1) {
        int length0 = strings0.length;
        int length1 = strings1.length;
        String[] strings = new String[length0 + length1];
        System.arraycopy(strings0, 0, strings, 0, length0);
        System.arraycopy(strings1, 0, strings, length0, length1);
        return strings;
    }
}
