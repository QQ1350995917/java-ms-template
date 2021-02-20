package pwd.initializr.common.utils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-20 18:10
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{

    public static String toLowerCaseFirstLetter(String string) {
        if (Character.isLowerCase(string.charAt(0))) {
            return string;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(string.charAt(0)))
                .append(string.substring(1)).toString();
        }
    }

}
