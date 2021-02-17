package pwd.initializr.edu.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.edu.business.bo.WordCollectionBO;
import pwd.initializr.edu.persistence.dao.WordCollectionDao;
import pwd.initializr.edu.persistence.entity.WordCollectionEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：WordCollectionEntity信息服务接口</h2>
 * date 2021-02-16 21:51
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("WordCollectionService")
public class WordCollectionServiceImpl implements WordCollectionService {

  @Resource
  private WordCollectionDao dao;

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
  public Long insert(WordCollectionBO bo) {
    WordCollectionEntity entity = this.convertWordCollectionBO2WordCollectionEntity(bo);
    this.dao.insert(entity);
    return entity.getId();
  }

  @Override
  public void insert(List<WordCollectionBO> bos) {
    List<WordCollectionEntity> entities = bos.stream()
      .map(this::convertWordCollectionBO2WordCollectionEntity).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(WordCollectionBO bo) {
    WordCollectionEntity entity = this.convertWordCollectionBO2WordCollectionEntity(bo);
    this.dao.insertOrReplace(entity);
    return entity.getId();
  }

  @Override
  public void insertOrReplace(List<WordCollectionBO> bos) {
    List<WordCollectionEntity> entities = bos.stream()
      .map(this::convertWordCollectionBO2WordCollectionEntity).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<WordCollectionBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<WordCollectionBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<WordCollectionEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      WordCollectionBO resultItem = new WordCollectionBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public WordCollectionBO queryById(Long id) {
    WordCollectionEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    WordCollectionBO bo = new WordCollectionBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(WordCollectionBO bo){
    WordCollectionEntity entity = new WordCollectionEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public WordCollectionEntity convertWordCollectionBO2WordCollectionEntity(WordCollectionBO bo){
    WordCollectionEntity entity = new WordCollectionEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
