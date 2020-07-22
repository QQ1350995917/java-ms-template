package pwd.initializr.account.business.admin;


import java.util.List;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.persistence.entity.AdminAccountEntity;

/**
 * (AdminAccountEntityEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
public interface AdminAccountService {

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
   * @param adminAccount 实例对象
   * @return 实例对象
   */
  AdminAccountEntity insert(AdminAccountEntity adminAccount);


  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminAccountEntity> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminAccountEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param adminAccount 实例对象
   * @return 实例对象
   */
  AdminAccountEntity update(AdminAccountEntity adminAccount);

}
