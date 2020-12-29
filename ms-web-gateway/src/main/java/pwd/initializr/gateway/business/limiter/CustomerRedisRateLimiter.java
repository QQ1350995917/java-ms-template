package pwd.initializr.gateway.business.limiter;

import java.util.List;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;

/**
 * pwd.initializr.gateway.business.limiter@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-29 15:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CustomerRedisRateLimiter extends RedisRateLimiter {

    public CustomerRedisRateLimiter(
        ReactiveRedisTemplate<String, String> redisTemplate,
        RedisScript<List<Long>> script, Validator validator) {
        super(redisTemplate, script, validator);
    }

    public CustomerRedisRateLimiter(int defaultReplenishRate, int defaultBurstCapacity) {
        super(defaultReplenishRate, defaultBurstCapacity);
    }
}
