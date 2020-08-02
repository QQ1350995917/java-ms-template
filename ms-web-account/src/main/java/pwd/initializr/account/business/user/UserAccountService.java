package pwd.initializr.account.business.user;


import java.util.List;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * (UserAccountEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:35:18
 */
public interface UserAccountService {


  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-07-28 16:09
   *
   * @param ids 主键
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean ableById(List<Long> ids, EntityAble able);


  /**
   * <h2>通过外键启用/禁用账户</h2>
   * date 2020-07-28 16:09
   *
   * @param userIds 用户外键
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean ableByUserId(List<Long> userIds, EntityAble able);

  /**
   * <h2>通过外键启用/禁用账户</h2>
   * date 2020-07-28 16:21
   *
   * @param userId 用户外键
   * @param able 启用/禁用
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean ableByUserId(Long userId, EntityAble able);


  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  Boolean deleteById(Long id);

  Boolean deleteById(List<Long> ids);

  /**
   * 新增数据
   *
   * @param userAccountBO 实例对象
   * @return 实例对象
   */
  UserAccountBO insert(UserAccountBO userAccountBO);

  /**
   * 查询多条数据
   *
   * @param pageIndex 查询起始位置
   * @param pageSize 查询条数
   * @return 对象列表
   */
  PageableQueryResult<UserAccountBO> queryAllByCondition(UserAccountBO userAccountBO, Long pageIndex,
      Long pageSize);

  PageableQueryResult<UserAccountBO> queryAllByUserId(Long userId);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserAccountBO queryById(Long id);

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
   * 修改数据
   *
   * @param userAccountBO 实例对象
   * @return 实例对象
   */
  Integer update(UserAccountBO userAccountBO);

}
