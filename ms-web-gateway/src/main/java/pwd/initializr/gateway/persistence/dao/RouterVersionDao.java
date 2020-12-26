package pwd.initializr.gateway.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import pwd.initializr.gateway.persistence.entity.RouterVersionEntity;

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
@Mapper
public interface RouterVersionDao {

  int create(RouterVersionEntity entity);

  void createTable();

  int delete();

  RouterVersionEntity query();

}
