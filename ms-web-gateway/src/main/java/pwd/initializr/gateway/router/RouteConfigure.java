package pwd.initializr.gateway.router;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * pwd.initializr.gateway.router@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-12 14:55
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class RouteConfigure {


    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        ThreadPoolTaskScheduler taskPool = new ThreadPoolTaskScheduler();
        taskPool.setPoolSize(20);
        return taskPool;
    }

    @Bean
    public RedisMessageListenerContainer initRedisContainer(RedisRouteDefinitionRepositoryListener listener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //container.setConnectionFactory(jedisConnectionFactory());
        container.setTaskExecutor(initTaskScheduler());
        //定义监听渠道
        ChannelTopic topic = new ChannelTopic("topic1");
        container.addMessageListener(listener,topic);
        return container;
    }
}
