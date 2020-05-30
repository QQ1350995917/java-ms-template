package pwd.initializr.account.persistence.dao;

import java.util.LinkedHashSet;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.AdminEntity;

/**
 * (AdminEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-25 16:18:47
 */
public interface AdminDao {


  Integer countAll(@Param("adminEntity") AdminEntity adminEntity);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

  /**
   * 新增数据
   *
   * @param adminEntity 实例对象
   * @return 影响行数
   */
  int insert(AdminEntity adminEntity);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminEntity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param adminEntity 实例对象
   * @param orderBys 排序字符串
   * @param offset 分页
   * @param pageSize 页面容量
   * @return 对象列表
   */
  List<AdminEntity> queryByCondition(@Param("adminEntity") AdminEntity adminEntity,
      @Param("orderBys") LinkedHashSet<String> orderBys, @Param("offset") Integer offset,
      @Param("pageSize") Integer pageSize);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminEntity queryById(Integer id);

  /**
   * 通过ID查询单条数据
   *
   * @param loginName 主键
   * @param loginPassword 主键
   * @return 实例对象
   */
  AdminEntity queryByLoginNameAndLoginPassword(@Param("loginName") String loginName,
      @Param("loginPassword") String loginPassword);

  /**
   * 修改数据
   *
   * @param adminEntity 实例对象
   * @return 影响行数
   */
  int update(AdminEntity adminEntity);

}
