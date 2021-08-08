package pwd.initializr.access.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.access.business.bo.AccessStorageBO;
import pwd.initializr.access.persistence.dao.AccessStorageDao;
import pwd.initializr.access.persistence.entity.AccessStorageEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：AccessStorageEntity信息服务接口</h2>
 * date 2021-08-08 16:34
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("AccessStorageService")
public class AccessStorageServiceImpl implements AccessStorageService {

  @Resource
  private AccessStorageDao dao;

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
  public Long insert(AccessStorageBO bo) {
    AccessStorageEntity entity = this.convertAccessStorageBO2AccessStorageEntityForCreate(bo);
    this.dao.insert(entity);
    return entity.getSid();
  }

  @Override
  public void insert(List<AccessStorageBO> bos) {
    List<AccessStorageEntity> entities = bos.stream()
      .map(this::convertAccessStorageBO2AccessStorageEntityForCreate).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(AccessStorageBO bo) {AccessStorageEntity entity = this.convertAccessStorageBO2AccessStorageEntityForCreate(bo);
    this.dao.insertOrReplace(entity);
    return entity.getSid();
  }

  @Override
  public void insertOrReplace(List<AccessStorageBO> bos) {
    List<AccessStorageEntity> entities = bos.stream()
      .map(this::convertAccessStorageBO2AccessStorageEntityForCreate).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<AccessStorageBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<AccessStorageBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<AccessStorageEntity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      AccessStorageBO resultItem = new AccessStorageBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public AccessStorageBO queryById(Long id) {
    AccessStorageEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    AccessStorageBO bo = new AccessStorageBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(AccessStorageBO bo){AccessStorageEntity entity = new AccessStorageEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public AccessStorageEntity convertAccessStorageBO2AccessStorageEntityForCreate(AccessStorageBO bo){
    AccessStorageEntity entity = new AccessStorageEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    entity.setVersion(0L);
    return entity;
  }
}
