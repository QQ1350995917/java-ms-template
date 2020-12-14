package pwd.initializr.email.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.email.business.bo.EmailAttachmentBO;
import pwd.initializr.email.persistence.dao.EmailAttachmentDao;
import pwd.initializr.email.persistence.entity.EmailAttachmentEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：EmailAttachmentEntity信息服务接口</h2>
 * date 2020-12-14 15:55
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("EmailAttachmentService")
public class EmailAttachmentServiceImpl implements EmailAttachmentService {

  @Resource
  private EmailAttachmentDao dao;

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
  public void insert(EmailAttachmentBO bo) {
    EmailAttachmentEntity entity = new EmailAttachmentEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insert(entity);
  }

  @Override
  public void insert(Set<EmailAttachmentBO> bos) {
    LinkedList<EmailAttachmentEntity> entities = new LinkedList<>();
    for (EmailAttachmentBO bo : bos) {
      EmailAttachmentEntity entity = new EmailAttachmentEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      entities.add(entity);
    }
    this.dao.insertByBatch(entities);
  }

  @Override
  public void insert(Long emailId,Set<EmailAttachmentBO> bos) {
    LinkedList<EmailAttachmentEntity> entities = new LinkedList<>();
    for (EmailAttachmentBO bo : bos) {
      EmailAttachmentEntity entity = new EmailAttachmentEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setEmailId(emailId);
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      entities.add(entity);
    }
    this.dao.insertByBatch(entities);
  }



  @Override
  public void insertOrReplace(EmailAttachmentBO bo) {
    EmailAttachmentEntity entity = new EmailAttachmentEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    this.dao.insertOrReplace(entity);
  }

  @Override
  public void insertOrReplace(List<EmailAttachmentBO> bos) {
    LinkedList<EmailAttachmentEntity> entities = new LinkedList<>();
    for (EmailAttachmentBO bo : bos) {
      EmailAttachmentEntity entity = new EmailAttachmentEntity();
      BeanUtils.copyProperties(bo, entity);
      entity.setCreateTime(new Date());
      entity.setUpdateTime(new Date());
      entities.add(entity);
    }
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<EmailAttachmentBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<EmailAttachmentBO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<EmailAttachmentEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      EmailAttachmentBO resultItem = new EmailAttachmentBO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public EmailAttachmentBO queryById(Long id) {
    EmailAttachmentEntity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    EmailAttachmentBO bo = new EmailAttachmentBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(EmailAttachmentBO bo){
    EmailAttachmentEntity entity = new EmailAttachmentEntity();
    BeanUtils.copyProperties(bo, entity);
    return this.dao.updateById(entity);
  }

}
