package pwd.initializr.gateway.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.gateway.persistence.entity.SessionEntity;

/**
 * pwd.initializr.gateway.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-30 22:57
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Mapper
public interface SessionDao {

  int createByBatch(@Param("entities") List<SessionEntity> entities);

  void createTable();

  int delete();

  int deleteById(@Param("id") Long id);

  List<SessionEntity> queryAll();

  int queryVersion();

  int create(@Param("entity") SessionEntity entity);

  int update(@Param("entity") SessionEntity entity);

}
