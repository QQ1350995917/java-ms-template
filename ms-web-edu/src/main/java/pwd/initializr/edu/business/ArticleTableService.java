package pwd.initializr.edu.business;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import pwd.initializr.edu.business.bo.ArticleTableBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * <h2>服务层逻辑接口封装：ArticleTableEntity信息服务接口</h2>
 * date 2021-02-04 16:07
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
public interface ArticleTableService {

  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2021-02-04 16:07
   *
   * @param id 主键
   * @param able 启用/禁用
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  Integer ableById(Long id, EntityAble able);

  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2021-02-04 16:07
   *
   * @param ids 主键
   * @param able 启用/禁用
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  Integer ableById(Set<Long> ids, EntityAble able);

  /**
   * <h2>通过主键删除数据</h2>
   * date 2021-02-04 16:07
   *
   * @param id 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  Integer deleteById(Long id);

  /**
   * <h2>通过主键批量删除数据</h2>
   * date 2021-02-04 16:07
   *
   * @param ids 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  Integer deleteById(Set<Long> ids);

  /**
   * <h2>新增数据</h2>
   * date 2021-02-04 16:07
   *
   * @param bo 业务对象
   * @return java.lang.Long 数据ID
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  Long insert(ArticleTableBO bo);

  /**
   * <h2>新增数据（批量操作）</h2>
   * date 2021-02-04 16:07
   *
   * @param bos 业务对象
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  void insert(List<ArticleTableBO> bos);

  /**
   * <h2>新增或者替换数据</h2>
   * date 2021-02-04 16:07
   *
   * @param bo 业务对象
   * @return java.lang.Long 数据ID
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  Long insertOrReplace(ArticleTableBO bo);

  /**
   * <h2>新增或者替换数据（批量操作）</h2>
   * date 2021-02-04 16:07
   *
   * @param bos 业务对象
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  void insertOrReplace(List<ArticleTableBO> bos);

  /**
   * <h2>根据条件查询多条数据</h2>
   * date 2021-02-04 16:07
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param pageIndex 页码
   * @param pageSize 容量
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.edu.business.bo.ArticleTableBO>
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  PageableQueryResult<ArticleTableBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize);

  /**
   * <h2>通过ID查询单条数据</h2>
   * date 2021-02-04 16:07
   *
   * @param id 主键
   * @return pwd.initializr.edu.business.bo.ArticleTableBO 业务对象
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  ArticleTableBO queryById(Long id);

  /**
   * <h2>根据ID更新数据</h2>
   * date 2021-02-04 16:07
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2021-02-04 16:07
   */
  Integer updateById(ArticleTableBO bo);
}
