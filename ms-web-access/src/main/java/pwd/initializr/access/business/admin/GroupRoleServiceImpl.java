package pwd.initializr.access.business.admin;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.access.business.admin.bo.GroupRoleBO;
import pwd.initializr.access.persistence.dao.GroupRoleDao;
import pwd.initializr.access.persistence.entity.GroupRoleEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：GroupRoleEntity信息服务接口</h2>
 * date 2021-08-08 16:34
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("GroupRoleService")
public class GroupRoleServiceImpl implements GroupRoleService {

  @Resource
  private GroupRoleDao dao;

  @Override
  public Integer ableById(Long id, EntityAble able) {
    return this.dao.ableById(id, able.getNumber(), new Date());
  }

  @Override
  public Integer ableById(Set<Long> ids, EntityAble able) {
    return this.dao.ableByIds(ids, able.getNumber(), new Date());
  }

  @Override
  public Integer deleteById(Long id) {
    return this.dao.deleteById(id, new Date());
  }

  @Override
  public Integer deleteById(Set<Long> ids) {
    return this.dao.deleteByIds(ids, new Date());
  }

  @Override
  public Long insert(GroupRoleBO bo) {
    GroupRoleEntity entity = this.convertGroupRoleBO2GroupRoleEntityForCreate(bo);
    this.dao.insert(entity);
    return entity.getRid();
  }

  @Override
  public void insert(List<GroupRoleBO> bos) {
    List<GroupRoleEntity> entities = bos.stream()
      .map(this::convertGroupRoleBO2GroupRoleEntityForCreate).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(GroupRoleBO bo) {GroupRoleEntity entity = this.convertGroupRoleBO2GroupRoleEntityForCreate(bo);
    this.dao.insertOrReplace(entity);
    return entity.getRid();
  }

  @Override
  public void insertOrReplace(List<GroupRoleBO> bos) {
    List<GroupRoleEntity> entities = bos.stream()
      .map(this::convertGroupRoleBO2GroupRoleEntityForCreate).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<GroupRoleBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<GroupRoleBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<GroupRoleEntity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      GroupRoleBO resultItem = new GroupRoleBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public GroupRoleBO queryById(Long id) {
    GroupRoleEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    GroupRoleBO bo = new GroupRoleBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(GroupRoleBO bo){GroupRoleEntity entity = new GroupRoleEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public GroupRoleEntity convertGroupRoleBO2GroupRoleEntityForCreate(GroupRoleBO bo){
    GroupRoleEntity entity = new GroupRoleEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    entity.setVersion(0L);
    return entity;
  }
}
