package pwd.initializr.gateway.persistence.dao;

import pwd.initializr.gateway.persistence.entity.RouterEntity;

/**
 * pwd.initializr.gateway.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-13 21:21
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface RouterDao {

    void createRouterTable();

    RouterEntity query();

    int delete();

    int create(RouterEntity routerEntity);


}
