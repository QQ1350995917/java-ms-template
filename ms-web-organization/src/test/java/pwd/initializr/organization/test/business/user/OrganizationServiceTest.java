package pwd.initializr.organization.test.business.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.organization.business.user.OrganizationService;
import pwd.initializr.organization.business.user.bo.Organization;
import pwd.initializr.organization.business.user.bo.OrganizationMember;

/**
 * pwd.initializr.organization.test.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 14:22
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationServiceTest {

  @Autowired
  private OrganizationService organizationService;

  @Test
  public void testCreate(){
    Organization organization = new Organization();
    organization.setPid(1L);
    organization.setName("business测试");
    OrganizationMember organizationMember = new OrganizationMember();
    organizationMember.setMemId(1L);
    organizationMember.setLevel(1);
    organizationService.create(organization,organizationMember);
  }

  public void testUpdate(){

  }

  public void testReviewPending(){

  }

  public void testListByPidAndStatus(){

  }
}
