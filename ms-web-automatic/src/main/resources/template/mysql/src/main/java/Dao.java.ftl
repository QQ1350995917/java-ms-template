package ${projectPackage}.persistence.dao;

import java.util.LinkedHashSet;
import java.util.List;
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
   * <h2>通过ID启用/禁用数据</h2>
   * date ${projectCreateDate}
   *
   * @param id 主键
   * @param able 业务数据
   * @return java.lang.Integer 受影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer ableById(@Param("id") Long id, @Param("able") Integer able);

  /**
   * <h2>通过ID批量启用/禁用数据</h2>
   * date ${projectCreateDate}
   *
   * @param ids 主键集合
   * @return java.lang.Integer 受影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer ableByIds(List<Long> ids, int able);

  /**
   * <h2>统计指定条件下命中的数据行数</h2>
   * date ${projectCreateDate}
   *
   * @param scopes 查询条件集合
   * @return java.lang.Long 总行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Long countAllByCondition(@Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes);

  /**
   * <h2>通过主键删除数据</h2>
   * date ${projectCreateDate}
   *
   * @param id 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer deleteById(@Param("id") Long id);

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
   * <h2>查询指定行数据</h2>
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
  List<${className}Entity> queryAllByCondition(
    @Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes,
    @Param("sorts") LinkedHashSet<? extends SortEntity> sorts,
    @Param("offset") Long offset, @Param("limit") Long limit);

}
