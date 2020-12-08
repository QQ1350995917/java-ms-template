package pwd.initializr.storage.business;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pwd.initializr.common.mw.minio.MinIOClient;
import pwd.initializr.common.utils.Snowflake;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.storage.business.bo.ObjectDelErrorBO;
import pwd.initializr.storage.business.bo.StorageBO;
import pwd.initializr.storage.persistence.entity.StorageEntity;

/**
 * pwd.initializr.storage.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 19:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class StorageServiceImpl implements StorageService {

  @Autowired
  private MinIOClient minIOClient;
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void delete(String bucketName, String objectName) throws Exception {
//    minIOClient.removeObject(bucketName, objectName);
  }

  @Override
  public List<ObjectDelErrorBO> delete(String bucketName, List<String> objectNames)
      throws Exception {
    List<ObjectDelErrorBO> result = new LinkedList<>();
//    Iterable<Result<DeleteError>> results = minIOClient.removeObjects(bucketName, objectNames);
//    Iterator<Result<DeleteError>> iterator = results.iterator();
//    while (iterator.hasNext()) {
//      Result<DeleteError> next = iterator.next();
//      try {
//        DeleteError deleteError = next.get();
//        result.add(
//            new ObjectDelErrorBO(deleteError.code(), deleteError.message(),
//                deleteError.bucketName(),
//                deleteError.objectName(), deleteError.resource(), deleteError.requestId(),
//                deleteError.hostId(), deleteError.errorCode().code()));
//      } catch (Exception e) {
//        e.printStackTrace();
//        ObjectDelErrorBO objectDelErrorBO = new ObjectDelErrorBO();
//        objectDelErrorBO.setMessage(e.getMessage());
//        result.add(objectDelErrorBO);
//      }
//    }
    return result;
  }

  @Override
  public StorageBO uploadFile(StorageBO bo, InputStream inputStream) throws Exception {
    return upload(bo, inputStream);
  }

  @Override
  public StorageBO uploadText(StorageBO bo, String text) throws Exception {
    try (InputStream inputStream = new ByteArrayInputStream(text.getBytes())) {
      return upload(bo, inputStream);
    }
  }

  @Override
  public PageableQueryResult<StorageBO> listFile(Integer pageIndex, Integer pageSize) {
    Query query = new Query().with(PageRequest.of(pageIndex.intValue(), pageSize.intValue()))
        .with(Sort
            .by(Direction.DESC, "id"));
    List<StorageEntity> storageEntities = mongoTemplate.find(query, StorageEntity.class);
    long count = mongoTemplate.count(query, StorageEntity.class);
    PageableQueryResult<StorageBO> result = new PageableQueryResult<>();
    List<StorageBO> resultElements = result.getElements();
    for (StorageEntity storageEntity : storageEntities) {
      StorageBO storageBO = new StorageBO();
      BeanUtils.copyProperties(storageEntity, storageBO);
      resultElements.add(storageBO);
    }
    result.setIndex(pageIndex.longValue());
    result.setSize(pageSize.longValue());
    result.setTotal(count);
    return result;
  }

  @Override
  public StorageBO findOneById(String id) {
    Query query = new Query(Criteria.where("id").is(id));
    StorageEntity one = mongoTemplate.findOne(query, StorageEntity.class);
    StorageBO storageBO = new StorageBO();
    BeanUtils.copyProperties(one, storageBO);
    return storageBO;
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

  private StorageBO upload(StorageBO bo, InputStream inputStream) throws Exception {
    Long id = new Snowflake().nextId();
    bo.setObjectName(id.toString() + "." + bo.getFileSuffix());
    minIOClient.putObject(bo.getBucketName(), bo.getObjectName(), inputStream, null, null, null,
        bo.getFileType());
    String url = minIOClient.getObjectUrl(bo.getBucketName(), bo.getObjectName());
    StorageEntity storageEntity = new StorageEntity();
    BeanUtils.copyProperties(bo, storageEntity);
    storageEntity.setUrl(url);
    storageEntity.setPath(url);
    storageEntity.setStatus(0);
    storageEntity.setCreateTime(System.currentTimeMillis());
    storageEntity.setUpdateTime(System.currentTimeMillis());
    mongoTemplate.save(storageEntity);
    StorageBO storageBO = new StorageBO();
    BeanUtils.copyProperties(storageEntity, storageBO);
    return storageBO;
  }

}
