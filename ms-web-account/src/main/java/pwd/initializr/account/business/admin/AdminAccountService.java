package pwd.initializr.account.business.admin;


import java.util.List;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * (AdminAccountEntityEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
public interface AdminAccountService {

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Long id);

  /**
   * <h2>根据用户ID删除数据</h2>
   * date 2020-07-26 23:31
   *
   * @param userId 用户ID
   * @return boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  boolean deleteByUserId(Long userId);

  /**
   * <h2>根据用户ID删除数据</h2>
   * date 2020-07-26 23:43
   *
   * @param userIds 用户ID集合
   * @return boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  boolean deleteByUserId(List<Long> userIds);

  /**
   * 新增数据
   *
   * @param adminAccountBO 实例对象
   * @return 实例对象
   */
  AdminAccountBO insert(AdminAccountBO adminAccountBO);

  /**
   * <h2>根据条件查询多条数据</h2>
   * date 2020-07-26 21:31
   *
   * @param adminAccountBO 查询条件
   * @param pageIndex 页码
   * @param pageSize 页面容量
   * @return @return pwd.initializr.common.web.business.bo.ObjectList<pwd.initializr.account.business.admin.bo.AdminAccountBO>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  ObjectList<AdminAccountBO> queryAllByCondition(AdminAccountBO adminAccountBO, Long pageIndex,
      Long pageSize);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminAccountBO queryById(Long id);

  /**
   * <h2>session创建接口：管理员通过账号密码登录</h2>
   * <p>1：使用既定的加密方式对密码进行加密</p>
   * <p>2：使用登录名和加密的密码进行查找</p>
   * <p>3：找不到对应的账号则抛出异常</p>
   * <p>4：判断对应的账号的可用状态，可用则生成响应的session和个人信息保存在redis，不可用则跳过</p>
   * <p>5：返回账号对象</p>
   * date 2020-07-21 22:14
   *
   * @param loginName 登录名
   * @param loginPwd 登录密码
   * @return pwd.initializr.account.business.admin.bo.AdminAccountBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  AdminAccountBO queryByNameAndPwd(String loginName, String loginPwd);

  /**
   * <h2>TODO session创建接口：管理员通过手机号和短信验证码登录</h2>
   * date 2020-07-22 16:28
   *
   * @param phoneNumber 手机号码
   * @param smsCode 短信验证码
   * @return pwd.initializr.account.business.admin.bo.AdminAccountBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  AdminAccountBO queryByPhoneNumberAndSmsCode(String phoneNumber, String smsCode);

  /**
   * 修改数据
   *
   * @param adminAccountBO 实例对象
   * @return 实例对象
   */
  Integer update(AdminAccountBO adminAccountBO);

}
