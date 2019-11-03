package pwd.initializr.organization.test.mapper;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.organization.persistence.dao.OrganizationMemberEntity;
import pwd.initializr.organization.persistence.mapper.OrganizationMemberMapper;

/**
 * pwd.initializr.account.test.user.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 22:46
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationMemberMapperTest {

  @Autowired
  private OrganizationMemberMapper organizationMemberMapper;

  @Test
  public void testAddUserToOrg() {
    OrganizationMemberEntity userOrgEntity = new OrganizationMemberEntity();
    userOrgEntity.setMemId(1L);
    userOrgEntity.setOrgId(2L);
    userOrgEntity.setLevel(1);
    userOrgEntity.setStatus(0);
    userOrgEntity.setCreateTime(System.currentTimeMillis());
    userOrgEntity.setUpdateTime(System.currentTimeMillis());
    organizationMemberMapper.addMemToOrg(userOrgEntity);
  }


  @Test
  public void testUpdateLevel() {
    organizationMemberMapper.updateMemLevelInOrg(1L, 1L, 250);
  }

  @Test
  public void testUpdateStatus() {
    organizationMemberMapper.updateMemStatusInOrg(1L, 1L, 250);
  }


  @Test
  public void testFindOne() {
    OrganizationMemberEntity one = organizationMemberMapper.findOneMemInOrg(1L, 1L);
    System.out.println();
  }

}
