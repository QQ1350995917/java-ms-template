package pwd.initializr.account.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.UserConfigEntity;

/**
 * (UserConfigEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:35:22
 */
public interface UserConfigDao {

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Long id);

  /**
   * 新增数据
   *
   * @param userConfig 实例对象
   * @return 影响行数
   */
  int insert(UserConfigEntity userConfig);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param userConfig 实例对象
   * @return 对象列表
   */
  List<UserConfigEntity> queryAll(UserConfigEntity userConfig);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<UserConfigEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserConfigEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param userConfig 实例对象
   * @return 影响行数
   */
  int update(UserConfigEntity userConfig);

}