package pwd.initializr.access.business.admin;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.access.business.admin.bo.AccessMenuBO;
import pwd.initializr.access.persistence.dao.AccessMenuDao;
import pwd.initializr.access.persistence.entity.AccessMenuEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：AccessMenuEntity信息服务接口</h2>
 * date 2021-08-08 16:34
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("AccessMenuService")
public class AccessMenuServiceImpl implements AccessMenuService {

  @Resource
  private AccessMenuDao dao;

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
  public Long insert(AccessMenuBO bo) {
    AccessMenuEntity entity = this.convertAccessMenuBO2AccessMenuEntityForCreate(bo);
    this.dao.insert(entity);
    return entity.getMid();
  }

  @Override
  public void insert(List<AccessMenuBO> bos) {
    List<AccessMenuEntity> entities = bos.stream()
      .map(this::convertAccessMenuBO2AccessMenuEntityForCreate).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(AccessMenuBO bo) {AccessMenuEntity entity = this.convertAccessMenuBO2AccessMenuEntityForCreate(bo);
    this.dao.insertOrReplace(entity);
    return entity.getMid();
  }

  @Override
  public void insertOrReplace(List<AccessMenuBO> bos) {
    List<AccessMenuEntity> entities = bos.stream()
      .map(this::convertAccessMenuBO2AccessMenuEntityForCreate).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<AccessMenuBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<AccessMenuBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<AccessMenuEntity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      AccessMenuBO resultItem = new AccessMenuBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public AccessMenuBO queryById(Long id) {
    AccessMenuEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    AccessMenuBO bo = new AccessMenuBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(AccessMenuBO bo){AccessMenuEntity entity = new AccessMenuEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public AccessMenuEntity convertAccessMenuBO2AccessMenuEntityForCreate(AccessMenuBO bo){
    AccessMenuEntity entity = new AccessMenuEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    entity.setVersion(0L);
    return entity;
  }
}
