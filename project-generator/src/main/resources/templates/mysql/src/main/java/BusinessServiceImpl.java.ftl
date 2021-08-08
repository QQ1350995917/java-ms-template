package ${projectPackage}.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ${projectPackage}.business.bo.${className}BO;
import ${projectPackage}.persistence.dao.${className}Dao;
import ${projectPackage}.persistence.entity.${className}Entity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：${className}Entity信息服务接口</h2>
 * date ${projectCreateDate}
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since ${projectVersion}
 */
@Service("${className}Service")
public class ${className}ServiceImpl implements ${className}Service {

  @Resource
  private ${className}Dao dao;

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
  public Long insert(${className}BO bo) {
    ${className}Entity entity = this.convert${className}BO2${className}EntityForCreate(bo);
    this.dao.insert(entity);
    return entity.getId();
  }

  @Override
  public void insert(List<${className}BO> bos) {
    List<${className}Entity> entities = bos.stream()
      .map(this::convert${className}BO2${className}EntityForCreate).collect(Collectors.toList());
    this.dao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(${className}BO bo) {${className}Entity entity = this.convert${className}BO2${className}EntityForCreate(bo);
    this.dao.insertOrReplace(entity);
    return entity.getId();
  }

  @Override
  public void insertOrReplace(List<${className}BO> bos) {
    List<${className}Entity> entities = bos.stream()
      .map(this::convert${className}BO2${className}EntityForCreate).collect(Collectors.toList());
    this.dao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<${className}BO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<${className}BO> result = new PageableQueryResult<>();
    Long total = this.dao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<${className}Entity> entities = this.dao.queryByCondition(scopes,sorts,
      pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    entities.forEach(entity -> {
      ${className}BO resultItem = new ${className}BO();
      BeanUtils.copyProperties(entity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public ${className}BO queryById(Long id) {
    ${className}Entity entity = this.dao.queryById(id);
    if (entity == null) {
      return null;
    }
    ${className}BO bo = new ${className}BO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(${className}BO bo){${className}Entity entity = new ${className}Entity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.dao.updateById(entity);
  }

  public ${className}Entity convert${className}BO2${className}EntityForCreate(${className}BO bo){
    ${className}Entity entity = new ${className}Entity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    entity.setVersion(0L);
    return entity;
  }
}
