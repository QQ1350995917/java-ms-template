package pwd.initializr.account.test.persistence;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.persistence.dao.AdminUserDao;
import pwd.initializr.account.persistence.entity.AdminUserEntity;
import pwd.initializr.common.web.persistence.entity.EntityScopeHit;
import pwd.initializr.common.web.persistence.entity.EntitySort;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;
import pwd.initializr.common.web.persistence.entity.SortEntity;

/**
 * pwd.initializr.account.test.persistence@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-08-10 17:06
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminUserBODaoTest {

    @Autowired
    private AdminUserDao adminUserDao;

    @Test
    public void testQueryAllByScope() {
        LinkedHashSet<ScopeEntity> scopeEntities = new LinkedHashSet<>();
        ScopeEntity scopeEntityName = new ScopeEntity();
        scopeEntityName.setHit(EntityScopeHit.LIKE.getHit());
        scopeEntityName.setFieldName("name");
        scopeEntityName.setFieldValue("LuoGuanZhong");
        scopeEntities.add(scopeEntityName);

        ScopeEntity scopeEntityPin = new ScopeEntity();
        scopeEntityPin.setHit(EntityScopeHit.RIGHT_LIKE.getHit());
        scopeEntityPin.setFieldName("pin");
        scopeEntityPin.setFieldValue("LuoGuan");
        scopeEntities.add(scopeEntityPin);

        ScopeEntity scopeEntityId = new ScopeEntity();
        scopeEntityId.setHit(EntityScopeHit.EQUALS.getHit());
        scopeEntityId.setFieldName("id");
        scopeEntityId.setFieldValue("1");
        scopeEntities.add(scopeEntityId);

        ScopeEntity scopeEntityDel = new ScopeEntity();
        scopeEntityDel.setHit(EntityScopeHit.EQUALS_NOT.getHit());
        scopeEntityDel.setFieldName("del");
        scopeEntityDel.setFieldValue("1");
        scopeEntities.add(scopeEntityDel);

        ScopeEntity scopeEntityCreateTime = new ScopeEntity();
        scopeEntityCreateTime.setHit(EntityScopeHit.SCOPE.getHit());
        scopeEntityCreateTime.setFieldName("create_time");
        scopeEntityCreateTime.setStart("2020-07-25");
        scopeEntityCreateTime.setEnd("2020-07-29");
        scopeEntities.add(scopeEntityCreateTime);

        LinkedHashSet<SortEntity> sortEntities = new LinkedHashSet<>();
        SortEntity sortEntity = new SortEntity();
        sortEntity.setFieldName("create_time");
        sortEntity.setSort(EntitySort.DESC.getSort());
        sortEntities.add(sortEntity);

        List<AdminUserEntity> adminUserEntities = adminUserDao
            .queryAllByCondition(scopeEntities, sortEntities, 0L, 100L);
        Assert.assertEquals(1, adminUserEntities.size());
    }
}
