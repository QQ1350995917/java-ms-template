package pwd.initializr.account.business.admin;

import java.util.List;
import pwd.initializr.account.business.admin.bo.AdminContactBO;
import pwd.initializr.account.persistence.entity.AdminContactEntity;

/**
 * (AdminContactEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:18:58
 */
public interface AdminContactService {

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  Integer deleteById(Long id);

  /**
   * 新增数据
   *
   * @param adminContactBO 实例对象
   * @return 实例对象
   */
  AdminContactBO insert(AdminContactBO adminContactBO);

  Integer insertByBatch(List<AdminContactBO> adminContacts);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminContactBO> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminContactBO queryById(Long id);

  /**
   * <h2>根据用户ID查询联系方式</h2>
   * date 2021-07-30 22:26
   *
   * @param uid 用户ID
   * @return java.util.List<pwd.initializr.account.persistence.entity.AdminContactEntity>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<AdminContactBO> queryByUid(Long uid);

  /**
   * <h2>TODO what you want to do</h2>
   * date 2021-07-31 22:52
   *
   * @param uids
   * @return java.util.List<pwd.initializr.account.business.admin.bo.AdminContactBO>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<AdminContactBO> queryByUids(List<Long> uids);

  /**
   * 修改数据
   *
   * @param adminContactBO 实例对象
   * @return 实例对象
   */
  AdminContactBO update(AdminContactBO adminContactBO);

}