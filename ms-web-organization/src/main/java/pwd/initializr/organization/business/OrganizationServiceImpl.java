package pwd.initializr.organization.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.organization.business.bo.OrganizationBO;
import pwd.initializr.organization.persistence.dao.OrganizationDao;
import pwd.initializr.organization.persistence.entity.OrganizationEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：OrganizationEntity信息服务接口</h2>
 * date 2021-02-20 22:30
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("OrganizationService")
public class OrganizationServiceImpl implements OrganizationService {

  @Resource
  private OrganizationDao dao;

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
  public Long insert(OrganizationBO bo) {
    OrganizationEntity entity = this.convertOrganizationBO2OrganizationEntity(bo);
    this.dao.insert(entity);
    return entity.getId();
  }

  @Override
  public void insert(List<OrganizationBO> bos) {
    List<OrganizationEntity> entities = bos.stream()
      .map(this::convertOrganizationBO2OrganizationEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(OrganizationBO bo) {
    OrganizationEntity entity = this.convertOrganizationBO2OrganizationEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getId();
  }

  @Override
  public void insertOrReplace(List<OrganizationBO> bos) {
    List<OrganizationEntity> entities = bos.stream()
      .map(this::convertOrganizationBO2OrganizationEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<OrganizationBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<OrganizationBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<OrganizationEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      OrganizationBO resultItem = new OrganizationBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public OrganizationBO queryById(Long id) {
    OrganizationEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    OrganizationBO bo = new OrganizationBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(OrganizationBO bo){
    OrganizationEntity entity = new OrganizationEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public OrganizationEntity convertOrganizationBO2OrganizationEntity(OrganizationBO bo){
    OrganizationEntity entity = new OrganizationEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
