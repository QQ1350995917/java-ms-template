package pwd.initializr.gateway.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import pwd.initializr.gateway.api.vo.DynamicFilterDefinition;
import pwd.initializr.gateway.api.vo.DynamicPredicateDefinition;
import pwd.initializr.gateway.api.vo.DynamicRouterDefinition;
import pwd.initializr.gateway.business.RouterService;
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
@Controller()
@RequestMapping("/router")
public class RouterController {

  @Autowired
  private RouterService routerService;

  //增加路由
  @PostMapping("")
  @ResponseBody
  public String create(@RequestBody DynamicRouterDefinition dynamicRouterDefinition) {
    String flag = "fail";
    try {
      RouteDefinition definition = assembleRouteDefinition(dynamicRouterDefinition);
      flag = this.routerService.add(definition);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }

  //删除路由
  @DeleteMapping("/routes/{id}")
  @ResponseBody
  public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
    try {
      return this.routerService.delete(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @GetMapping("")
  public Mono<String> indexPage(final Model model) {
    model.addAttribute("key1", "hello");
    model.addAttribute("key2", "word");
    return Mono.create(mono -> mono.success("index"));
  }

  @GetMapping("/all")
  @ResponseBody
  public String list() {

    return "index";
  }

  //更新路由
  @PostMapping("/update")
  @ResponseBody
  public String update(@RequestBody DynamicRouterDefinition dynamicRouterDefinition) {
    RouteDefinition definition = assembleRouteDefinition(dynamicRouterDefinition);
    return this.routerService.update(definition);
  }


  //把传递进来的参数转换成路由对象
  private RouteDefinition assembleRouteDefinition(DynamicRouterDefinition dynamicRouterDefinition) {
    RouteDefinition definition = new RouteDefinition();
    definition.setId(dynamicRouterDefinition.getId());
    definition.setOrder(dynamicRouterDefinition.getOrder());

    //设置断言
    List<PredicateDefinition> pdList = new ArrayList<>();
    List<DynamicPredicateDefinition> gatewayPredicateDefinitionList = dynamicRouterDefinition
        .getPredicates();
    for (DynamicPredicateDefinition gpDefinition : gatewayPredicateDefinitionList) {
      PredicateDefinition predicate = new PredicateDefinition();
      predicate.setArgs(gpDefinition.getArgs());
      predicate.setName(gpDefinition.getName());
      pdList.add(predicate);
    }
    definition.setPredicates(pdList);

    //设置过滤器
    List<FilterDefinition> filters = new ArrayList();
    List<DynamicFilterDefinition> gatewayFilters = dynamicRouterDefinition.getFilters();
    for (DynamicFilterDefinition filterDefinition : gatewayFilters) {
      FilterDefinition filter = new FilterDefinition();
      filter.setName(filterDefinition.getName());
      filter.setArgs(filterDefinition.getArgs());
      filters.add(filter);
    }
    definition.setFilters(filters);

    URI uri = null;
    if (dynamicRouterDefinition.getUri().startsWith("http")) {
      uri = UriComponentsBuilder.fromHttpUrl(dynamicRouterDefinition.getUri()).build().toUri();
    } else {
      // uri为 lb://consumer-service 时使用下面的方法
      uri = URI.create(dynamicRouterDefinition.getUri());
    }
    definition.setUri(uri);
    return definition;
  }

}
