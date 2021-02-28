package pwd.initializr.edu.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.edu.business.bo.EduTermBO;
import pwd.initializr.edu.persistence.dao.EduTermDao;
import pwd.initializr.edu.persistence.entity.EduTermEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：EduTermEntity信息服务接口</h2>
 * date 2021-02-28 22:45
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("EduTermService")
public class EduTermServiceImpl implements EduTermService {

  @Resource
  private EduTermDao dao;

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
  public Long insert(EduTermBO bo) {
    EduTermEntity entity = this.convertEduTermBO2EduTermEntity(bo);
    this.dao.insert(entity);
    return entity.getId();
  }

  @Override
  public void insert(List<EduTermBO> bos) {
    List<EduTermEntity> entities = bos.stream()
      .map(this::convertEduTermBO2EduTermEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(EduTermBO bo) {EduTermEntity entity = this.convertEduTermBO2EduTermEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getId();
  }

  @Override
  public void insertOrReplace(List<EduTermBO> bos) {
    List<EduTermEntity> entities = bos.stream()
      .map(this::convertEduTermBO2EduTermEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<EduTermBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<EduTermBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<EduTermEntity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      EduTermBO resultItem = new EduTermBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public EduTermBO queryById(Long id) {
    EduTermEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    EduTermBO bo = new EduTermBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(EduTermBO bo){EduTermEntity entity = new EduTermEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public EduTermEntity convertEduTermBO2EduTermEntity(EduTermBO bo){
    EduTermEntity entity = new EduTermEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
