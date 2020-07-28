package pwd.initializr.account.business.user;


import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * (UserAccountEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:35:18
 */
public interface UserAccountService {

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
   * @param userAccountBO 实例对象
   * @return 实例对象
   */
  UserAccountBO insert(UserAccountBO userAccountBO);

  /**
   * <h2>用户登录</h2>
   * date 2020-07-21 22:08
   *
   * @param loginName 登录名
   * @param loginPwd 登录密码
   * @return pwd.initializr.account.business.admin.bo.AdminUserBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  UserAccountBO queryByNameAndPwd(String loginName, String loginPwd);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  ObjectList<UserAccountBO> queryAllByCondition(UserAccountBO userAccountBO, Long offset,
      Long limit);

  ObjectList<UserAccountBO> queryAllByUserId(Long userId);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserAccountBO queryById(Long id);

  /**
   * 修改数据
   *
   * @param userAccountBO 实例对象
   * @return 实例对象
   */
  UserAccountBO update(UserAccountBO userAccountBO);

}