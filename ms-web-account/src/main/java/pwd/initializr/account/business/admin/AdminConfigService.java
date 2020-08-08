package pwd.initializr.account.business.admin;

import java.util.List;
import pwd.initializr.account.business.admin.bo.AdminConfigBO;

/**
 * (AdminConfigEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:19:33
 */
public interface AdminConfigService {

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
   * @param adminConfigBO 实例对象
   * @return 实例对象
   */
  AdminConfigBO insert(AdminConfigBO adminConfigBO);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminConfigBO> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminConfigBO queryById(Long id);

  /**
   * <h2>通过业务可以查询单挑数据</h2>
   * date 2020-07-25 15:50
   *
   * @param key 唯一业务主键
   * @return pwd.initializr.account.persistence.entity.AdminConfigEntity
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  AdminConfigBO queryByKey(String key);

  /**
   * 修改数据
   *
   * @param adminConfigBO 实例对象
   * @return 实例对象
   */
  AdminConfigBO update(AdminConfigBO adminConfigBO);

}
