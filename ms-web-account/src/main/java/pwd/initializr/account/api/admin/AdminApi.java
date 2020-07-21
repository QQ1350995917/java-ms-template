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

  /**
   * <h2>用户账号登录接口</h2>
   * <p>1：如发生异常，则抛出</p>
   * <p>2：根据查询对象，判断可用状态</p>
   * <p>3：根据查询对象，不可用对象则响应对应信息</p>
   * <p>4：可用状态对象，响应是否需要修改密码</p>
   * <p>5：可用状态对象，响应是否异地登录</p>
   * <p>6：可用状态对象，响应token，过期时间等信息</p>
   * date 2020-07-21 23:21
   *
   * @param loginName 登录名
   * @param loginPwd 登录密码
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void loginByNameAndPwd(String loginName, String loginPwd);

  void modify(Long id, CreateAdminInput input);

}
