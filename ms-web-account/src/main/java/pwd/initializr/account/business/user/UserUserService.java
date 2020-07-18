package pwd.initializr.account.business.user;

import java.util.List;
import pwd.initializr.account.persistence.entity.UserUserEntity;

/**
 * (UserUserEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
public interface UserUserService {

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
   * @param userUser 实例对象
   * @return 实例对象
   */
  UserUserEntity insert(UserUserEntity userUser);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<UserUserEntity> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserUserEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param userUser 实例对象
   * @return 实例对象
   */
  UserUserEntity update(UserUserEntity userUser);

}