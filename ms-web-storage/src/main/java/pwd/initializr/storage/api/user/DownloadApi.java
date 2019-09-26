package pwd.initializr.storage.api.user;

import pwd.initializr.storage.api.user.vo.DownloadInput;

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
public interface DownloadApi {

  void download(DownloadInput input);
}
