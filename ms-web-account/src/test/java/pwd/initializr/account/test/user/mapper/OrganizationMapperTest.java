package pwd.initializr.account.test.user.mapper;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.persistence.dao.OrganizationEntity;
import pwd.initializr.account.persistence.mapper.OrganizationMapper;

/**
 * pwd.initializr.account.test.user.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 22:43
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationMapperTest {

  @Autowired
  private OrganizationMapper organizationMapper;

  @Test
  public void testCreate() {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    organizationEntity.setPid(0L);
    organizationEntity.setCreateTime(System.currentTimeMillis());
    organizationEntity.setUpdateTime(System.currentTimeMillis());
    organizationMapper.create(organizationEntity);
  }

  @Test
  public void testUpdate() {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    organizationEntity.setId(1L);
    organizationEntity.setName("德玛西亚");
    organizationEntity.setDescription("一种游戏");
    organizationEntity.setSlogan("德玛西亚万岁");
    organizationEntity.setLogo("http://initializer.pwd");
    organizationMapper.update(organizationEntity);
  }

  @Test
  public void testUpdateLevelById() {
    organizationMapper.updateLevelById(1L, 250);
  }

  @Test
  public void testUpdateSortById() {
    organizationMapper.updateSortById(0L, 1L, 250);
  }

  @Test
  public void testUpdateMembersById() {
    organizationMapper.updateMembersById(1L, 250);
  }

  @Test
  public void testUpdateStatusById() {
    organizationMapper.updateStatusById(1L, 2);
  }

  @Test
  public void testUpdateProgressById() {
    organizationMapper.updateProgressById(1L, 2);
  }

  @Test
  public void testListByPidAndStatus() {
    List<OrganizationEntity> organizationEntities = organizationMapper.listByPidAndStatus(0L, 1);
    Assert.assertNotEquals(organizationEntities.size(), 0);
  }


}
