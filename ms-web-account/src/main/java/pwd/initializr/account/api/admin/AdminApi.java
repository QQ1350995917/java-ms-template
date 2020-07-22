package pwd.initializr.account.api.admin;

import pwd.initializr.account.api.admin.vo.AdminVO;
import pwd.initializr.account.api.admin.vo.CreateAdminInput;
import pwd.initializr.common.web.api.vo.PageInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-26 8:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AdminApi {

  void create(CreateAdminInput input);

  void disable(Long id);

  void enable(Long id);

  void list(PageInput pageInput, AdminVO adminVO);



  void modify(Long id, CreateAdminInput input);

}
