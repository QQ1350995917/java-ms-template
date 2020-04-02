package pwd.initializr.typeface.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.storage.rpc.UploadInput;

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
// TODO 比较两种写法，boundary是干嘛的
@FeignClient(value = "storage", configuration = StorageServiceSupportConfig.class)
public interface StorageService {


  @ResponseBody
  @PostMapping(value = "/api/robot/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  String upload(@RequestPart(value = "file") MultipartFile file);


  @ResponseBody
  @PostMapping(value = "/api/robot/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  String upload(@RequestPart(value = "file") MultipartFile file,
      @RequestParam("appName") String appName,
      @RequestParam("bucketName") String bucketName);

  @ResponseBody
  @PostMapping(value = "/api/robot/upload", consumes = "multipart/form-data; boundary=----WebKitFormBoundaryiok3pBT6h8LrgwGw")
  String upload(@RequestPart(value = "file") MultipartFile file,
      @RequestParam("appName") String appName,
      @RequestParam("bucketName") String bucketName,
      @RequestParam("objectName") String objectName,
      @RequestParam("contentType") String contentType);
}
