package pwd.initializr.organization.test.mapper;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.organization.persistence.dao.OrganizationMemberDealEntity;
import pwd.initializr.organization.persistence.mapper.OrganizationMemberDealMapper;

/**
 * pwd.initializr.organization.test.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-07 22:25
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationMemberDealMapperTest {

  @Autowired
  private OrganizationMemberDealMapper organizationMemberDealMapper;

  @Test
  public void testFindOneByOrgIdUserIdType(){
    OrganizationMemberDealEntity oneByOrgIdUserIdType = organizationMemberDealMapper
        .findOneByOrgIdUserIdType(1L, 1L, 0);
    System.out.println(oneByOrgIdUserIdType);
  }


  @Test
  public void testCreate(){
    OrganizationMemberDealEntity organizationMemberDealEntity = new OrganizationMemberDealEntity();
    organizationMemberDealEntity.setOrgId(1L);
    organizationMemberDealEntity.setUserId(1L);
    organizationMemberDealEntity.setType(0);
    organizationMemberDealEntity.setDeal(0);
    organizationMemberDealEntity.setCounter(1);
    organizationMemberDealEntity.setStatus(0);
    organizationMemberDealEntity.setCreateTime(System.currentTimeMillis());
    organizationMemberDealEntity.setUpdateTime(System.currentTimeMillis());
    organizationMemberDealMapper.create(organizationMemberDealEntity);
  }


  @Test
  public void testUpdateCounter(){
    organizationMemberDealMapper.updateCounter(1L, 1L, 0);
  }


  @Test
  public void testListByOrgId(){
    List<OrganizationMemberDealEntity> organizationMemberDealEntities = organizationMemberDealMapper
        .listByOrgId(1L, 0);
    System.out.println(organizationMemberDealEntities);
  }


  @Test
  public void testListByUserId(){
    List<OrganizationMemberDealEntity> organizationMemberDealEntities = organizationMemberDealMapper
        .listByUserId(1L, 1);
    System.out.println(organizationMemberDealEntities);
  }


}
