package pwd.initializr.account.business.admin.service;

import java.util.List;
import pwd.initializr.account.persistence.entity.AdminEntity;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2020-04-25 20:16:10
 */
public interface AdminService {

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

  /**
   * 新增数据
   *
   * @param admin 实例对象
   * @return 实例对象
   */
  AdminEntity insert(AdminEntity admin);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminEntity> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminEntity queryById(Integer id);

  /**
   * 修改数据
   *
   * @param admin 实例对象
   * @return 实例对象
   */
  AdminEntity update(AdminEntity admin);

}