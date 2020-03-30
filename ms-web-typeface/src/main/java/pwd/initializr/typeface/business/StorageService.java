package pwd.initializr.typeface.business;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
@FeignClient(value = "storage", configuration = StorageServiceSupportConfig.class)
public interface StorageService {

  @PostMapping(value = "/api/robot/upload/multi", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String upload(@RequestPart(value = "file") MultipartFile file,
      @RequestParam Map<String, Object> map);

}
