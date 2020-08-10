package pwd.initializr.account.business.admin;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * (AdminUserEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:12:54
 */
public interface AdminUserService {

  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-07-28 16:09
   *
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableById(List<Long> ids, EntityAble able);


  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  Integer deleteById(Long id);

  /**
   * <h2>通过主键删除数据</h2>
   * date 2020-07-26 23:50
   *
   * @return boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer deleteById(List<Long> ids);

  /**
   * <h2>新增数据:同时新增账号</h2>
   * date 2020-07-26 22:34
   *
   * @param adminUserBO 用户实例
   * @return pwd.initializr.account.business.admin.bo.AdminUserBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  AdminUserBO insert(AdminUserBO adminUserBO);

  /**
   * <h2>根据条件查询多条数据</h2>
   * date 2020-07-26 21:08
   *
   * @param scopeBOS 查询条件
   * @param sortBOS 排序条件
   * @param pageIndex 页码
   * @param pageSize 页面容量
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.account.persistence.entity.AdminUserEntity>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<AdminUserBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopeBOS,
      LinkedHashSet<SortBO> sortBOS, Long pageIndex,
      Long pageSize);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminUserBO queryById(Long id);

  /**
   * 修改数据
   *
   * @param adminUserBO 实例对象
   * @return 实例对象
   */
  Integer update(AdminUserBO adminUserBO);


}
