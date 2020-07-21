package pwd.initializr.account.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.AdminContactEntity;
import pwd.initializr.account.persistence.entity.UserContactEntity;


/**
 * (UserContactEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
public interface UserContactDao {

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
   * @param userContact 实例对象
   * @return 影响行数
   */
  int insert(UserContactEntity userContact);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param userContactEntity 实例对象
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<UserContactEntity> queryAllByCondition(@Param("adminContactEntity") UserContactEntity userContactEntity,@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param userContactEntity 实例对象
   * @return 对象列表
   */
  List<UserContactEntity> countAllByCondition(@Param("adminContactEntity") UserContactEntity userContactEntity);


  /**
   * 查询指定行数据
   *
   * @param uid 用户ID
   * @return 对象列表
   */
  List<UserContactEntity> queryAllByUid(@Param("uid") Long uid);

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
   * @return 影响行数
   */
  int update(UserContactEntity userContact);

}