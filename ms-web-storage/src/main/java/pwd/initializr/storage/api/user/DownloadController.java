package pwd.initializr.storage.api.user;

import io.swagger.annotations.Api;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.common.mw.minio.MinIOClient;
import pwd.initializr.common.web.api.ApiController;
import pwd.initializr.storage.api.user.vo.DownloadInput;
import pwd.initializr.storage.business.QueryService;
import pwd.initializr.storage.business.bo.Storage;

/**
 * pwd.initializr.storage.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-26 18:13
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文件下载",
    value = "文件下载Api",
    description = "文件下载API"
)
@Controller(value = "downloadApi")
@RequestMapping(value = "/api/user/download")
public class DownloadController extends ApiController implements DownloadApi {

  @Autowired
  private QueryService queryService;
  @Autowired
  private MinIOClient minIOClient;

  @Override
  public void download(DownloadInput input) {
    Storage oneByUrl = queryService.findOneByUrl(input.getUrl());
    getResponse().setContentType("application/octet-stream");
    getResponse().addHeader("Content-Disposition", "attachment;fileName=" + oneByUrl.getName());
    OutputStream outputStream = getResponse().getOutputStream();
    outputStream.write();
    outputStream.flush();
  }
}
