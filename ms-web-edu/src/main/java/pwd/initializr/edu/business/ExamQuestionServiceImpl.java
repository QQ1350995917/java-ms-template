package pwd.initializr.edu.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.edu.business.bo.ExamQuestionBO;
import pwd.initializr.edu.persistence.dao.ExamQuestionDao;
import pwd.initializr.edu.persistence.entity.ExamQuestionEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：ExamQuestionEntity信息服务接口</h2>
 * date 2021-02-19 14:59
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("ExamQuestionService")
public class ExamQuestionServiceImpl implements ExamQuestionService {

  @Resource
  private ExamQuestionDao dao;

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
  public Long insert(ExamQuestionBO bo) {
    ExamQuestionEntity entity = this.convertExamQuestionBO2ExamQuestionEntity(bo);
    this.dao.insert(entity);
    return entity.getId();
  }

  @Override
  public void insert(List<ExamQuestionBO> bos) {
    List<ExamQuestionEntity> entities = bos.stream()
      .map(this::convertExamQuestionBO2ExamQuestionEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(ExamQuestionBO bo) {
    ExamQuestionEntity entity = this.convertExamQuestionBO2ExamQuestionEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getId();
  }

  @Override
  public void insertOrReplace(List<ExamQuestionBO> bos) {
    List<ExamQuestionEntity> entities = bos.stream()
      .map(this::convertExamQuestionBO2ExamQuestionEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<ExamQuestionBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<ExamQuestionBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<ExamQuestionEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      ExamQuestionBO resultItem = new ExamQuestionBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public ExamQuestionBO queryById(Long id) {
    ExamQuestionEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    ExamQuestionBO bo = new ExamQuestionBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(ExamQuestionBO bo){
    ExamQuestionEntity entity = new ExamQuestionEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public ExamQuestionEntity convertExamQuestionBO2ExamQuestionEntity(ExamQuestionBO bo){
    ExamQuestionEntity entity = new ExamQuestionEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}