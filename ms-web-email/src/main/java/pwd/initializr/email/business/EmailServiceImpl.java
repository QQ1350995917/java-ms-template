package pwd.initializr.email.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityDel;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.persistence.dao.EmailDao;
import pwd.initializr.email.persistence.entity.EmailEntity;
import pwd.initializr.email.persistence.entity.EmailSendType;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-05 16:33
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {



    @Resource
    private EmailDao dao;

    @Override
    public Integer deleteById(Long id) {
        return this.dao.deleteById(id);
    }

    @Override
    public Integer deleteById(Set<Long> ids) {
        return this.dao.deleteByIds(ids);
    }

    @Override
    public void insert(EmailBO bo) {
        EmailEntity entity = new EmailEntity();
        BeanUtils.copyProperties(bo, entity);
        entity.setSent(EmailSendType.WAITING.getType());
        entity.setDel(EntityDel.NO.getNumber());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        this.dao.insert(entity);
    }

    @Override
    public void insert(List<EmailBO> bos) {
        LinkedList<EmailEntity> entities = new LinkedList<>();
        for (EmailBO bo : bos) {
            EmailEntity entity = new EmailEntity();
            BeanUtils.copyProperties(bo, entity);
            entity.setSent(EmailSendType.WAITING.getType());
            entity.setDel(EntityDel.NO.getNumber());
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            entities.add(entity);
        }
        this.dao.insertByBatch(entities);
    }

    @Override
    public void insertOrReplace(EmailBO bo) {
        EmailEntity entity = new EmailEntity();
        BeanUtils.copyProperties(bo, entity);
        entity.setSent(EmailSendType.WAITING.getType());
        entity.setDel(EntityDel.NO.getNumber());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        this.dao.insertOrReplace(entity);
    }

    @Override
    public void insertOrReplace(List<EmailBO> bos) {
        LinkedList<EmailEntity> entities = new LinkedList<>();
        for (EmailBO bo : bos) {
            EmailEntity entity = new EmailEntity();
            BeanUtils.copyProperties(bo, entity);
            entity.setSent(EmailSendType.WAITING.getType());
            entity.setDel(EntityDel.NO.getNumber());
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            entities.add(entity);
        }
        this.dao.insertOrReplaceByBatch(entities);
    }

    @Override
    public PageableQueryResult<EmailBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
        LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
        PageableQueryResult<EmailBO> result = new PageableQueryResult<>();
        Long total = this.dao.countByCondition(scopes);
        if (total == null || total < 1) {
            return result;
        }
        List<EmailEntity> entities = this.dao.queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
        if (entities == null) {
            return result;
        }
        entities.forEach(entity -> {
            EmailBO resultItem = new EmailBO();
            BeanUtils.copyProperties(entity, resultItem);
            result.getElements().add(resultItem);
        });
        result.setIndex(pageIndex);
        result.setSize(pageSize);
        result.setTotal(total);
        return result;
    }

    @Override
    public List<EmailBO> queryBySynchronizationForSender(Long pageSize) {
        List<EmailEntity> emailEntities = this.dao.queryBySynchronizationForSender(pageSize);
        LinkedList<EmailBO> emailBOS = new LinkedList<>();
        if (emailEntities != null && !emailEntities.isEmpty()) {
            for (EmailEntity emailEntity : emailEntities) {
                EmailBO emailBO = new EmailBO();
                BeanUtils.copyProperties(emailEntity,emailBO);
                emailBOS.add(emailBO);
            }
        }
        return emailBOS;
    }

    @Override
    public EmailBO queryById(Long id) {
        EmailEntity entity = this.dao.queryById(id);
        if (entity == null) {
            return null;
        }
        EmailBO bo = new EmailBO();
        BeanUtils.copyProperties(entity, bo);
        return bo;
    }

    @Override
    public Integer updateById(EmailBO bo){
        EmailEntity entity = new EmailEntity();
        BeanUtils.copyProperties(bo, entity);
        return this.dao.updateById(entity);
    }

    @Override
    public Integer updateSentStatusByBatch(Set<Long> ids, EmailSendType sendType) {
        Integer integer = this.dao.updateSentStatusByBatch(ids, sendType.getType());
        return integer;
    }
}
