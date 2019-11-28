package pwd.initializr.logger.business;

import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.logger.business.bo.OperationLogger;

/**
 * pwd.initializr.logger.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 11:41
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OperationLoggerService {

  OperationLogger save(OperationLogger operationLogger);

  ObjectList<OperationLogger> list(Integer pageSize, Long lastId);

  ObjectList<OperationLogger> list(Integer pageSize, Long lastId, String keyWord);

  ObjectList<OperationLogger> listByUserId(Long userId, Integer pageSize, Long lastId);

  ObjectList<OperationLogger> listByUserId(Long userId, Integer pageSize, Long lastId,
      String keyWord);
}
