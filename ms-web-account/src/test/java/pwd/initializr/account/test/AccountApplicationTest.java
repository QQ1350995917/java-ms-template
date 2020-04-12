package pwd.initializr.account.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.mw.redis.RedisClient;

/**
 * pwd.initializr.account@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-13 22:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AccountApplicationTest {


    @Test
    public void contextLoads() {
    }
    @Autowired
    private RedisClient redisClient;

    @Test
    public void testRedisClient(){
        redisClient.set("pwd","xxx",0);
    }

}
