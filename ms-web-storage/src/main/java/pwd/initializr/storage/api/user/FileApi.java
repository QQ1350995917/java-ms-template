package pwd.initializr.storage.api.user;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * pwd.initializr.storage.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 17:03
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文件上传",
    value = "文件上传Api",
    description = "文件上传API"
)
@Controller(value = "uploadApiByUser")
@RequestMapping(value = "/api/file")
public interface FileApi {

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void download(@PathVariable("id") String id);

  @PostMapping(value = "/{appName}/{bucketName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void upload(
      @PathVariable("app") String app,
      @PathVariable("bucketName") String bucketName,
      @RequestPart("file") MultipartFile file);
}
