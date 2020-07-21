package pwd.initializr.account.persistence.dao;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.account.persistence.entity.AdminAccountEntity;

/**
 * (AdminAccountEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
@Mapper
public interface AdminAccountDao {

  /**
   * 查询指定行数据
   *
   * @param adminAccountEntity 实例对象
   * @return 对象列表
   */
  List<AdminAccountEntity> countAllByCondition(
      @Param("adminAccountEntity") AdminAccountEntity adminAccountEntity);

  /**
   * 通过主键删除数据
   *
   * @param ids 主键
   * @return 影响行数
   */
  int deleteByIds(List<Long> ids);

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
   * @param adminAccountEntity 实例对象
   * @return 影响行数
   */
  int insert(AdminAccountEntity adminAccountEntity);

  /**
   * 查询指定行数据
   *
   * @param adminAccountEntity 实例对象
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<AdminAccountEntity> queryAllByCondition(
      @Param("adminAccountEntity") AdminAccountEntity adminAccountEntity,
      @Param("offset") int offset, @Param("limit") int limit);

  /**
   * 查询指定行数据
   *
   * @param uid 用户ID
   * @return 对象列表
   */
  List<AdminAccountEntity> queryAllByUid(@Param("uid") Long uid);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  AdminAccountEntity queryById(Long id);

  /**
   * 登录查询：通过登录名和密码查询单条数据
   *
   * @param loginName 登录账号
   * @param loginPwd 登录密码
   * @return 实例对象
   */
  AdminAccountEntity queryByLoginNameAndPwd(@Param("loginName") String loginName,@Param("loginPwd") String loginPwd);

  /**
   * 修改数据
   *
   * @param adminAccountEntity 实例对象
   * @return 影响行数
   */
  int update(AdminAccountEntity adminAccountEntity);

}