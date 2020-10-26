package ${projectPackage}.business;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import ${projectPackage}.business.bo.${className}BO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * <h2>服务层逻辑接口封装：${className}Entity信息服务接口</h2>
 * date ${projectCreateDate}
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since ${projectVersion}
 */
public interface ${className}Service {

  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date ${projectCreateDate}
   *
   * @param id 主键
   * @param able 启用/禁用
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectVersion}
   */
  Integer ableById(Long id, EntityAble able);

  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date ${projectCreateDate}
   *
   * @param ids 主键
   * @param able 启用/禁用
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  Integer ableById(Set<Long> ids, EntityAble able);

  /**
   * <h2>通过主键删除数据</h2>
   * date ${projectCreateDate}
   *
   * @param id 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  Integer deleteById(Long id);

  /**
   * <h2>通过主键批量删除数据</h2>
   * date ${projectCreateDate}
   *
   * @param ids 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  Integer deleteById(Set<Long> ids);

  /**
   * <h2>新增数据</h2>
   * date ${projectCreateDate}
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  void insert(${className}BO bo);

  /**
   * <h2>新增数据（批量操作）</h2>
   * date ${projectCreateDate}
   *
   * @param bos 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  void insert(List<${className}BO> bos);

  /**
   * <h2>新增或者替换数据</h2>
   * date ${projectCreateDate}
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  void insertOrReplace(${className}BO bo);

  /**
   * <h2>新增或者替换数据（批量操作）</h2>
   * date ${projectCreateDate}
   *
   * @param bos 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  void insertOrReplace(List<${className}BO> bos);

  /**
   * <h2>根据条件查询多条数据</h2>
   * date ${projectCreateDate}
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param pageIndex 页码
   * @param pageSize 容量
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<${projectPackage}.business.bo.${className}BO>
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  PageableQueryResult<${className}BO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize);

  /**
   * <h2>通过ID查询单条数据</h2>
   * date ${projectCreateDate}
   *
   * @param id 主键
   * @return ${projectPackage}.business.bo.${className}BO 业务对象
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  ${className}BO queryById(Long id);

  /**
   * <h2>根据ID更新数据</h2>
   * date ${projectCreateDate}
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since ${projectCreateDate}
   */
  Integer updateById(${className}BO bo);
}
