package pwd.initializr.storage.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.storage.api.user.vo.DownloadInput;
import pwd.initializr.storage.business.StorageService;
import pwd.initializr.storage.business.bo.StorageBO;
import pwd.initializr.storage.rpc.RPCUploadOutput;

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
@RequestMapping(value = "/index")
public class FileController extends UserController implements FileApi {

  @Value("${spring.minio.bucket_name}")
  private String bucketName;
  @Autowired
  private StorageService storageService;

  @ApiOperation(value = "文件列表")
  @GetMapping(value = {"/{index}/{size}"})
  public void list(@PathVariable("index") int pageIndex, @PathVariable("size") Integer pageSize) {
    PageableQueryResult<StorageBO> storagePageableQuery = storageService
        .listFile(pageIndex, pageSize);
    List<StorageBO> elements = storagePageableQuery.getElements();
    List<RPCUploadOutput> resultElements = new LinkedList<>();
    elements.forEach(element -> {
      RPCUploadOutput rpcUploadOutput = new RPCUploadOutput();
      BeanUtils.copyProperties(element,rpcUploadOutput);
      resultElements.add(rpcUploadOutput);
    });
    PageableQueryResult<RPCUploadOutput> result = new PageableQueryResult<>();
    BeanUtils.copyProperties(storagePageableQuery,result);
    result.setElements(resultElements);
    super.outputData(result);
  }

  @PostMapping("/upload/batch")
  @Override
  public ModelAndView upload(HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    StringBuffer resultMessage = new StringBuffer();
    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
    MultipartFile file;
    for (int i = 0; i < files.size(); ++i) {
      file = files.get(i);
      if (!file.isEmpty()) {
        String name = file.getOriginalFilename();
        try {
          upload0(file);
          resultMessage.append("第 " + i + " 个文件[" + name + "]上传成功");
        } catch (Exception e) {
          resultMessage.append("第 " + i + " 个文件[" + name + "]上传失败");
          e.printStackTrace();
        }
      } else {
        resultMessage.append("第 " + i + " 个文件为空");
      }
    }
    modelAndView.addObject("message", resultMessage.toString());
    modelAndView.setViewName("upload");
    return modelAndView;
  }

  @ApiOperation(value = "文件上传")
  @PostMapping(value = {"/upload"})
  @Override
  public ModelAndView upload(MultipartFile file) {
    ModelAndView modelAndView = new ModelAndView();
    String name = file.getOriginalFilename();
    try {
      upload0(file);
      modelAndView.addObject("message", "单文件上传[" + name + "]成功");
    } catch (Exception e) {
      modelAndView.addObject("message", "单文件上传[" + name + "]失败");
      e.printStackTrace();
    } finally {
      modelAndView.setViewName("upload");
    }
    return modelAndView;
  }

  @ApiOperation(value = "文件下载")
  @GetMapping(value = {"/download"})
  @Override
  public void download(DownloadInput input) {
    StorageBO oneByUrl = storageService.findOneByUrl(input.getUrl());
    getResponse().reset();
    getResponse().setContentType("application/octet-stream");
    getResponse().addHeader("Content-Disposition", "attachment;fileName=" + oneByUrl.getFileName() + "." + oneByUrl.getFileSuffix());
    try (InputStream inputStream = storageService.getObject(oneByUrl)) {
      this.outputAttachmentFile(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @ApiOperation(value = "文件列表页面")
  @GetMapping(value = {""})
  @Override
  public ModelAndView list() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    modelAndView.addObject("attributeName", "");
    return modelAndView;
  }

  private void upload0(MultipartFile file) throws Exception {
    String originalFilename = file.getOriginalFilename();
    String fileName = originalFilename.substring(0,originalFilename.lastIndexOf("."));
    String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    String contentType = file.getContentType();
    try (InputStream inputStream = file.getInputStream()) {
      StorageBO storageBO = new StorageBO();
      storageBO.setApp(bucketName);
      storageBO.setFileName(fileName);
      storageBO.setFileSuffix(suffix);
      storageBO.setFileType(contentType);
      storageBO.setBucketName(bucketName);
      storageService.uploadFile(storageBO, inputStream);
    }
  }

  @ApiOperation(value = "文件上传页面")
  @GetMapping(value = {"/upload"})
  public ModelAndView upload() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("upload");
    modelAndView.addObject("attributeName", "");
    return modelAndView;
  }
}
