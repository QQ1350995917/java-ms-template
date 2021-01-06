package pwd.initializr.gateway.persistence.dao;

import org.apache.ibatis.annotations.Mapper;
import pwd.initializr.gateway.persistence.entity.SessionVersionEntity;

/**
 * pwd.initializr.gateway.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-06 17:01
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Mapper
public interface SessionVersionDao {

    int create(SessionVersionEntity entity);

    void createTable();

    int delete();

    SessionVersionEntity query();
}
