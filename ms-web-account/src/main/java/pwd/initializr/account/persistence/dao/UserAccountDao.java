package pwd.initializr.account.persistence.dao;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.UserAccountEntity;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;
import pwd.initializr.common.web.persistence.entity.SortEntity;

/**
 * (UserAccountEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:35:17
 */
public interface UserAccountDao {


  /**
   * <h2>通过ID启用/禁用数据</h2>
   * date 2020-07-27 00:01
   *
   * @param id 主键
   * @param uid 外键
   * @param able 业务数据
   * @param updateTime 更新时间
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableById(@Param("id") Long id,@Param("uid") Long uid, @Param("able") Integer able, @Param("updateTime") Date updateTime);

  /**
   * <h2>通过ID启用/禁用数据</h2>
   * date 2020-07-27 00:01
   *
   * @param ids 主键集合
   * @param updateTime 更新时间
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByIds(@Param("ids") List<Long> ids, @Param("able") int able, @Param("updateTime") Date updateTime);

  /**
   * <h2>通过用户ID启用/禁用数据</h2>
   * date 2020-07-27 00:06
   *
   * @param uid 用户ID
   * @param able 业务数据
   * @param updateTime 更新时间
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByUserId(@Param("uid") Long uid,@Param("able") Integer able, @Param("updateTime") Date updateTime);

  /**
   * <h2>通过用户ID启用/禁用数据</h2>
   * date 2020-07-27 00:06
   *
   * @param ids 用户ID集合
   * @param updateTime 更新时间
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByUserIds(@Param("ids") List<Long> ids, @Param("able") int able, @Param("updateTime") Date updateTime);

  /**
   * <h2>通过实体作为筛选条件统计</h2>
   * date 2020-07-27 22:07
   *
   * @param scopes 查询条件
   * @return java.lang.Long
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Long countAllByCondition(@Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @param updateTime 更新时间
   * @return 影响行数
   */
  int deleteById(@Param("id") Long id,@Param("uid") Long uid, @Param("updateTime") Date updateTime);

  /**
   * 通过主键删除数据
   *
   * @param ids 主键
   * @param updateTime 更新时间
   * @return 影响行数
   */
  int deleteByIds(@Param("ids") List<Long> ids, @Param("updateTime") Date updateTime);

  /**
   *
   * @param uids
   * @param updateTime 更新时间
   * @return
   */
  int deleteByUserIds(@Param("uids") List<Long> uids, @Param("updateTime") Date updateTime);

  /**
   * 检查账户名是否存在
   * @param loginName 账户名
   * @return
   */
  UserAccountEntity existLoginName(String loginName);

  /**
   * 新增数据
   *
   * @param userAccount 实例对象
   * @return 影响行数
   */
  int insert(UserAccountEntity userAccount);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<UserAccountEntity> queryAllByCondition(
      @Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes,
      @Param("sorts") LinkedHashSet<? extends SortEntity> sorts,
      @Param("offset") Long offset, @Param("limit") Long limit);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param uid 用户ID
   * @return 对象列表
   */
  List<UserAccountEntity> queryAllByUid(@Param("uid") Long uid);

  /**
   * 通过ID查询单条数据
   *
   * @param id 账号主键
   * @param uid 用户外键
   * @return 实例对象
   */
  UserAccountEntity queryById(@Param("id") Long id,@Param("uid") Long uid);

  /**
   * 登录查询：通过登录名查询单条数据
   *
   * @param loginName 登录账号
   * @return 实例对象
   */
  UserAccountEntity queryByLoginNameAndPwd(@Param("loginName") String loginName);

  /**
   * <h2>修改密码</h2>
   * date 2020-08-13 14:26
   *
   * @param id 账户ID
   * @param uid 用户ID
   * @param loginPwd 登录密码
   * @param pwdSalt 密码盐
   * @param updateTime 更新时间
   * @return int
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  int updateLoginPwd(
      @Param("id") Long id,
      @Param("uid") Long uid,
      @Param("loginPwd") String loginPwd,
      @Param("pwdSalt") String pwdSalt,
      @Param("updateTime") Date updateTime);

}
