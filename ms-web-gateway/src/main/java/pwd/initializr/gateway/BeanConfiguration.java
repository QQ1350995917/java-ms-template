package pwd.initializr.gateway;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.validation.Validator;
import pwd.initializr.gateway.business.filter.SessionWhiteListFilterServiceImpl;
import pwd.initializr.gateway.business.limiter.CustomerRedisRateLimiter;
import pwd.initializr.gateway.business.router.DynamicRouteServiceImpl;

/**
 * pwd.initializr.gateway@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-25 15:01
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
public class BeanConfiguration {

    @Value("${gateway.router.in.redis.sync.topic}")
    public String GATEWAY_ROUTES_IN_REDIS_SYNC_TOPIC;
    @Value("${gateway.filter.global.session.in.redis.sync.topic}")
    public String GATEWAY_FILTER_GLOBAL_SESSION_IN_REDIS_SYNC_TOPIC;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        /*hash字符串序列化方法*/
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisMessageListenerContainer initRedisContainer(
        DynamicRouteServiceImpl dynamicRouteService,
        SessionWhiteListFilterServiceImpl sessionFilterService,
        RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        ChannelTopic routerTopic = new ChannelTopic(GATEWAY_ROUTES_IN_REDIS_SYNC_TOPIC);
        ChannelTopic sessionTopic = new ChannelTopic(GATEWAY_ROUTES_IN_REDIS_SYNC_TOPIC);
        container.addMessageListener(dynamicRouteService, routerTopic);
        container.addMessageListener(sessionFilterService, sessionTopic);
        container.setTaskExecutor(Executors.newFixedThreadPool(2));
        return container;
    }

    @Bean
    @Primary
    public CustomerRedisRateLimiter customRedisRateLimiter(
        ReactiveRedisTemplate<String, String> redisTemplate,
        @Qualifier(CustomerRedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> redisScript,
        Validator validator) {
        return new CustomerRedisRateLimiter(redisTemplate, redisScript, validator);
    }

}
