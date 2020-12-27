package pwd.initializr.gateway.persistence.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * pwd.initializr.gateway.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-26 21:51
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RouterDefinitionEntity extends RouteDefinition {

  private String url;
  @JSONField(serialize = false)
  private String predicatesJsonString;
  @JSONField(serialize = false)
  private String filtersJsonString;

  public void setFiltersJsonString(String filtersJsonString) {
    this.filtersJsonString = filtersJsonString;
    super.setFilters(
        JSON.parseObject(filtersJsonString, new TypeReference<List<FilterDefinition>>() {
        }));
  }

  @Override
  public void setPredicates(List<PredicateDefinition> predicates) {
    super.setPredicates(predicates);
    this.predicatesJsonString = JSON.toJSONString(predicates);
  }

  @Override
  public void setFilters(List<FilterDefinition> filters) {
    super.setFilters(filters);
    this.filtersJsonString = JSON.toJSONString(filters);
  }

  @Override
  public void setUri(URI uri) {
    super.setUri(uri);
    this.url = uri.toString();
  }

  public void setPredicatesJsonString(String predicatesJsonString) {
    this.predicatesJsonString = predicatesJsonString;
    super.setPredicates(
        JSON.parseObject(predicatesJsonString, new TypeReference<List<PredicateDefinition>>() {
        }));
  }

  public void setUrl(String url) {
    this.url = url;
    super.setUri(URI.create(url));
  }
}
