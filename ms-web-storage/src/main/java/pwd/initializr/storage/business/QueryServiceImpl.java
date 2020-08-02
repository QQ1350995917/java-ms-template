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
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.storage.business.bo.StorageBO;
import pwd.initializr.storage.persistence.dao.StorageEntity;

/**
 * pwd.initializr.storage.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-26 18:24
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
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
  public PageableQueryResult<StorageBO> listFile() {
    List<StorageEntity> findAll = mongoTemplate.findAll(StorageEntity.class);
    PageableQueryResult<StorageBO> result = new PageableQueryResult<>();
    List<StorageBO> resultElements = result.getElements();
    for (StorageEntity storageEntity : findAll) {
      StorageBO storageBO = new StorageBO();
      BeanUtils.copyProperties(storageEntity, storageBO);
      resultElements.add(storageBO);
    }
    return result;
  }

  @Override
  public StorageBO findOneByUrl(String url) {
    Query query = new Query(Criteria.where("url").is(url));
    StorageEntity one = mongoTemplate.findOne(query, StorageEntity.class);
    StorageBO storageBO = new StorageBO();
    BeanUtils.copyProperties(one, storageBO);
    return storageBO;
  }

  @Override
  public InputStream getObject(StorageBO storageBO) throws Exception {
    return minIOClient.getObject(storageBO.getBucketName(), storageBO.getObjectName());
  }
}
