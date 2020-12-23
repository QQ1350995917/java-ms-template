package pwd.initializr.gateway.api;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.gateway.business.router.DynamicRouteServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-12 16:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController()
@RequestMapping("/router")
public class RouterController {

  @Resource
  private DynamicRouteServiceImpl dynamicRouteService;

  /**
   * <h2>新增路由</h2>
   * date 2020-12-23 22:18
   *
   * @param routeDefinition 路由对象
   * @return reactor.core.publisher.Flux<java.util.List                                                                                                                               <
               *       org.springframework.cloud.gateway.route.RouteDefinition>>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Flux<RouteDefinition> craete(@RequestBody RouteDefinition routeDefinition) {
    dynamicRouteService.create(routeDefinition);
    return Flux.just(routeDefinition);
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<List<RouteDefinition>> index() {
    return dynamicRouteService.list().collectList();
  }

}
