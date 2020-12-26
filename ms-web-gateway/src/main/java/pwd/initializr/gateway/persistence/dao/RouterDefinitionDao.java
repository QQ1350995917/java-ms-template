package pwd.initializr.gateway.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import pwd.initializr.gateway.persistence.entity.RouterDefinitionEntity;

/**
 * pwd.initializr.gateway.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-26 22:00
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Mapper
public interface RouterDefinitionDao {

  int create(RouterDefinitionEntity entity);

  int createByBatch(List<RouterDefinitionEntity> entities);

  void createTable();

  int deleteAll();

  int deleteById(String id);

  List<RouterDefinitionEntity> queryAll();

  int updateById(String id);

}
