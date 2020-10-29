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
import pwd.initializr.monitor.business.bo.HostDiskStatBO;
import pwd.initializr.monitor.persistence.dao.HostDiskStatDao;
import pwd.initializr.monitor.persistence.entity.HostDiskStatEntity;

/**
 * <h2>服务层逻辑接口封装：HostDiskStatEntity信息服务接口</h2>
 * date 2020-10-29 11:44
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("HostDiskStatService")
public class HostDiskStatServiceImpl implements HostDiskStatService {

  @Resource
  private HostDiskStatDao dao;

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
  public void insert(HostDiskStatBO bo) {
    HostDiskStatEntity entity = new HostDiskStatEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insert(entity);
  }

  @Override
  public void insert(List<HostDiskStatBO> bos) {
    LinkedList<HostDiskStatEntity> entities = new LinkedList<>();
    for (HostDiskStatBO bo : bos) {
      HostDiskStatEntity entity = new HostDiskStatEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setAble(EntityAble.DISABLE.getNumber());
      entity.setDel(EntityDel.NO.getNumber());
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      entities.add(entity);
    }
    this.dao.insertByBatch(entities);
  }

  @Override
  public void insertOrReplace(HostDiskStatBO bo) {
    HostDiskStatEntity entity = new HostDiskStatEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insertOrReplace(entity);
  }

  @Override
  public void insertOrReplace(List<HostDiskStatBO> bos) {
    LinkedList<HostDiskStatEntity> entities = new LinkedList<>();
    for (HostDiskStatBO bo : bos) {
      HostDiskStatEntity entity = new HostDiskStatEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setAble(EntityAble.DISABLE.getNumber());
      entity.setDel(EntityDel.NO.getNumber());
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      entities.add(entity);
    }
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<HostDiskStatBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<HostDiskStatBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<HostDiskStatEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      HostDiskStatBO resultItem = new HostDiskStatBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public HostDiskStatBO queryById(Long id) {
    HostDiskStatEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    HostDiskStatBO bo = new HostDiskStatBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(HostDiskStatBO bo){
    HostDiskStatEntity entity = new HostDiskStatEntity();
    BeanUtils.copyProperties(bo, entity);
    return this.dao.updateById(entity);
  }

}
