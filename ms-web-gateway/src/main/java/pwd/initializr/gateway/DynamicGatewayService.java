package pwd.initializr.gateway;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-04 08:27
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 * https://my.oschina.net/tongyufu/blog/1844573
 */
@Service
public class DynamicGatewayService implements ApplicationEventPublisherAware {
  @Autowired
  private RouteDefinitionWriter routeDefinitionWriter;
  private ApplicationEventPublisher publisher;

  public String save() {
    RouteDefinition definition = new RouteDefinition();
    PredicateDefinition predicate = new PredicateDefinition();
    Map<String, String> predicateParams = new HashMap<>(8);

    definition.setId("accountRouter");
    predicate.setName("Path");
    //请替换成本地可访问的路径
    predicateParams.put("pattern", "/account");
    //请替换成本地可访问的路径
    predicateParams.put("pathPattern", "/account");
    predicate.setArgs(predicateParams);
    definition.setPredicates(Arrays.asList(predicate));
    //请替换成本地可访问的域名
    URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:11230").build().toUri();
    definition.setUri(uri);
    routeDefinitionWriter.save(Mono.just(definition)).subscribe();
    this.publisher.publishEvent(new RefreshRoutesEvent(this));
    return "success";
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }
}
