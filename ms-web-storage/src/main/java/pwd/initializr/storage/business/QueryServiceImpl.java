package pwd.initializr.storage.business;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.storage.business.bo.Storage;
import pwd.initializr.storage.persistence.dao.StorageEntity;

/**
 * pwd.initializr.storage.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-26 18:24
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class QueryServiceImpl implements QueryService {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public ObjectList<Storage> listFile() {
    List<StorageEntity> all = mongoTemplate.findAll(StorageEntity.class);
    ObjectList<Storage> result = new ObjectList<>();
    for (StorageEntity storageEntity : all) {
      Storage storage = new Storage();
      BeanUtils.copyProperties(storageEntity, storage);
      result.getElements().add(storage);
    }
    return result;
  }

  @Override
  public Storage findOneByUrl(String url) {
    Query query = new Query();
    StorageEntity one = mongoTemplate.findOne(query, StorageEntity.class);
    return new Storage(one.getUrl(),one.getName());
  }
}
