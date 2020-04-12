package pwd.initializr.storage.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;
import pwd.initializr.storage.persistence.dao.StorageEntity;

/**
 * pwd.initializr.storage.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 19:53
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Mapper
public interface StorageMapper {

  void insertFile(StorageEntity storageEntity);
}
