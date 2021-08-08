package pwd.initializr.access.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.access.business.bo.GroupUserBO;
import pwd.initializr.access.persistence.dao.GroupUserDao;
import pwd.initializr.access.persistence.entity.GroupUserEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：GroupUserEntity信息服务接口</h2>
 * date 2021-08-08 16:34
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("GroupUserService")
public class GroupUserServiceImpl implements GroupUserService {

  @Resource
  private GroupUserDao dao;

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
  public Long insert(GroupUserBO bo) {
    GroupUserEntity entity = this.convertGroupUserBO2GroupUserEntityForCreate(bo);
    this.dao.insert(entity);
    return entity.getUid();
  }

  @Override
  public void insert(List<GroupUserBO> bos) {
    List<GroupUserEntity> entities = bos.stream()
      .map(this::convertGroupUserBO2GroupUserEntityForCreate).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(GroupUserBO bo) {GroupUserEntity entity = this.convertGroupUserBO2GroupUserEntityForCreate(bo);
    this.dao.insertOrReplace(entity);
    return entity.getUid();
  }

  @Override
  public void insertOrReplace(List<GroupUserBO> bos) {
    List<GroupUserEntity> entities = bos.stream()
      .map(this::convertGroupUserBO2GroupUserEntityForCreate).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<GroupUserBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<GroupUserBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<GroupUserEntity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      GroupUserBO resultItem = new GroupUserBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public GroupUserBO queryById(Long id) {
    GroupUserEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    GroupUserBO bo = new GroupUserBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(GroupUserBO bo){GroupUserEntity entity = new GroupUserEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public GroupUserEntity convertGroupUserBO2GroupUserEntityForCreate(GroupUserBO bo){
    GroupUserEntity entity = new GroupUserEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    entity.setVersion(0L);
    return entity;
  }
}
