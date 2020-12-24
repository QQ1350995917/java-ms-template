package pwd.initializr.gateway.business.router;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.business.router@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-23 22:20
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

  @Resource
  protected RouteLocator routeLocator;
  @Resource
  private RouteDefinitionWriter routeDefinitionWriter;
  private ApplicationEventPublisher publisher;

  /**
   * <h2>删除路由</h2>
   * date 2020-12-23 22:28
   *
   * @param id 路由ID
   * @return reactor.core.publisher.Mono<java.lang.String>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public Mono<String> delete(String id) {
    this.routeDefinitionWriter.delete(Mono.just(id));
    this.publisher.publishEvent(new RefreshRoutesEvent(this));
    return Mono.just(id);
  }

  public Flux<RouteDefinition> list() {
    return this.routeLocator.getRoutes().map(this::serialize);
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  /**
   * <h2>更新路由</h2>
   * date 2020-12-23 22:25
   *
   * @param definition 路由对象
   * @return reactor.core.publisher.Mono<org.springframework.cloud.gateway.route.RouteDefinition>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public Mono<RouteDefinition> update(RouteDefinition definition) {
    return create(definition);
  }

  /**
   * <h2>新增路由</h2>
   * date 2020-12-23 22:22
   *
   * @param definition 路由对象
   * @return reactor.core.publisher.Mono<org.springframework.cloud.gateway.route.RouteDefinition>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public Mono<RouteDefinition> create(RouteDefinition definition) {
    routeDefinitionWriter.save(Mono.just(definition)).subscribe();
    this.publisher.publishEvent(new RefreshRoutesEvent(this));
    return Mono.just(definition);
  }

  RouteDefinition serialize(Route route) {
    RouteDefinition routeDefinition = new RouteDefinition();
    routeDefinition.setId(route.getId());
    routeDefinition.setOrder(route.getOrder());
    routeDefinition.setUri(route.getUri());


//    routeDefinition.setPredicates();
    ArrayList<FilterDefinition> filters = new ArrayList<>();
    for (int i = 0; i < route.getFilters().size(); i++) {
      FilterDefinition filterDefinition = new FilterDefinition();
    }
    routeDefinition.setFilters(filters);
    return routeDefinition;
  }
}
