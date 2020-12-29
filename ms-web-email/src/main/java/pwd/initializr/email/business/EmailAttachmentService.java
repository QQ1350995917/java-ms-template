package pwd.initializr.email.business;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import pwd.initializr.email.business.bo.EmailAttachmentBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * <h2>服务层逻辑接口封装：EmailAttachmentEntity信息服务接口</h2>
 * date 2020-12-14 15:55
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
public interface EmailAttachmentService {

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
  void insert(EmailAttachmentBO bo);

  /**
   * <h2>新增数据（批量操作）</h2>
   * date 2020-12-14 15:55
   *
   * @param bos 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insert(Set<EmailAttachmentBO> bos);

  /**
   * <h2>新增数据（批量操作）</h2>
   * date 2020-12-14 15:55
   *
   * @param emailId 邮件ID
   * @param bos 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insert(Long emailId,Set<EmailAttachmentBO> bos);

  /**
   * <h2>新增或者替换数据</h2>
   * date 2020-12-14 15:55
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insertOrReplace(EmailAttachmentBO bo);

  /**
   * <h2>新增或者替换数据（批量操作）</h2>
   * date 2020-12-14 15:55
   *
   * @param bos 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  void insertOrReplace(List<EmailAttachmentBO> bos);

  /**
   * <h2>根据条件查询多条数据</h2>
   * date 2020-12-14 15:55
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param pageIndex 页码
   * @param pageSize 容量
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.email.business.bo.EmailAttachmentBO>
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  PageableQueryResult<EmailAttachmentBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize);

  /**
   * <h2>通过ID查询单条数据</h2>
   * date 2020-12-14 15:55
   *
   * @param id 主键
   * @return pwd.initializr.email.business.bo.EmailAttachmentBO 业务对象
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  EmailAttachmentBO queryById(Long id);

  /**
   * <h2>通过emailId查询数据</h2>
   * date 2020-12-15 15:10
   *
   * @param emailId emailID
   * @return pwd.initializr.email.business.bo.EmailAttachmentBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<EmailAttachmentBO> queryByEmailId(Long emailId);

  /**
   * <h2>根据ID更新数据</h2>
   * date 2020-12-14 15:55
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-12-14 15:55
   */
  Integer updateById(EmailAttachmentBO bo);
}