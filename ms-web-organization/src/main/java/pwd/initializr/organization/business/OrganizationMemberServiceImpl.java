package pwd.initializr.organization.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.organization.business.bo.OrganizationMemberBO;
import pwd.initializr.organization.persistence.dao.OrganizationMemberDao;
import pwd.initializr.organization.persistence.entity.OrganizationMemberEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：OrganizationMemberEntity信息服务接口</h2>
 * date 2021-02-22 21:33
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("OrganizationMemberService")
public class OrganizationMemberServiceImpl implements OrganizationMemberService {

  @Resource
  private OrganizationMemberDao dao;

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
  public Long insert(OrganizationMemberBO bo) {
    OrganizationMemberEntity entity = this.convertOrganizationMemberBO2OrganizationMemberEntity(bo);
    this.dao.insert(entity);
    return entity.getMemId();
  }

  @Override
  public void insert(List<OrganizationMemberBO> bos) {
    List<OrganizationMemberEntity> entities = bos.stream()
      .map(this::convertOrganizationMemberBO2OrganizationMemberEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(OrganizationMemberBO bo) {
    OrganizationMemberEntity entity = this.convertOrganizationMemberBO2OrganizationMemberEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getOrgId();
  }

  @Override
  public void insertOrReplace(List<OrganizationMemberBO> bos) {
    List<OrganizationMemberEntity> entities = bos.stream()
      .map(this::convertOrganizationMemberBO2OrganizationMemberEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<OrganizationMemberBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<OrganizationMemberBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<OrganizationMemberEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      OrganizationMemberBO resultItem = new OrganizationMemberBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public OrganizationMemberBO queryById(Long id) {
    OrganizationMemberEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    OrganizationMemberBO bo = new OrganizationMemberBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(OrganizationMemberBO bo){
    OrganizationMemberEntity entity = new OrganizationMemberEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public OrganizationMemberEntity convertOrganizationMemberBO2OrganizationMemberEntity(OrganizationMemberBO bo){
    OrganizationMemberEntity entity = new OrganizationMemberEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
