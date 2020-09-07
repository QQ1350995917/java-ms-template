package pwd.initializr.account.persistence.dao;


import java.util.LinkedHashSet;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.AdminAccountEntity;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;
import pwd.initializr.common.web.persistence.entity.SortEntity;

/**
 * (AdminAccountEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
@Mapper
public interface AdminAccountDao {

  /**
   * <h2>通过ID启用/禁用数据</h2>
   * date 2020-07-27 00:01
   *
   * @param id 主键
   * @param uid 外键
   * @param able 业务数据
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableById(@Param("id") Long id,@Param("uid") Long uid, @Param("able") Integer able);

  /**
   * <h2>通过ID启用/禁用数据</h2>
   * date 2020-07-27 00:01
   *
   * @param ids 主键集合
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByIds(List<Long> ids, int able);

  /**
   * <h2>通过用户ID启用/禁用数据</h2>
   * date 2020-07-27 00:06
   *
   * @param id 用户ID
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByUserId(Long id);

  /**
   * <h2>通过用户ID启用/禁用数据</h2>
   * date 2020-07-27 00:06
   *
   * @param ids 用户ID集合
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByUserIds(List<Long> ids, int able);

  /**
   * 查询指定行数据
   *
   * @param scopes 查询条件
   * @return 对象列表
   */
  Long countAllByCondition(@Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @param uid 外键
   * @return 影响行数
   */
  int deleteById(@Param("id") Long id,@Param("uid") Long uid);

  /**
   * <h2>通过用户外键删除数据</h2>
   * date 2020-07-26 23:41
   *
   * @param userId 用户外键集
   * @return int
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  int deleteByUserId(Long userId);

  /**
   * <h2>通过用户外键删除数据</h2>
   * date 2020-07-26 23:41
   *
   * @param userIds 用户外键集合
   * @return int
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  int deleteByUserIds(@Param("userIds") List<Long> userIds);

  /**
   * <h2>根据用户外键查询可用账号数量</h2>
   * date 2020-09-07 11:15
   *
   * @param userId 用户ID
   * @return int
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  int enabledAccountNum(@Param("userId") Long userId);

  /**
   * <h2>根据用户外键查询未删除账号数量</h2>
   * date 2020-09-07 23:47
   *
   * @param userId 用户ID
   * @return int
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  int existedAccountNum(@Param("userId") Long userId);

  /**
   * 新增数据
   *
   * @param adminAccountEntity 实例对象
   * @return 影响行数
   */
  int insert(AdminAccountEntity adminAccountEntity);

  /**
   * 查询指定行数据
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminAccountEntity> queryAllByCondition(
      @Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes,
      @Param("sorts") LinkedHashSet<? extends SortEntity> sorts,
      @Param("offset") Long offset, @Param("limit") Long limit);

  /**
   * 查询指定行数据
   *
   * @param uid 用户ID
   * @return 对象列表
   */
  List<AdminAccountEntity> queryAllByUid(@Param("uid") Long uid);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminAccountEntity queryById(@Param("id") Long id,@Param("uid") Long uid);

  /**
   * 登录查询：通过登录名和密码查询单条数据
   *
   * @param loginName 登录账号
   * @param loginPwd 登录密码
   * @return 实例对象
   */
  AdminAccountEntity queryByLoginNameAndPwd(@Param("loginName") String loginName,
      @Param("loginPwd") String loginPwd);


  /**
   * <h2>修改密码</h2>
   * date 2020-08-13 14:26
   *
   * @param id 账户ID
   * @param uid 用户ID
   * @param previousPwd 旧版本密码
   * @param currentPwd 新版本密码
   * @return int
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  int updateLoginPwd(@Param("id") Long id,@Param("uid") Long uid,@Param("previousPwd") String previousPwd, @Param("currentPwd") String currentPwd);


}
