package pwd.initializr.logger.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.logger.business.bo.OperationLogger;

/**
 * pwd.initializr.logger.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 11:36
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OperationLoggerServiceImpl implements OperationLoggerService {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public OperationLogger save(OperationLogger operationLogger) {
    OperationLogger result = mongoTemplate.save(operationLogger);
    return result;
  }

  @Override
  public ObjectList<OperationLogger> list(Integer pageSize, Long lastId) {
    return null;
  }

  @Override
  public ObjectList<OperationLogger> list(Integer pageSize, Long lastId, String keyWord) {
    return null;
  }

  @Override
  public ObjectList<OperationLogger> listByUserId(Long userId, Integer pageSize, Long lastId) {
    return null;
  }

  @Override
  public ObjectList<OperationLogger> listByUserId(Long userId, Integer pageSize, Long lastId,
      String keyWord) {
    return null;
  }
}
