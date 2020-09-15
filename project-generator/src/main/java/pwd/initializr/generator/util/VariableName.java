package pwd.initializr.generator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * pwd.initializr.automatic.util@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-08 18:52
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class VariableName {

    private static Pattern underlinePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static String upperInitials(String string) {
        char[] cs = string.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static String underlineToHump(String underline) {
        underline = underline.toLowerCase();
        Matcher matcher = underlinePattern.matcher(underline);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String humpToUnderline(String hump) {
        return humpToUnderline(hump, false);
    }

    public static String humpToUnderline(String hump, Boolean upperCase) {
        Matcher matcher = humpPattern.matcher(hump);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
