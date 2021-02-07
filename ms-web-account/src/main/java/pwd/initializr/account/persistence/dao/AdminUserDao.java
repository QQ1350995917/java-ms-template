package pwd.initializr.account.persistence.dao;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.AdminUserEntity;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;
import pwd.initializr.common.web.persistence.entity.SortEntity;

/**
 * (AdminUserEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:12:53
 */
public interface AdminUserDao {

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
   * @param able 启用/禁用
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableByIds(@Param("ids") List<Long> ids, @Param("able") int able);

  /**
   * 通过实体作为筛选条件统计数量
   *
   * @param scopes 查询条件
   * @return 统计数量
   */
  Long countAllByCondition(@Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  Integer deleteById(Long id);

  /**
   * <h2>通过ID删除数据</h2>
   * date 2020-07-26 23:50
   *
   * @param ids id集合
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer deleteByIds(@Param("ids") List<Long> ids);

  /**
   * 新增数据
   *
   * @param adminUserEntity 实例对象
   * @return 影响行数
   */
  void insert(AdminUserEntity adminUserEntity);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminUserEntity> queryAllByCondition(
      @Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes,
      @Param("sorts") LinkedHashSet<? extends SortEntity> sorts,
      @Param("offset") Long offset, @Param("limit") Long limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminUserEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param adminUserEntity 实例对象
   * @return 影响行数
   */
  Integer update(AdminUserEntity adminUserEntity);

}
