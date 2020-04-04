package pwd.initializr.storage.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.storage.api.user.vo.DownloadInput;
import pwd.initializr.storage.api.user.vo.ListInput;
import pwd.initializr.storage.business.QueryService;
import pwd.initializr.storage.business.StorageServiceImpl;
import pwd.initializr.storage.business.bo.StorageBO;

/**
 * pwd.initializr.storage.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 17:17
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
@RequestMapping(value = "/api/user/file")
public class FileController extends UserController implements FileApi {

  @Value("${spring.minio.bucket_name}")
  private String bucketName;
  @Autowired
  private QueryService queryService;
  @Autowired
  private StorageServiceImpl storageService;

  @PostMapping("/upload/batch")
  public String handleFileUpload(HttpServletRequest request, Model model) {
    StringBuffer resultMessage = new StringBuffer();
    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
    MultipartFile file;
    for (int i = 0; i < files.size(); ++i) {
      file = files.get(i);
      if (!file.isEmpty()) {
        String name = file.getOriginalFilename();
        try {
          InputStream inputStream = file.getInputStream();
          storageService.uploadFile(bucketName, name, inputStream);
          resultMessage.append("第 " + i + " 个文件[" + name + "]上传成功");
        } catch (Exception e) {
          resultMessage.append("第 " + i + " 个文件[" + name + "]上传失败");
          e.printStackTrace();
        }
      } else {
        resultMessage.append("第 " + i + " 个文件为空");
      }
    }
    model.addAttribute("message", resultMessage.toString());
    return "upload";
  }

  @ApiOperation(value = "文件上传")
  @PostMapping(value = {"/upload"})
  public String upload(MultipartFile file, Model model) {
    String name = file.getOriginalFilename();
    try {
      InputStream inputStream = file.getInputStream();
      storageService.uploadFile(bucketName, name, inputStream);
      model.addAttribute("message", "单文件上传[" + name + "]成功");
    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("message", "单文件上传[" + name + "]失败");
    } finally {
      return "upload";
    }
  }

  @ApiOperation(value = "文件上传页面")
  @GetMapping(value = {"/upload"})
  public String upload(Model model) {
    model.addAttribute("message", "");
    return "upload";
  }

  @Override
  public void upload(HttpServletRequest request) {

  }

  @Override
  public void upload(MultipartFile file) {

  }


  @ApiOperation(value = "文件下载")
  @GetMapping(value = {"/download"})
  @Override
  public void download(DownloadInput input) {
    StorageBO oneByUrl = queryService.findOneByUrl(input.getUrl());
    getResponse().reset();
    getResponse().setContentType("application/octet-stream");
    getResponse().addHeader("Content-Disposition", "attachment;fileName=" + oneByUrl.getFilename());
    OutputStream outputStream = null;
    InputStream inputStream = null;
    try {
      outputStream = getResponse().getOutputStream();
      inputStream = queryService.getObject(oneByUrl);
      int len = 0;
      byte[] buffer = new byte[1024];
      while ((len = inputStream.read(buffer)) > 0) {
        outputStream.write(buffer, 0, len);
      }
      outputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
      if (outputStream != null) {
        try {
          outputStream.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  @ApiOperation(value = "文件列表")
  @GetMapping(value = {"/list"})
  @Override
  public void list(ListInput input) {
    ObjectList<StorageBO> storageObjectList = queryService.listFile();
    super.outputData(storageObjectList);
  }
}
