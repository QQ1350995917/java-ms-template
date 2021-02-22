package pwd.initializr.access.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.access.business.bo.RoleMenuOperationBO;
import pwd.initializr.access.persistence.dao.RoleMenuOperationDao;
import pwd.initializr.access.persistence.entity.RoleMenuOperationEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：RoleMenuOperationEntity信息服务接口</h2>
 * date 2021-02-22 22:48
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("RoleMenuOperationService")
public class RoleMenuOperationServiceImpl implements RoleMenuOperationService {

  @Resource
  private RoleMenuOperationDao dao;

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
  public Long insert(RoleMenuOperationBO bo) {
    RoleMenuOperationEntity entity = this.convertRoleMenuOperationBO2RoleMenuOperationEntity(bo);
    this.dao.insert(entity);
    return entity.getRid();
  }

  @Override
  public void insert(List<RoleMenuOperationBO> bos) {
    List<RoleMenuOperationEntity> entities = bos.stream()
      .map(this::convertRoleMenuOperationBO2RoleMenuOperationEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(RoleMenuOperationBO bo) {
    RoleMenuOperationEntity entity = this.convertRoleMenuOperationBO2RoleMenuOperationEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getRid();
  }

  @Override
  public void insertOrReplace(List<RoleMenuOperationBO> bos) {
    List<RoleMenuOperationEntity> entities = bos.stream()
      .map(this::convertRoleMenuOperationBO2RoleMenuOperationEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<RoleMenuOperationBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<RoleMenuOperationBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<RoleMenuOperationEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      RoleMenuOperationBO resultItem = new RoleMenuOperationBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public RoleMenuOperationBO queryById(Long id) {
    RoleMenuOperationEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    RoleMenuOperationBO bo = new RoleMenuOperationBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(RoleMenuOperationBO bo){
    RoleMenuOperationEntity entity = new RoleMenuOperationEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public RoleMenuOperationEntity convertRoleMenuOperationBO2RoleMenuOperationEntity(RoleMenuOperationBO bo){
    RoleMenuOperationEntity entity = new RoleMenuOperationEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
