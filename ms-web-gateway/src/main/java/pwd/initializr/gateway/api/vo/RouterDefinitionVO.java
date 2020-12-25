package pwd.initializr.gateway.api.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.gateway.route.RouteDefinition;
import pwd.initializr.gateway.business.router.bo.RouteDefinitionBO;

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
public class RouterDefinitionVO extends RouteDefinitionBO {


  private Long serialNumber;
  private List<RouteDefinition> routeDefinitions;

  //    /**
//     * 路由的Id
//     */
//    private String id;
//    /**
//     * 路由规则转发的目标uri
//     */
//    private String uri;
//    /**
//     * 路由执行的顺序
//     */
//    private int order = 0;
//    /**
//     * 路由断言集合配置
//     */
//
//    private List<DynamicPredicateDefinition> predicates = new ArrayList<>();
//    /**
//     * 路由过滤器集合配置
//     */
//    private List<DynamicFilterDefinition> filters = new ArrayList<>();
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        RouterDefinitionVO routeDefinition = (RouterDefinitionVO) o;
//        return Objects.equals(id, routeDefinition.id)
//            && Objects.equals(predicates, routeDefinition.predicates)
//            && Objects.equals(order, routeDefinition.order)
//            && Objects.equals(uri, routeDefinition.uri);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, predicates, uri);
//    }
//
//    @Override
//    public String toString() {
//        return "RouteDefinition{" + "id='" + id + '\'' + ", predicates=" + predicates
//            + ", filters=" + filters + ", uri=" + uri + ", order=" + order + '}';
//    }
}
