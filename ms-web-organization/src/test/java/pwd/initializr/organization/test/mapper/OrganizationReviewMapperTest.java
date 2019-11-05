package pwd.initializr.organization.test.mapper;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.organization.persistence.dao.OrganizationReviewEntity;
import pwd.initializr.organization.persistence.mapper.OrganizationReviewMapper;

/**
 * pwd.initializr.organization.test.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 18:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationReviewMapperTest {

  @Autowired
  private OrganizationReviewMapper organizationProgressMapper;

  @Test
  public void testCreate(){
    OrganizationReviewEntity organizationProgressEntity = new OrganizationReviewEntity();
    organizationProgressEntity.setOrgId(1L);
    organizationProgressEntity.setEditorId(1L);
    organizationProgressEntity.setContent("neirong =");
    organizationProgressEntity.setRefId(9L);
    organizationProgressEntity.setType(1);
//    organizationProgressEntity.setProgress(0);
    organizationProgressEntity.setStatus(0);
    organizationProgressEntity.setCreateTime(System.currentTimeMillis());
    organizationProgressEntity.setUpdateTime(System.currentTimeMillis());
    organizationProgressMapper.create(organizationProgressEntity);
    System.out.println(organizationProgressEntity);
  }


  @Test
  public void testListByOrgId(){
    List<OrganizationReviewEntity> organizationProgressEntities = organizationProgressMapper
        .listByOrgId(1L, null);
    System.out.println(organizationProgressEntities.size());
  }

}
