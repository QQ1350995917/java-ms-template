package pwd.initializr.account.business.admin;

import java.util.List;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.AdminBO;
import pwd.initializr.account.persistence.entity.AdminEntity;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * (AdminBO)表服务接口
 *
 * @author makejava
 * @since 2020-04-25 20:16:10
 */
@Service
public interface AdminService {

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  Integer deleteById(Integer id);

  /**
   * 新增数据
   *
   * @param adminBO 实例对象
   * @return 实例对象
   */
  AdminBO insert(AdminBO adminBO);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  ObjectList<AdminBO> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminBO queryById(Integer id);

  /**
   * 修改数据
   *
   * @param adminBO 实例对象
   * @return 实例对象
   */
  Integer update(AdminBO adminBO);

}