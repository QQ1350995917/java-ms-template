package pwd.initializr.monitor.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;
import pwd.initializr.monitor.business.bo.HostDiskBO;
import pwd.initializr.monitor.persistence.dao.HostDiskDao;
import pwd.initializr.monitor.persistence.entity.HostDiskEntity;

/**
 * <h2>服务层逻辑接口封装：HostDiskEntity信息服务接口</h2>
 * date 2020-10-23 11:58
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("HostDiskService")
public class HostDiskServiceImpl implements HostDiskService {

  @Resource
  private HostDiskDao dao;

  @Override
  public Integer ableById(Long id, EntityAble able) {
    return this.dao.ableById(id, able.getNumber());
  }

  @Override
  public Integer ableById(Set<Long> ids, EntityAble able) {
    return this.dao.ableByIds(ids, able.getNumber());
  }

  @Override
  public Integer deleteById(Long id) {
    return this.dao.deleteById(id);
  }

  @Override
  public Integer deleteById(Set<Long> ids) {
    return this.dao.deleteByIds(ids);
  }

  @Override
  public void insert(HostDiskBO bo) {
    HostDiskEntity entity = new HostDiskEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insert(entity);
  }

  @Override
  public void insertOrReplace(HostDiskBO bo) {
    HostDiskEntity entity = new HostDiskEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insertOrReplace(entity);
  }

  @Override
  public void insertOrReplace(List<HostDiskBO> bos) {
    LinkedList<HostDiskEntity> hostDiskEntities = new LinkedList<>();
    for (HostDiskBO bo : bos) {
      HostDiskEntity entity = new HostDiskEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setAble(EntityAble.DISABLE.getNumber());
      entity.setDel(EntityDel.NO.getNumber());
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      hostDiskEntities.add(entity);
    }
    this.dao.insertOrReplaceBatch(hostDiskEntities);
  }

  @Override
  public PageableQueryResult<HostDiskBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<HostDiskBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<HostDiskEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      HostDiskBO resultItem = new HostDiskBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public HostDiskBO queryById(Long id) {
    HostDiskEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    HostDiskBO bo = new HostDiskBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(HostDiskBO bo){
    HostDiskEntity entity = new HostDiskEntity();
    BeanUtils.copyProperties(bo, entity);
    return this.dao.updateById(entity);
  }

}
