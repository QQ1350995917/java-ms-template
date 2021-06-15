package pwd.initializr.organization.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.rpc.RPCUser;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.organization.business.bo.OrganizationMemberBO;
import pwd.initializr.organization.business.remote.OrganizationMemberClientService;
import pwd.initializr.organization.persistence.dao.OrganizationDao;
import pwd.initializr.organization.persistence.dao.OrganizationMemberDao;
import pwd.initializr.organization.persistence.entity.OrganizationEntity;
import pwd.initializr.organization.persistence.entity.OrganizationMemberEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * <h2>服务层逻辑接口封装：OrganizationMemberEntity信息服务接口</h2>
 * date 2021-02-22 21:33
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Service("OrganizationMemberService")
public class OrganizationMemberServiceImpl implements OrganizationMemberService {


  @Resource
  private OrganizationDao organizationDao;
  @Resource
  private OrganizationMemberDao organizationMemberDao;
  @Resource
  private OrganizationMemberClientService memberClientService;

  @Override
  public Integer ableById(Long id, EntityAble able) {
    return this.organizationMemberDao.ableById(id, able.getNumber(), new Date());
  }

  @Override
  public Integer ableById(Set<Long> ids, EntityAble able) {
    return this.organizationMemberDao.ableByIds(ids, able.getNumber(), new Date());
  }

  @Override
  public Integer deleteById(Long id) {
    return this.organizationMemberDao.deleteById(id, new Date());
  }

  @Override
  public Integer deleteById(Set<Long> ids) {
    return this.organizationMemberDao.deleteByIds(ids, new Date());
  }

  @Override
  public Long insert(OrganizationMemberBO bo) {
    bo.setSort(0);
    bo.setAble(0);
    bo.setDel(0);
    OrganizationMemberEntity entity = this.convertOrganizationMemberBO2OrganizationMemberEntity(bo);
    this.organizationMemberDao.insert(entity);
    return entity.getMemId();
  }

  @Override
  public void insert(List<OrganizationMemberBO> bos) {
    List<OrganizationMemberEntity> entities = bos.stream()
      .map(this::convertOrganizationMemberBO2OrganizationMemberEntity).collect(Collectors.toList());
    this.organizationMemberDao.insertByBatch(entities);
  }

  @Override
  public Long insertOrReplace(OrganizationMemberBO bo) {
    OrganizationMemberEntity entity = this.convertOrganizationMemberBO2OrganizationMemberEntity(bo);
    this.organizationMemberDao.insertOrReplace(entity);
    return entity.getOrgId();
  }

  @Override
  public void insertOrReplace(List<OrganizationMemberBO> bos) {
    List<OrganizationMemberEntity> entities = bos.stream()
      .map(this::convertOrganizationMemberBO2OrganizationMemberEntity).collect(Collectors.toList());
    this.organizationMemberDao.insertOrReplaceByBatch(entities);
  }

  @Override
  public PageableQueryResult<OrganizationMemberBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
    LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<OrganizationMemberBO> result = new PageableQueryResult<>();
    Long total = this.organizationMemberDao.countByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<OrganizationMemberEntity> entities = this.organizationMemberDao
        .queryByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return result;
    }
    // 查询组织信息
    Long[] orgIds = entities.stream().map(OrganizationMemberEntity::getOrgId).toArray(Long[]::new);
    List<OrganizationEntity> organizationEntities = organizationDao.queryByIds(orgIds);
    Map<Long, String> orgMap = organizationEntities.stream()
        .collect(Collectors.toMap(OrganizationEntity::getId, OrganizationEntity::getName));

    // 查询成员信息
    // FIXME: 熔断降级返回null，有空指针异常
    Long[] memberIds = entities.stream().map(OrganizationMemberEntity::getMemId).toArray(Long[]::new);
    Output<List<RPCUser>> response = memberClientService.findByIds(memberIds);
    Map<Long, RPCUser> memberMap = response.getData().stream()
        .collect(Collectors.toMap(RPCUser::getId, user -> user));

    entities.forEach(entity -> {
      OrganizationMemberBO resultItem = new OrganizationMemberBO();
      BeanUtils.copyProperties(entity, resultItem);
      resultItem.setOrgName(orgMap.get(entity.getOrgId()));
      resultItem.setMember(memberMap.get(entity.getMemId()));
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public OrganizationMemberBO queryById(Long id) {
    OrganizationMemberEntity entity = this.organizationMemberDao.queryById(id);
    if (entity == null) {
      return null;
    }
    OrganizationMemberBO bo = new OrganizationMemberBO();
    BeanUtils.copyProperties(entity, bo);
    return bo;
  }

  @Override
  public Integer updateById(OrganizationMemberBO bo){
    OrganizationMemberEntity entity = new OrganizationMemberEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setUpdateTime(new Date());
    return this.organizationMemberDao.updateById(entity);
  }

  public OrganizationMemberEntity convertOrganizationMemberBO2OrganizationMemberEntity(OrganizationMemberBO bo){
    OrganizationMemberEntity entity = new OrganizationMemberEntity();
    BeanUtils.copyProperties(bo, entity);
    entity.setAble(EntityAble.DISABLE.getNumber());
    entity.setDel(EntityDel.NO.getNumber());
    entity.setCreateTime(new Date());
    entity.setUpdateTime(new Date());
    return entity;
  }
}
