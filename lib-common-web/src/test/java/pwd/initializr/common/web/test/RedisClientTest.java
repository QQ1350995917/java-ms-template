package pwd.initializr.common.web.test;


import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.mw.redis.RedisClient;
import redis.clients.jedis.JedisPool;

/**
 * pwd.initializr.common.web.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 21:06
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClientTest {
  @Autowired
  private RedisClient redisClient;

  @Test
  public void testRedisClient(){
    redisClient.set("pwd","hello:" + new Date().getTime(),0);
  }
}
