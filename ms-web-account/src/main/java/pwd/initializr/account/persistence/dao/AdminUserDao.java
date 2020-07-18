package pwd.initializr.account.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.AdminUserEntity;

/**
 * (AdminUserEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:12:53
 */
public interface AdminUserDao {

  /**
   * 通过实体作为筛选条件查询
   *
   * @param adminUserEntity 实例对象
   * @return 行数
   */
  List<AdminUserEntity> countAllByCondition(
      @Param("adminUserEntity") AdminUserEntity adminUserEntity);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Long id);

  /**
   * 通过ID查询单条数据
   *
   * @param ids 主键
   * @return 实例对象
   */
  AdminUserEntity deleteByIds(List<Long> ids);

  /**
   * 新增数据
   *
   * @param adminUserEntity 实例对象
   * @return 影响行数
   */
  int insert(AdminUserEntity adminUserEntity);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param adminUserEntity 实例对象
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminUserEntity> queryAllByCondition(
      @Param("adminUserEntity") AdminUserEntity adminUserEntity,
      @Param("offset") int offset, @Param("limit") int limit);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminUserEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param adminUserEntity 实例对象
   * @return 影响行数
   */
  int update(AdminUserEntity adminUserEntity);

}