package pwd.initializr.etl.plugin;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pwd.initializr.etl.ETLDefaultHandler;

/**
 * pwd.initializr.etl.plugin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-14 14:49
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETDBInsert extends ETLDefaultHandler {

  @Override
  public void init() {

  }

  @Override
  public void handle(Object object) {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new ETDBInsert().init();
  }
}
