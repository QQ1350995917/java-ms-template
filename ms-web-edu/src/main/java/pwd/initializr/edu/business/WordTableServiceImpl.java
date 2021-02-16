package pwd.initializr.edu.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.edu.business.bo.WordTableBO;
import pwd.initializr.edu.persistence.dao.WordTableDao;
import pwd.initializr.edu.persistence.entity.WordTableEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：WordTableEntity信息服务接口</h2>
 * date 2021-02-16 15:23
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("WordTableService")
public class WordTableServiceImpl implements WordTableService {

  @Resource
  private WordTableDao dao;

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
  public String insert(WordTableBO bo) {
    WordTableEntity entity = this.convertWordTableBO2WordTableEntity(bo);
    this.dao.insert(entity);
    return entity.getId();
  }

  @Override
  public void insert(List<WordTableBO> bos) {
    List<WordTableEntity> entities = bos.stream()
      .map(this::convertWordTableBO2WordTableEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public String insertOrReplace(WordTableBO bo) {
    WordTableEntity entity = this.convertWordTableBO2WordTableEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getId();
  }

  @Override
  public void insertOrReplace(List<WordTableBO> bos) {
    List<WordTableEntity> entities = bos.stream()
      .map(this::convertWordTableBO2WordTableEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<WordTableBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<WordTableBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<WordTableEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      WordTableBO resultItem = new WordTableBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public WordTableBO queryById(Long id) {
    WordTableEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    WordTableBO bo = new WordTableBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(WordTableBO bo){
    WordTableEntity entity = new WordTableEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public WordTableEntity convertWordTableBO2WordTableEntity(WordTableBO bo){
    WordTableEntity entity = new WordTableEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
