package pwd.initializr.access.business.admin;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.access.business.admin.bo.RoleAccessBO;
import pwd.initializr.access.persistence.dao.RoleAccessDao;
import pwd.initializr.access.persistence.entity.RoleAccessEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：RoleAccessEntity信息服务接口</h2>
 * date 2021-08-08 16:34
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("RoleAccessService")
public class RoleAccessServiceImpl implements RoleAccessService {

  @Resource
  private RoleAccessDao dao;

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
  public Long insert(RoleAccessBO bo) {
    RoleAccessEntity entity = this.convertRoleAccessBO2RoleAccessEntityForCreate(bo);
    this.dao.insert(entity);
    return entity.getAid();
  }

  @Override
  public void insert(List<RoleAccessBO> bos) {
    List<RoleAccessEntity> entities = bos.stream()
      .map(this::convertRoleAccessBO2RoleAccessEntityForCreate).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(RoleAccessBO bo) {RoleAccessEntity entity = this.convertRoleAccessBO2RoleAccessEntityForCreate(bo);
    this.dao.insertOrReplace(entity);
    return entity.getAid();
  }

  @Override
  public void insertOrReplace(List<RoleAccessBO> bos) {
    List<RoleAccessEntity> entities = bos.stream()
      .map(this::convertRoleAccessBO2RoleAccessEntityForCreate).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<RoleAccessBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<RoleAccessBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<RoleAccessEntity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      RoleAccessBO resultItem = new RoleAccessBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public RoleAccessBO queryById(Long id) {
    RoleAccessEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    RoleAccessBO bo = new RoleAccessBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(RoleAccessBO bo){RoleAccessEntity entity = new RoleAccessEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public RoleAccessEntity convertRoleAccessBO2RoleAccessEntityForCreate(RoleAccessBO bo){
    RoleAccessEntity entity = new RoleAccessEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    entity.setVersion(0L);
    return entity;
  }
}
