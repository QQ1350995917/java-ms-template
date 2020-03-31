package pwd.initializr.storage.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.ApiController;
import pwd.initializr.storage.api.user.vo.DownloadInput;
import pwd.initializr.storage.business.QueryService;
import pwd.initializr.storage.business.bo.StorageBO;

/**
 * pwd.initializr.storage.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-26 18:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文件下载",
    value = "文件下载Api",
    description = "文件下载API"
)
@RestController(value = "downloadApi")
@RequestMapping(value = "/api/user/download")
public class DownloadController extends ApiController implements DownloadApi {

  @Autowired
  private QueryService queryService;

  @ApiOperation(value = "文件下载")
  @GetMapping(value = {""})
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
}
