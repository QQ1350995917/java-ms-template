package pwd.initializr.account.persistence.dao;

import java.util.LinkedHashSet;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.UserUserEntity;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;
import pwd.initializr.common.web.persistence.entity.SortEntity;

/**
 * (UserUserEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
public interface UserUserDao {


  /**
   * <h2>通过ID启用/禁用数据</h2>
   * date 2020-07-27 00:01
   *
   * @param id 主键
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableById(Long id, int able);

  /**
   * <h2>通过ID启用/禁用数据</h2>
   * date 2020-07-27 00:01
   *
   * @param ids 主键集合
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByIds(@Param("ids") List<Long> ids, @Param("able") int able);

  /**
   * <h2>通过实体作为筛选条件统计</h2>
   * date 2020-08-13 15:09
   *
   * @param scopes 查询条件
   * @return java.lang.Long
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Long countAllByCondition(
      @Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Long id);

  /**
   * 通过主键删除数据
   *
   * @param ids 主键
   * @return 影响行数
   */
  int deleteByIds(@Param("ids") List<Long> ids);

  /**
   * 新增数据
   *
   * @param userUser 实例对象
   * @return 影响行数
   */
  int insert(UserUserEntity userUser);


  /**
   * <h2>通过实体作为筛选条件查询</h2>
   * date 2020-08-13 15:08
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param offset 偏移条件
   * @param limit 容量条件
   * @return java.lang.Long
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<UserUserEntity> queryAllByCondition(
      @Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes,
      @Param("sorts") LinkedHashSet<? extends SortEntity> sorts,
      @Param("offset") Long offset, @Param("limit") Long limit
  );

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserUserEntity queryById(Long id);


  /**
   * <h2>通过主键批量查询用户</h2>
   * date 2021-06-14 14:18
   *
   * @param ids 用户主键集合
   * @return int
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<UserUserEntity> queryByIds(@Param("ids") Long[] ids);


  /**
   * 修改数据
   *
   * @param userUser 实例对象
   * @return 影响行数
   */
  int update(UserUserEntity userUser);

}
