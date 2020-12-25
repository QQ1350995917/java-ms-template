package pwd.initializr.gateway.business.router;

import javax.annotation.Resource;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import pwd.initializr.gateway.business.router.bo.RouteDefinitionBO;
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
  protected RouteDefinitionLocator routeLocator;
  @Resource
  private RouteDefinitionWriter routeDefinitionWriter;
  private ApplicationEventPublisher publisher;
  private Long serialNumber = 0L;

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

  public Mono<RouteDefinitionBO> list() {
    return this.routeLocator.getRouteDefinitions()
        .map(this::serialize).collectList()
        .map(routeDefinitions -> new RouteDefinitionBO(serialNumber, routeDefinitions));
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  /**
   * <h2>更新路由</h2>
   * date 2020-12-23 22:25
   *
   * @param serialNumber 路由版本
   * @param definition 路由对象
   * @return reactor.core.publisher.Mono<org.springframework.cloud.gateway.route.RouteDefinition>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public Mono<Long> update(Long serialNumber, RouteDefinition definition) {
    return create(serialNumber, definition);
  }

  /**
   * <h2>新增路由</h2>
   * date 2020-12-23 22:22
   *
   * @param serialNumber 路由版本
   * @param definition 路由对象
   * @return reactor.core.publisher.Mono<org.springframework.cloud.gateway.route.RouteDefinition>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public Mono<Long> create(Long serialNumber, RouteDefinition definition) {
    routeDefinitionWriter.save(Mono.just(definition)).subscribe();
    this.publisher.publishEvent(new RefreshRoutesEvent(this));
    return Mono.just(++serialNumber);
  }

  RouteDefinition serialize(RouteDefinition routeDefinition) {
    return routeDefinition;
  }


}
