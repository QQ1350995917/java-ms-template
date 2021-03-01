package pwd.initializr.edu.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.edu.business.bo.EduTermCourseBO;
import pwd.initializr.edu.persistence.dao.EduTermCourseDao;
import pwd.initializr.edu.persistence.entity.EduTermCourseEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：EduTermCourseEntity信息服务接口</h2>
 * date 2021-03-01 22:01
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("EduTermCourseService")
public class EduTermCourseServiceImpl implements EduTermCourseService {

  @Resource
  private EduTermCourseDao dao;

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
  public Long insert(EduTermCourseBO bo) {
    EduTermCourseEntity entity = this.convertEduTermCourseBO2EduTermCourseEntity(bo);
    this.dao.insert(entity);
    return entity.getId();
  }

  @Override
  public void insert(List<EduTermCourseBO> bos) {
    List<EduTermCourseEntity> entities = bos.stream()
      .map(this::convertEduTermCourseBO2EduTermCourseEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(EduTermCourseBO bo) {EduTermCourseEntity entity = this.convertEduTermCourseBO2EduTermCourseEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getId();
  }

  @Override
  public void insertOrReplace(List<EduTermCourseBO> bos) {
    List<EduTermCourseEntity> entities = bos.stream()
      .map(this::convertEduTermCourseBO2EduTermCourseEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<EduTermCourseBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<EduTermCourseBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<EduTermCourseEntity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      EduTermCourseBO resultItem = new EduTermCourseBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public EduTermCourseBO queryById(Long id) {
    EduTermCourseEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    EduTermCourseBO bo = new EduTermCourseBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(EduTermCourseBO bo){EduTermCourseEntity entity = new EduTermCourseEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public EduTermCourseEntity convertEduTermCourseBO2EduTermCourseEntity(EduTermCourseBO bo){
    EduTermCourseEntity entity = new EduTermCourseEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
