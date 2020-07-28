package pwd.initializr.account.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.UserUserEntity;

/**
 * (UserUserEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
public interface UserUserDao {

  /**
   * 通过实体作为筛选条件查询
   *
   * @param userUserEntity 实例对象
   * @return 行数
   */
  List<UserUserEntity> countAllByCondition(@Param("userUserEntity") UserUserEntity userUserEntity);

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
   * @param userUser 实例对象
   * @return 影响行数
   */
  int insert(UserUserEntity userUser);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param userUserEntity 实例对象
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<UserUserEntity> queryAllByCondition(@Param("userUserEntity") UserUserEntity userUserEntity,
      @Param("offset") Long offset, @Param("limit") Long limit);

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
   * @return 影响行数
   */
  int update(UserUserEntity userUser);

}