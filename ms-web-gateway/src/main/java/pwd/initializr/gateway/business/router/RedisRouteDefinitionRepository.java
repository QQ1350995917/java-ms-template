package pwd.initializr.gateway.business.router;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.business.router@ms-web-initializr
 *
 * <h1>https://blog.csdn.net/tianyaleixiaowu/article/details/83412301</h1>
 *
 * date 2020-12-12 14:10
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Slf4j
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

  @Value("${gateway.router.in.redis.key.name}")
  public String GATEWAY_ROUTES_IN_REDIS_KEY_NAME;

  @Resource
  private RedisTemplate<String, String> redisTemplate;

  @Override
  public Flux<RouteDefinition> getRouteDefinitions() {
    List<RouteDefinition> routeDefinitions = new ArrayList<>();
    redisTemplate.opsForHash().values(GATEWAY_ROUTES_IN_REDIS_KEY_NAME).stream()
        .forEach(routeDefinition -> routeDefinitions.add(
            JSON.parseObject(routeDefinition.toString(), RouteDefinition.class)));
    return Flux.fromIterable(routeDefinitions);
  }

  @Override
  public Mono<Void> save(Mono<RouteDefinition> route) {
    route.flatMap(routeDefinition -> {
      log.info(routeDefinition.toString());
      redisTemplate.opsForHash()
          .put(GATEWAY_ROUTES_IN_REDIS_KEY_NAME, routeDefinition.getId(),
              JSON.toJSONString(routeDefinition, new SerializeFilter[]{}));
      return Mono.empty();
    }).subscribe();
    return Mono.empty();
  }

  @Override
  public Mono<Void> delete(Mono<String> routeId) {
    routeId.map(id -> redisTemplate.opsForHash().delete(GATEWAY_ROUTES_IN_REDIS_KEY_NAME,id)).subscribe();
    return Mono.empty();
  }
}
