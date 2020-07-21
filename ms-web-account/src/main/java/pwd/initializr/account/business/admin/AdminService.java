package pwd.initializr.account.business.admin;

import java.util.LinkedHashSet;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * (AdminUserBO)表服务接口
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
   * @param adminUserBO 实例对象
   * @return 实例对象
   */
  AdminUserBO insert(AdminUserBO adminUserBO);

  /**
   * 查询多条数据
   *
   * @return 对象列表
   */
  ObjectList<AdminUserBO> queryByCondition(AdminUserBO adminUserBO, LinkedHashSet<String> orderBys,
      Integer pageIndex, Integer pageSize);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminUserBO queryById(Integer id);

  /**
   * 修改数据
   *
   * @param adminUserBO 实例对象
   * @return 实例对象
   */
  Integer update(AdminUserBO adminUserBO);

}
