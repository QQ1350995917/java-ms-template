package pwd.initializr.storage.api.robot;

import io.swagger.annotations.Api;
import java.io.InputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.common.web.api.robot.RobotController;
import pwd.initializr.storage.api.robot.vo.UploadOutput;
import pwd.initializr.storage.business.StorageService;
import pwd.initializr.storage.business.bo.StorageBO;

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
    tags = "文件",
    value = "文件Api",
    description = "文件API"
)
@RestController(value = "fileApiByRobot")
@RequestMapping(value = "/api/robot/file")
public class FileController extends RobotController implements FileApi {

  @Autowired
  private StorageService storageService;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void download(@RequestParam("url") String url) {
    StorageBO oneByUrl = storageService.findOneByUrl(url);
    getResponse().reset();
    getResponse().setContentType("application/octet-stream");
    getResponse().addHeader("Content-Disposition",
        "attachment;fileName=" + oneByUrl.getFileName() + "." + oneByUrl.getFileSuffix());
    try (InputStream inputStream = storageService.getObject(oneByUrl)) {
      this.outputAttachmentFile(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @PostMapping(value = "/{appName}/{bucketName}/{fileName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void upload(
      @PathVariable("appName") String appName,
      @PathVariable("bucketName") String bucketName,
      @PathVariable("fileName") String fileName,
      @RequestPart("file") MultipartFile file) {
    String contentType = file.getContentType();
    String name = file.getOriginalFilename();
    String suffix = name.substring(name.lastIndexOf(".") + 1);
    try (InputStream inputStream = file.getInputStream()) {
      StorageBO storageBO = new StorageBO();
      storageBO.setApp(appName);
      storageBO.setFileName(fileName);
      storageBO.setFileSuffix(suffix);
      storageBO.setFileType(contentType);
      storageBO.setBucketName(bucketName);
      StorageBO result = storageService.uploadFile(storageBO, inputStream);
      UploadOutput RPCUploadOutput = new UploadOutput();
      BeanUtils.copyProperties(result, RPCUploadOutput);
      outputData(RPCUploadOutput);
    } catch (Exception e) {
      e.printStackTrace();
      outputException(500, e.getMessage());
    }
  }

}
