package pwd.initializr.storage.business;

import java.io.InputStream;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pwd.initializr.common.mw.minio.MinIOClient;
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
  @Autowired
  private MinIOClient minIOClient;

  @Override
  public ObjectList<Storage> listFile() {
    List<StorageEntity> findAll = mongoTemplate.findAll(StorageEntity.class);
    ObjectList<Storage> result = new ObjectList<>();
    List<Storage> resultElements = result.getElements();
    for (StorageEntity storageEntity : findAll) {
      Storage storage = new Storage();
      BeanUtils.copyProperties(storageEntity, storage);
      resultElements.add(storage);
    }
    return result;
  }

  @Override
  public Storage findOneByUrl(String url) {
    Query query = new Query(Criteria.where("url").is(url));
    StorageEntity one = mongoTemplate.findOne(query, StorageEntity.class);
    Storage storage = new Storage();
    BeanUtils.copyProperties(one, storage);
    return storage;
  }

  @Override
  public InputStream getObject(Storage storage) throws Exception {
    return minIOClient.getObject(storage.getBucketName(), storage.getObjectName());
  }
}
