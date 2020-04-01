package pwd.initializr.storage.api.robot;

import io.swagger.annotations.Api;
import java.io.InputStream;
import java.util.stream.Stream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.common.web.api.ApiController;
import pwd.initializr.storage.business.StorageServiceImpl;
import pwd.initializr.storage.business.bo.StorageBO;
import pwd.initializr.storage.rpc.UploadInput;
import pwd.initializr.storage.rpc.UploadOutput;

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
@Controller(value = "uploadApiByRobot")
@RequestMapping(value = "/api/robot/upload")
public class UploadController extends ApiController implements UploadApi {

  @Autowired
  private StorageServiceImpl storageService;

  @PostMapping(value = "")
  @Override
  public void upload(@RequestParam(value = "file") MultipartFile[] files, UploadInput input) {
    Stream.of(files).forEach(file -> {
      String name = file.getOriginalFilename();
      String contentType = file.getContentType();
      try {
        InputStream inputStream = file.getInputStream();
        StorageBO storageBO = storageService
            .uploadFile(input.getBucketName(), name, inputStream, contentType);
        UploadOutput uploadOutput = new UploadOutput();
        BeanUtils.copyProperties(storageBO, uploadOutput);
        outputData(uploadOutput);
      } catch (Exception e) {
        e.printStackTrace();
        outputException(500,e.getMessage());
      }
    });
  }
}
