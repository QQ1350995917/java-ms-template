package pwd.initializr.storage.api.robot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.InputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import pwd.initializr.common.web.api.ApiController;
import pwd.initializr.storage.business.StorageServiceImpl;
import pwd.initializr.storage.business.bo.StorageBO;
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
@RequestMapping(value = "/api/robot/file")
public class FileController extends ApiController implements FileApi {

  @Autowired
  private StorageServiceImpl storageService;

  @DeleteMapping(value = "/{appName}/{bucketName}/**", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void delete(@PathVariable("appName") String appName,
      @PathVariable("bucketName") String bucketName) {
    // TODO 测试通配符性能
  }

  @ApiResponses({
      @ApiResponse(code = 200, message = "ok", response = UploadOutput.class)
  })
  @PostMapping(value = "/{appName}/{bucketName}/**", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void upload(@PathVariable("appName") String appName,
      @PathVariable("bucketName") String bucketName, @RequestPart("file") MultipartFile file) {
    // TODO 测试通配符性能
    String path = (String) requestLocal.get()
        .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    String bestMatchPattern = (String) requestLocal.get()
        .getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
    String objectName = new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);

    String contentType = file.getContentType();
    try {
      InputStream inputStream = file.getInputStream();
      StorageBO storageBO = storageService
          .uploadFile(bucketName, objectName, inputStream, contentType);
      UploadOutput uploadOutput = new UploadOutput();
      BeanUtils.copyProperties(storageBO, uploadOutput);
      outputData(uploadOutput);
    } catch (Exception e) {
      e.printStackTrace();
      outputException(500, e.getMessage());
    }
  }
}
