package pwd.initializr.email.business;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import pwd.initializr.email.business.bo.EmailBoxBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * <h2>服务层逻辑接口封装：EmailBoxEntity信息服务接口</h2>
 * date 2020-12-14 15:55
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
public interface EmailBoxService {

  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-12-14 15:55
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
   * date 2020-12-14 15:55
   *
   * @param ids 主键
   * @param able 启用/禁用
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  Integer ableById(Set<Long> ids, EntityAble able);

  /**
   * <h2>通过主键删除数据</h2>
   * date 2020-12-14 15:55
   *
   * @param id 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  Integer deleteById(Long id);

  /**
   * <h2>通过主键批量删除数据</h2>
   * date 2020-12-14 15:55
   *
   * @param ids 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  Integer deleteById(Set<Long> ids);

  /**
   * <h2>新增数据</h2>
   * date 2020-12-14 15:55
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insert(EmailBoxBO bo);

  /**
   * <h2>新增数据（批量操作）</h2>
   * date 2020-12-14 15:55
   *
   * @param bos 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insert(Set<EmailBoxBO> bos);

  /**
   * <h2>新增数据（批量操作）</h2>
   * date 2020-12-14 15:55
   *
   * @param emailId 外键
   * @param bos 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insert(Long emailId,Set<EmailBoxBO> bos);

  /**
   * <h2>新增或者替换数据</h2>
   * date 2020-12-14 15:55
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insertOrReplace(EmailBoxBO bo);

  /**
   * <h2>新增或者替换数据（批量操作）</h2>
   * date 2020-12-14 15:55
   *
   * @param bos 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insertOrReplace(List<EmailBoxBO> bos);

  /**
   * <h2>根据条件查询多条数据</h2>
   * date 2020-12-14 15:55
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param pageIndex 页码
   * @param pageSize 容量
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.email.business.bo.EmailBoxBO>
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  PageableQueryResult<EmailBoxBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize);

  /**
   * <h2>通过ID查询单条数据</h2>
   * date 2020-12-14 15:55
   *
   * @param id 主键
   * @return pwd.initializr.email.business.bo.EmailBoxBO 业务对象
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  EmailBoxBO queryById(Long id);

  /**
   * <h2>根据邮件ID查询</h2>
   * date 2020-12-15 14:56
   *
   * @param emailId 邮件ID
   * @return pwd.initializr.email.business.bo.EmailBoxBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<EmailBoxBO> queryByEmailId(Long emailId);

  /**
   * <h2>根据ID更新数据</h2>
   * date 2020-12-14 15:55
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  Integer updateById(EmailBoxBO bo);
}
