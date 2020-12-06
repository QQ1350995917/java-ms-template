package pwd.initializr.storage.api.robot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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


  void download(@RequestParam("url") String url);

  void upload(
      @PathVariable("appName") String appName,
      @PathVariable("bucketName") String bucketName,
      @PathVariable("fileName") String fileName,
      @RequestPart("file") MultipartFile file);
}
