package pwd.initializr.edu.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.edu.business.bo.EduTermCourseTextbookArticleBO;
import pwd.initializr.edu.persistence.dao.EduTermCourseTextbookArticleDao;
import pwd.initializr.edu.persistence.entity.EduTermCourseTextbookArticleEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：EduTermCourseTextbookArticleEntity信息服务接口</h2>
 * date 2021-03-08 17:38
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("EduTermCourseTextbookArticleService")
public class EduTermCourseTextbookArticleServiceImpl implements EduTermCourseTextbookArticleService {

  @Resource
  private EduTermCourseTextbookArticleDao dao;

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
  public Long insert(EduTermCourseTextbookArticleBO bo) {
    EduTermCourseTextbookArticleEntity entity = this.convertEduTermCourseTextbookArticleBO2EduTermCourseTextbookArticleEntity(bo);
    this.dao.insert(entity);
    return entity.getId();
  }

  @Override
  public void insert(List<EduTermCourseTextbookArticleBO> bos) {
    List<EduTermCourseTextbookArticleEntity> entities = bos.stream()
      .map(this::convertEduTermCourseTextbookArticleBO2EduTermCourseTextbookArticleEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(EduTermCourseTextbookArticleBO bo) {EduTermCourseTextbookArticleEntity entity = this.convertEduTermCourseTextbookArticleBO2EduTermCourseTextbookArticleEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getId();
  }

  @Override
  public void insertOrReplace(List<EduTermCourseTextbookArticleBO> bos) {
    List<EduTermCourseTextbookArticleEntity> entities = bos.stream()
      .map(this::convertEduTermCourseTextbookArticleBO2EduTermCourseTextbookArticleEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<EduTermCourseTextbookArticleBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<EduTermCourseTextbookArticleBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<EduTermCourseTextbookArticleEntity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      EduTermCourseTextbookArticleBO resultItem = new EduTermCourseTextbookArticleBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public EduTermCourseTextbookArticleBO queryById(Long id) {
    EduTermCourseTextbookArticleEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    EduTermCourseTextbookArticleBO bo = new EduTermCourseTextbookArticleBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(EduTermCourseTextbookArticleBO bo){EduTermCourseTextbookArticleEntity entity = new EduTermCourseTextbookArticleEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public EduTermCourseTextbookArticleEntity convertEduTermCourseTextbookArticleBO2EduTermCourseTextbookArticleEntity(EduTermCourseTextbookArticleBO bo){
    EduTermCourseTextbookArticleEntity entity = new EduTermCourseTextbookArticleEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
