package pwd.initializr.etl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * pwd.initializr.etl@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-18 21:16
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Lazy(false)
public class ETLApplicationContextAware implements ApplicationContextAware {


  private static ApplicationContext APPLICATION_CONTEXT;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    APPLICATION_CONTEXT = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return APPLICATION_CONTEXT;
  }

  public static <T> T getBean(Class<T> classz) {
    return APPLICATION_CONTEXT.getBean(classz);
  }
}
