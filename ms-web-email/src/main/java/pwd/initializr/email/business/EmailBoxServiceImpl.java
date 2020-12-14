package pwd.initializr.email.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.email.business.bo.EmailBoxBO;
import pwd.initializr.email.persistence.dao.EmailBoxDao;
import pwd.initializr.email.persistence.entity.EmailBoxEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：EmailBoxEntity信息服务接口</h2>
 * date 2020-12-14 15:55
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("EmailBoxService")
public class EmailBoxServiceImpl implements EmailBoxService {

  @Resource
  private EmailBoxDao dao;

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
  public void insert(EmailBoxBO bo) {
    EmailBoxEntity entity = new EmailBoxEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insert(entity);
  }

  @Override
  public void insert(Set<EmailBoxBO> bos) {
    LinkedList<EmailBoxEntity> entities = new LinkedList<>();
    for (EmailBoxBO bo : bos) {
      EmailBoxEntity entity = new EmailBoxEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      entities.add(entity);
    }
    this.dao.insertByBatch(entities);
  }


  @Override
  public void insert(Long emailId,Set<EmailBoxBO> bos) {
    LinkedList<EmailBoxEntity> entities = new LinkedList<>();
    for (EmailBoxBO bo : bos) {
      EmailBoxEntity entity = new EmailBoxEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setEmailId(emailId);
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      entities.add(entity);
    }
    this.dao.insertByBatch(entities);
  }



  @Override
  public void insertOrReplace(EmailBoxBO bo) {
    EmailBoxEntity entity = new EmailBoxEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insertOrReplace(entity);
  }

  @Override
  public void insertOrReplace(List<EmailBoxBO> bos) {
    LinkedList<EmailBoxEntity> entities = new LinkedList<>();
    for (EmailBoxBO bo : bos) {
      EmailBoxEntity entity = new EmailBoxEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      entities.add(entity);
    }
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<EmailBoxBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<EmailBoxBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<EmailBoxEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      EmailBoxBO resultItem = new EmailBoxBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public EmailBoxBO queryById(Long id) {
    EmailBoxEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    EmailBoxBO bo = new EmailBoxBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(EmailBoxBO bo){
    EmailBoxEntity entity = new EmailBoxEntity();
    BeanUtils.copyProperties(bo, entity);
    return this.dao.updateById(entity);
  }

}
