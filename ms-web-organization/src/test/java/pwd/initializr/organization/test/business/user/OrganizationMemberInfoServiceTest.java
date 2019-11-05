package pwd.initializr.organization.test.business.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.organization.business.user.OrganizationMemberInfoService;
import pwd.initializr.organization.business.user.OrganizationService;

/**
 * pwd.initializr.organization.test.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 16:39
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationMemberInfoServiceTest {

  @Autowired
  private OrganizationMemberInfoService organizationMemberInfoService;

  @Test
  public void testFeign(){
    Object o = organizationMemberInfoService.fetchMemberInfo(new Long[]{1L, 2L});
    System.out.println(o);
  }

}
