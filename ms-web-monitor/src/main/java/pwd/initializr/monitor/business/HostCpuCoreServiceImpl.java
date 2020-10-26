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
import pwd.initializr.monitor.business.bo.HostCpuCoreBO;
import pwd.initializr.monitor.persistence.dao.HostCpuCoreDao;
import pwd.initializr.monitor.persistence.entity.HostCpuCoreEntity;

/**
 * <h2>服务层逻辑接口封装：HostCpuCoreEntity信息服务接口</h2>
 * date 2020-10-23 11:58
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("HostCpuCoreService")
public class HostCpuCoreServiceImpl implements HostCpuCoreService {

  @Resource
  private HostCpuCoreDao dao;

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
  public void insert(HostCpuCoreBO bo) {
    HostCpuCoreEntity entity = new HostCpuCoreEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insert(entity);
  }

  @Override
  public void insertOrReplace(HostCpuCoreBO bo) {
    HostCpuCoreEntity entity = new HostCpuCoreEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insertOrReplace(entity);
  }

  @Override
  public void insertOrReplace(List<HostCpuCoreBO> bos) {
    LinkedList<HostCpuCoreEntity> hostCpuCoreEntities = new LinkedList<>();
    for (HostCpuCoreBO bo : bos) {
      HostCpuCoreEntity entity = new HostCpuCoreEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setAble(EntityAble.DISABLE.getNumber());
      entity.setDel(EntityDel.NO.getNumber());
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      hostCpuCoreEntities.add(entity);
    }
    this.dao.insertOrReplaceBatch(hostCpuCoreEntities);
  }

  @Override
  public PageableQueryResult<HostCpuCoreBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<HostCpuCoreBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<HostCpuCoreEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      HostCpuCoreBO resultItem = new HostCpuCoreBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public HostCpuCoreBO queryById(Long id) {
    HostCpuCoreEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    HostCpuCoreBO bo = new HostCpuCoreBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(HostCpuCoreBO bo){
    HostCpuCoreEntity entity = new HostCpuCoreEntity();
    BeanUtils.copyProperties(bo, entity);
    return this.dao.updateById(entity);
  }

}
