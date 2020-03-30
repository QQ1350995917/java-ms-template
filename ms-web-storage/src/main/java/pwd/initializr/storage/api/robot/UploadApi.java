package pwd.initializr.storage.api.robot;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.storage.api.robot.vo.UploadInput;

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
public interface UploadApi {

  void upload(HttpServletRequest request);

  void upload(@RequestParam("file") MultipartFile file,UploadInput input);
}
