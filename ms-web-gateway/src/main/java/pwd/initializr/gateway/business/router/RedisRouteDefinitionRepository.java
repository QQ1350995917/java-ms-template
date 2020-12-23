package pwd.initializr.gateway.business.router;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
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
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

  public static final String GATEWAY_ROUTES = "geteway_routes";

  @Resource
  private RedisTemplate redisTemplate;

  @Override
  public Flux<RouteDefinition> getRouteDefinitions() {
    List<RouteDefinition> routeDefinitions = new ArrayList<>();
    redisTemplate.opsForHash().values(GATEWAY_ROUTES).stream()
        .forEach(routeDefinition -> routeDefinitions.add(
            JSON.parseObject(routeDefinition.toString(), RouteDefinition.class)));
    return Flux.fromIterable(routeDefinitions);
  }

  @Override
  public Mono<Void> save(Mono<RouteDefinition> route) {
    return route.flatMap(r -> {
      redisTemplate.opsForHash().put(GATEWAY_ROUTES, r.getId(), r);
      return Mono.empty();
    });
  }

  @Override
  public Mono<Void> delete(Mono<String> routeId) {
    return routeId.flatMap(id -> {
      boolean remove = redisTemplate.opsForHash().values(GATEWAY_ROUTES).remove(id);
      if (remove) {
        return Mono.empty();
      }
      return Mono.defer(() -> Mono.error(
          new NotFoundException("RouteDefinition not found: " + routeId)));
    });
  }
}
