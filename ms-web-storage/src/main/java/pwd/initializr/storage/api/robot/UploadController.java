package pwd.initializr.storage.api.robot;

import io.swagger.annotations.Api;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.common.web.api.ApiController;
import pwd.initializr.storage.api.robot.vo.UploadInput;
import pwd.initializr.storage.business.StorageServiceImpl;
import pwd.initializr.storage.business.bo.Storage;

/**
 * pwd.initializr.storage.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 23:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文件上传",
    value = "文件上传Api",
    description = "文件上传API"
)
@Controller(value = "uploadApi")
@RequestMapping(value = "/api/robot/upload")
public class UploadController extends ApiController implements UploadApi {

  @Autowired
  private StorageServiceImpl storageService;


  @PostMapping("")
  @Override
  public void upload(HttpServletRequest request) {

  }

  @PostMapping("/multi")
  @Override
  public void upload(@RequestParam(value = "file") MultipartFile file,
      @RequestParam(value = "params") UploadInput input) {
    String name = file.getOriginalFilename();
    try {
      InputStream inputStream = file.getInputStream();
      Storage storage = storageService
          .uploadFile(input.getBucketName(), input.getObjectName(), inputStream,
              input.getContentType());
      outputData(storage.getUrl());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
