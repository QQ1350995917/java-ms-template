package pwd.initializr.account.business.user;

import java.util.List;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * (UserUserEntity)表服务接口
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
public interface UserUserService {


  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-07-28 16:09
   *
   * @param ids 主键
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableById(List<Long> ids, EntityAble able);

  /**
   * <h2>通过主键启用/禁用账户</h2>
   * date 2020-07-28 16:24
   *
   * @param id 主键
   * @return java.lang.Integer
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer ableById(Long id, EntityAble able);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  Integer deleteById(Long id);

  Integer deleteById(List<Long> ids);

  /**
   * 新增数据
   *
   * @param userUserBO 实例对象
   * @return 实例对象
   */
  UserUserBO insert(UserUserBO userUserBO);

  /**
   * 查询多条数据
   *
   * @param pageIndex 查询起始位置
   * @param pageSize 查询条数
   * @return 对象列表
   */
  PageableQueryResult<UserUserBO> queryAllByCondition(UserUserBO userUserBO, Long pageIndex,
      Long pageSize);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserUserBO queryById(Long id);

  /**
   * 修改数据
   *
   * @param userUserBO 实例对象
   * @return 实例对象
   */
  Integer update(UserUserBO userUserBO);

}
