package pwd.initializr.account.test.user.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.persistence.dao.UserOrgEntity;
import pwd.initializr.account.persistence.mapper.OrganizationMapper;
import pwd.initializr.account.persistence.mapper.UserOrgMapper;

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
public class UserOrgMapperTest {
  @Autowired
  private UserOrgMapper userOrgMapper;

  @Test
  public void testAddUserToOrg() {
    UserOrgEntity userOrgEntity = new UserOrgEntity();
    userOrgEntity.setUserId(1L);
    userOrgEntity.setOrgId(2L);
    userOrgEntity.setLevel(1);
    userOrgEntity.setStatus(0);
    userOrgEntity.setCreateTime(System.currentTimeMillis());
    userOrgEntity.setUpdateTime(System.currentTimeMillis());
    userOrgMapper.addUserToOrg(userOrgEntity);
  }


  @Test
  public void testUpdateLevel() {
    userOrgMapper.updateLevel(1L,1L,250);
  }

  @Test
  public void testUpdateStatus() {
    userOrgMapper.updateStatus(1L,1L,250);
  }

}
