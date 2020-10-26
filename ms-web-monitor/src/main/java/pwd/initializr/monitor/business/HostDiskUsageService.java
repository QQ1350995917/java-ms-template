package pwd.initializr.monitor.business;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.monitor.business.bo.HostDiskUsageBO;

/**
 * <h2>服务层逻辑接口封装：HostDiskUsageEntity信息服务接口</h2>
 * date 2020-10-23 11:58
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
public interface HostDiskUsageService {

  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-10-23 11:58
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
   * date 2020-10-23 11:58
   *
   * @param ids 主键
   * @param able 启用/禁用
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-10-23 11:58
   */
  Integer ableById(Set<Long> ids, EntityAble able);

  /**
   * <h2>通过主键删除数据</h2>
   * date 2020-10-23 11:58
   *
   * @param id 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-10-23 11:58
   */
  Integer deleteById(Long id);

  /**
   * <h2>通过主键批量删除数据</h2>
   * date 2020-10-23 11:58
   *
   * @param ids 主键
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-10-23 11:58
   */
  Integer deleteById(Set<Long> ids);

  /**
   * <h2>新增数据</h2>
   * date 2020-10-23 11:58
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-10-23 11:58
   */
  void insert(HostDiskUsageBO bo);

  /**
   * <h2>新增数据</h2>
   * date 2020-10-26 11:27
   *
   * @param bos 业务对象
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void insert(List<HostDiskUsageBO> bos);

  /**
   * <h2>根据条件查询多条数据</h2>
   * date 2020-10-23 11:58
   *
   * @param scopes 查询条件
   * @param sorts 排序条件
   * @param pageIndex 页码
   * @param pageSize 容量
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.monitor.business.bo.HostDiskUsageBO>
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-10-23 11:58
   */
  PageableQueryResult<HostDiskUsageBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize);

  /**
   * <h2>通过ID查询单条数据</h2>
   * date 2020-10-23 11:58
   *
   * @param id 主键
   * @return pwd.initializr.monitor.business.bo.HostDiskUsageBO 业务对象
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-10-23 11:58
   */
  HostDiskUsageBO queryById(Long id);

  /**
   * <h2>根据ID更新数据</h2>
   * date 2020-10-23 11:58
   *
   * @param bo 业务对象
   * @return java.lang.Integer 影响行数
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 2020-10-23 11:58
   */
  Integer updateById(HostDiskUsageBO bo);
}
