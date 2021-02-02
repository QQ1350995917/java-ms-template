package pwd.initializr.edu.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.edu.business.bo.ArticleContentBO;
import pwd.initializr.edu.persistence.dao.ArticleContentDao;
import pwd.initializr.edu.persistence.entity.ArticleContentEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：ArticleContentEntity信息服务接口</h2>
 * date 2021-02-02 14:44
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("ArticleContentService")
public class ArticleContentServiceImpl implements ArticleContentService {

  @Resource
  private ArticleContentDao dao;

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
  public void insert(ArticleContentBO bo) {
    ArticleContentEntity entity = new ArticleContentEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insert(entity);
  }

  @Override
  public void insert(List<ArticleContentBO> bos) {
    LinkedList<ArticleContentEntity> entities = new LinkedList<>();
    for (ArticleContentBO bo : bos) {
      ArticleContentEntity entity = new ArticleContentEntity();
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
  public void insertOrReplace(ArticleContentBO bo) {
    ArticleContentEntity entity = new ArticleContentEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insertOrReplace(entity);
  }

  @Override
  public void insertOrReplace(List<ArticleContentBO> bos) {
    LinkedList<ArticleContentEntity> entities = new LinkedList<>();
    for (ArticleContentBO bo : bos) {
      ArticleContentEntity entity = new ArticleContentEntity();
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
  public PageableQueryResult<ArticleContentBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<ArticleContentBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<ArticleContentEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      ArticleContentBO resultItem = new ArticleContentBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public ArticleContentBO queryById(Long id) {
    ArticleContentEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    ArticleContentBO bo = new ArticleContentBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(ArticleContentBO bo){
    ArticleContentEntity entity = new ArticleContentEntity();
    BeanUtils.copyProperties(bo, entity);
    return this.dao.updateById(entity);
  }

}
