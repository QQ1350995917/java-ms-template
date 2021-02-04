package ${projectPackage}.persistence.dao;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ${projectPackage}.persistence.entity.${className}Entity;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;
import pwd.initializr.common.web.persistence.entity.SortEntity;

/**
 * <h2>${tableName}数据表操作</h2>
 * date ${projectCreateDate}
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since ${projectVersion}
 */
@Mapper
public interface ${className}Dao {

  /**
   * <h2>通过主键启用/禁用数据</h2>
   * date ${projectCreateDate}
   *
   * @param id 主键
   * @param able 业务数据
   * @param date 更新时间
   * @return java.lang.Integer 受影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer ableById(@Param("id") Long id, @Param("able") Integer able, Date date);

  /**
   * <h2>通过主键批量启用/禁用数据</h2>
   * date ${projectCreateDate}
   *
   * @param ids 主键集合
   * @param able 业务数据
   * @param date 更新时间
   * @return java.lang.Integer 受影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer ableByIds(Set<Long> ids, int able, Date date);

  /**
   * <h2>统计指定条件下命中的数据行数</h2>
   * date ${projectCreateDate}
   *
   * @param scopes 查询条件集合
   * @return java.lang.Long 总行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Long countByCondition(@Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes);

  /**
   * <h2>通过主键删除数据</h2>
   * date ${projectCreateDate}
   *
   * @param id 主键
   * @param date 更新时间
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer deleteById(@Param("id") Long id, Date date);

  /**
   * <h2>通过主键批量删除数据</h2>
   * date ${projectCreateDate}
   *
   * @param ids 主键集合
   * @param date 更新时间
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer deleteByIds(@Param("ids") Set<Long> ids, Date date);

  /**
   * <h2>新增数据</h2>
   * date ${projectCreateDate}
   *
   * @param entity 实例对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer insert(@Param("entity") ${className}Entity entity);

  /**
   * <h2>新增数据（批量新增）</h2>
   * date ${projectCreateDate}
   *
   * @param entities 实例对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer insertByBatch(@Param("entities") List<${className}Entity> entities);

  /**
   * <h2>新增或者替换数据</h2>
   * date ${projectCreateDate}
   *
   * @param entity 实例对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer insertOrReplace(@Param("entity") ${className}Entity entity);

  /**
   * <h2>新增或者替换数据（批量新增或者替换）</h2>
   * date ${projectCreateDate}
   *
   * @param entities 实例对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer insertOrReplaceByBatch(@Param("entities") List<${className}Entity> entities);

  /**
   * <h2>在指定的条件下查询数据</h2>
   * date ${projectCreateDate}
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  List<${className}Entity> queryByCondition(
    @Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes,
    @Param("sorts") LinkedHashSet<? extends SortEntity> sorts,
    @Param("offset") Long offset, @Param("limit") Long limit);

  /**
   * <h2>根据主键进行查询</h2>
   * date ${projectCreateDate}
   *
   * @param id 主键
   * @return ${className}Entity
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  ${className}Entity queryById(@Param("id") Long id);


  /**
   * <h2>通过主键更新数据</h2>
   * date ${projectCreateDate}
   *
   * @param entity 实例对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer updateById(@Param("entity") ${className}Entity entity);
}
