package pwd.initializr.storage.api.robot;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
@Api(
    tags = "文件",
    value = "文件Api",
    description = "文件API"
)
@RestController(value = "fileApiByRobot")
@RequestMapping(value = "/api/robot/file")
public interface FileApi {

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void download(@PathVariable("id") String id);

  @PostMapping(value = "/{appName}/{bucketName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void upload(
      @PathVariable("app") String app,
      @PathVariable("bucketName") String bucketName,
      @RequestPart("file") MultipartFile file);
}
