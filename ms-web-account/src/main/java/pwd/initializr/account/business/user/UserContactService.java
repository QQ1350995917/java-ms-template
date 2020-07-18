package pwd.initializr.account.business.user;

import java.util.List;
import pwd.initializr.account.persistence.entity.UserContactEntity;

/**
 * (UserContactEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
public interface UserContactService {

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
   * @param userContact 实例对象
   * @return 实例对象
   */
  UserContactEntity insert(UserContactEntity userContact);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<UserContactEntity> queryAllByLimit(int offset, int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserContactEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param userContact 实例对象
   * @return 实例对象
   */
  UserContactEntity update(UserContactEntity userContact);

}