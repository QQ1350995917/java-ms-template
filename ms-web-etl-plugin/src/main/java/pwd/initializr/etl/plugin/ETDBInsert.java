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

  private String resource = "mybatis-config.xml";
  private ETLMapper mapper;
  @Override
  public void init() {
    try (
        InputStream inputStream = Resources.getResourceAsStream(resource);
    ) {

      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      mapper = sqlSession.getMapper(ETLMapper.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void handle(Object object) {
    if (mapper != null) {
      mapper.selectRegisterEnterprise();
    }
  }

  public static void main(String[] args) {
    new ETDBInsert().init();
  }
}
