package pwd.initializr.gateway.router;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pwd.initializr.gateway.router.vo.DynamicFilterDefinition;
import pwd.initializr.gateway.router.vo.DynamicPredicateDefinition;
import pwd.initializr.gateway.router.vo.DynamicRouterDefinition;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.router@ms-web-initializr
 *
 * <h1>https://blog.csdn.net/zhuyu19911016520/article/details/86562615</h1>
 *
 * date 2020-12-12 15:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController
@RequestMapping("/route")
public class DynamicRouterController {
    @Autowired
    private DynamicRouterService dynamicRouterService;

    //增加路由
    @PostMapping("/add")
    public String add(@RequestBody DynamicRouterDefinition dynamicRouterDefinition) {
        String flag = "fail";
        try {
            RouteDefinition definition = assembleRouteDefinition(dynamicRouterDefinition);
            flag = this.dynamicRouterService.add(definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    //删除路由
    @DeleteMapping("/routes/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        try {
            return this.dynamicRouterService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //更新路由
    @PostMapping("/update")
    public String update(@RequestBody DynamicRouterDefinition dynamicRouterDefinition) {
        RouteDefinition definition = assembleRouteDefinition(dynamicRouterDefinition);
        return this.dynamicRouterService.update(definition);
    }

    //把传递进来的参数转换成路由对象
    private RouteDefinition assembleRouteDefinition(DynamicRouterDefinition dynamicRouterDefinition) {
        RouteDefinition definition = new RouteDefinition();
        definition.setId(dynamicRouterDefinition.getId());
        definition.setOrder(dynamicRouterDefinition.getOrder());

        //设置断言
        List<PredicateDefinition> pdList=new ArrayList<>();
        List<DynamicPredicateDefinition> gatewayPredicateDefinitionList=dynamicRouterDefinition.getPredicates();
        for (DynamicPredicateDefinition gpDefinition: gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);

        //设置过滤器
        List<FilterDefinition> filters = new ArrayList();
        List<DynamicFilterDefinition> gatewayFilters = dynamicRouterDefinition.getFilters();
        for(DynamicFilterDefinition filterDefinition : gatewayFilters){
            FilterDefinition filter = new FilterDefinition();
            filter.setName(filterDefinition.getName());
            filter.setArgs(filterDefinition.getArgs());
            filters.add(filter);
        }
        definition.setFilters(filters);

        URI uri = null;
        if(dynamicRouterDefinition.getUri().startsWith("http")){
            uri = UriComponentsBuilder.fromHttpUrl(dynamicRouterDefinition.getUri()).build().toUri();
        }else{
            // uri为 lb://consumer-service 时使用下面的方法
            uri = URI.create(dynamicRouterDefinition.getUri());
        }
        definition.setUri(uri);
        return definition;
    }
}
