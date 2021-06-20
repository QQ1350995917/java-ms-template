package pwd.initializr.edu.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.edu.FeignConfig;

/**
 * pwd.initializr.typeface.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 23:27
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@FeignClient(value = "storage", configuration = FeignConfig.class, fallback = StorageServiceFallbackImpl.class)
public interface StorageService {

  @PostMapping(value = "/api/robot/file/{appName}/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  String upload(
      @PathVariable("appName") String appName,
      @PathVariable("bucketName") String bucketName,
      @RequestPart("file") MultipartFile file);
}
