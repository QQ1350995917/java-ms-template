package pwd.initializr.common.web.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * pwd.initializr.common.web.util@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 17:30
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ResourceUtil {
    public static String readResourceFileToString(ResourceLoader resourceLoader,String resourceFile) {
        StringBuilder stringBuilder = new StringBuilder();
        Resource publicKeyResource = resourceLoader.getResource("classpath:" + resourceFile);
        try (
            InputStream inputStream = publicKeyResource.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
