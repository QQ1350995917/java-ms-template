package pwd.initializr.account.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.AdminContactEntity;

/**
 * (AdminContactEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:18:58
 */
public interface AdminContactDao {

  /**
   * 通过实体作为筛选条件查询
   *
   * @param adminContactEntity 实例对象
   * @return 对象列表
   */
  List<AdminContactEntity> countAllByCondition(
      @Param("adminContactEntity") AdminContactEntity adminContactEntity);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Long id);

  /**
   * 通过主键删除数据
   *
   * @param ids 主键
   * @return 影响行数
   */
  int deleteByIds(List<Long> ids);

  /**
   * 新增数据
   *
   * @param adminContactEntity 实例对象
   * @return 影响行数
   */
  int insert(AdminContactEntity adminContactEntity);

  int insertByBatch(@Param("entities") List<AdminContactEntity> entities);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param adminContactEntity 实例对象
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminContactEntity> queryAllByCondition(
      @Param("adminContactEntity") AdminContactEntity adminContactEntity,
      @Param("offset") int offset, @Param("limit") int limit);

  /**
   * 查询指定行数据
   *
   * @param uid 用户ID
   * @return 对象列表
   */
  List<AdminContactEntity> queryAllByUid(@Param("uid") Long uid);

  List<AdminContactEntity> queryAllByUids(@Param("uids") List<Long> uids);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminContactEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param adminContactEntity 实例对象
   * @return 影响行数
   */
  int update(AdminContactEntity adminContactEntity);

}