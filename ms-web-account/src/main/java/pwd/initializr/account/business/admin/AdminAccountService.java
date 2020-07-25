package pwd.initializr.account.business.admin;


import java.util.List;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.persistence.entity.AdminAccountEntity;

/**
 * (AdminAccountEntityEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
public interface AdminAccountService {

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
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Long id);

  /**
   * 新增数据
   *
   * @param adminAccountBO 实例对象
   * @return 实例对象
   */
  AdminAccountBO insert(AdminAccountBO adminAccountBO);


  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminAccountEntity> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminAccountEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param adminAccount 实例对象
   * @return 实例对象
   */
  AdminAccountEntity update(AdminAccountEntity adminAccount);

}
