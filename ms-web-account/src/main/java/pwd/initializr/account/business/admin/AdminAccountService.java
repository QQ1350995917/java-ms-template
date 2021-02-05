package pwd.initializr.account.business.admin;


import java.util.LinkedHashSet;
import java.util.List;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.persistence.entity.AccountType;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * (AdminAccountEntityEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
public interface AdminAccountService {


  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-07-28 16:09
   *
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableById(Long id, Long uid, EntityAble able);


  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-07-28 16:09
   *
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByIds(List<Long> ids, EntityAble able);


  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-07-28 16:09
   *
   * @param userIds 用户外键
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByUserIds(List<Long> userIds, EntityAble able);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @param uid 外键
   * @return 是否成功
   */
  Integer deleteById(Long id, Long uid);

  /**
   * <h2>根据用户ID删除数据</h2>
   * date 2020-07-26 23:31
   *
   * @param userId 用户ID
   * @return Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer deleteByUserId(Long userId);

  /**
   * <h2>根据用户ID删除数据</h2>
   * date 2020-07-26 23:43
   *
   * @param userIds 用户ID集合
   * @return Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer deleteByUserId(List<Long> userIds);

  /**
   * <h2>根据用户ID查询可用的账号个数</h2>
   * date 2020-09-07 11:13
   *
   * @param userId 用户ID
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer enabledAccountNum(Long userId);

  /**
   * <h2>根据用户ID查询未删除账号个数</h2>
   * date 2020-09-07 23:45
   *
   * @param userId 用户ID
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer existedAccountNum(Long userId);

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
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param pageIndex 页码
   * @param pageSize 容量
   * @return @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.account.business.admin.bo.AdminAccountBO>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<AdminAccountBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts,
      Long pageIndex, Long pageSize);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminAccountBO queryById(Long id, Long uid);

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
   * <h2>通过用户ID查询数据</h2>
   * date 2020-07-27 13:58
   *
   * @param userId 用户ID
   * @return java.util.List<pwd.initializr.account.business.admin.bo.AdminAccountBO>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<AdminAccountBO> queryByUserId(Long userId);

  /**
   * 修改数据
   *
   * @param id 账户ID
   * @return 实例对象
   */
  Integer updateLoginPwd(Long id, Long uid, String previousPwd, String currentPwd);

  /**
   * <h2>根据类型，查询用户名下的该类型的账号</h2>
   * date 2021-02-06 00:04
   *
   * @param uid 用户ID
   * @param accountType 账户类型
   * @return pwd.initializr.account.business.admin.bo.AdminAccountBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  AdminAccountBO queryByAccountTypeUnderUserId(Long uid,AccountType accountType);

}
