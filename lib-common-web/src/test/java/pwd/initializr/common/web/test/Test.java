package pwd.initializr.common.web.test;

import java.io.File;
import javax.activation.MimetypesFileTypeMap;

/**
 * pwd.initializr.common.web.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-27 11:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Test {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Downloads\\hyperic-sigar-1.6.4.zip");
        String type = new MimetypesFileTypeMap().getContentType("application/x-zip-compressed");
        System.out.println();
    }

}
