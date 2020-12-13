package pwd.initializr.gateway.api.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

/**
 * pwd.initializr.gateway.business.router@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-12 15:36
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DynamicRouterDefinition {
    //路由的Id
    private String id;
    //路由断言集合配置
    private List<DynamicPredicateDefinition> predicates = new ArrayList<>();
    //路由过滤器集合配置
    private List<DynamicFilterDefinition> filters = new ArrayList<>();
    //路由规则转发的目标uri
    private String uri;
    //路由执行的顺序
    private int order = 0;
}
