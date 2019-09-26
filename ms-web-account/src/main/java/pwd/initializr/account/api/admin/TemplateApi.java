package pwd.initializr.account.api.admin;

import pwd.initializr.account.api.admin.vo.UserTemplateUploadInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-26 18:07
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface TemplateApi {

  void uploadUserTemplate(UserTemplateUploadInput input);

  void downloadUserTemplate();
}
