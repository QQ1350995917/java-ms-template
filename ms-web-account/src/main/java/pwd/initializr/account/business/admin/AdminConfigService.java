package pwd.initializr.account.business.admin;

import java.util.List;
import pwd.initializr.account.persistence.entity.AdminConfigEntity;

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
  boolean deleteById(Long id);

  /**
   * 新增数据
   *
   * @param adminConfig 实例对象
   * @return 实例对象
   */
  AdminConfigEntity insert(AdminConfigEntity adminConfig);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminConfigEntity> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminConfigEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param adminConfig 实例对象
   * @return 实例对象
   */
  AdminConfigEntity update(AdminConfigEntity adminConfig);

}