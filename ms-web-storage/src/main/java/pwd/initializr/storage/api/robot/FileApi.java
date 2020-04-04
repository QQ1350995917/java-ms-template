package pwd.initializr.storage.api.robot;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * pwd.initializr.storage.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 23:02
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface FileApi {

  void delete(@PathVariable("appName") String appName,
      @PathVariable("bucketName") String bucketName, @RequestBody List<String> objectNames);

  void upload(@PathVariable("appName") String appName,
      @PathVariable("bucketName") String bucketName, @RequestPart("file") MultipartFile file);
}
