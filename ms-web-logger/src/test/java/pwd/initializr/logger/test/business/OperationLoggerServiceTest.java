package pwd.initializr.logger.test.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.logger.business.OperationLoggerService;
import pwd.initializr.logger.business.bo.OperationLogger;

/**
 * pwd.initializr.logger.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 12:47
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationLoggerServiceTest {

  @Autowired
  private OperationLoggerService operationLoggerService;
  @Test
  public void saveTest() {
    OperationLogger operationLogger = new OperationLogger();
    operationLogger.setUserId("pwd");
    OperationLogger save = operationLoggerService.save(operationLogger);
    System.out.println(save);
  }
}
