package pwd.initializr.account.business.user;


import java.util.LinkedHashSet;
import java.util.List;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
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
  Integer ableById(List<Long> ids, EntityAble able);


  /**
   * <h2>通过外键启用/禁用账户</h2>
   * date 2020-07-28 16:09
   *
   * @param userIds 用户外键
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByUserId(List<Long> userIds, EntityAble able);

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
  Integer ableByUserId(Long userId, EntityAble able);


  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @param uid 外键
   * @return 是否成功
   */
  Integer deleteById(Long id,Long uid);

  Integer deleteById(List<Long> ids);

  /**
   * 通过外键删除数据
   * @param uids 外键
   * @return
   */
  Integer deleteByUserId(List<Long> uids);

  /**
   * <h2>检查账号名是否存在</h2>
   * date 2020-08-08 11:07
   *
   * @param loginName 账号名
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean existLoginName(String loginName);

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
  PageableQueryResult<UserAccountBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes, LinkedHashSet<SortBO> sorts,
      Long pageIndex, Long pageSize);

  /**
   * <h2>通过用户ID查询账号信息</h2>
   * date 2020-08-08 12:50
   *
   * @param userId 用户ID
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.account.business.user.bo.UserAccountBO>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<UserAccountBO> queryAllByUserId(Long userId);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserAccountBO queryById(Long id,Long uid);

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
   * @param id 实例对象
   * @return 实例对象
   */
  Integer update(Long id, Long uid, String previousPwd, String currentPwd);

}
