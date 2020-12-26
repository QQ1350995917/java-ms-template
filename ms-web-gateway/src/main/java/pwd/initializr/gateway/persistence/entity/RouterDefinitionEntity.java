package pwd.initializr.gateway.persistence.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
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
  private String predicatesJsonString;
  private String filtersJsonString;

  public String getFiltersJsonString() {
    if (StringUtils.isBlank(filtersJsonString)) {
      return JSON.toJSONString(getFilters());
    } else {
      return filtersJsonString;
    }
  }

  public String getPredicatesJsonString() {
    if (StringUtils.isBlank(predicatesJsonString)) {
      return JSON.toJSONString(getPredicates());
    } else {
      return predicatesJsonString;
    }
  }

  @Override
  public List<PredicateDefinition> getPredicates() {
    if (StringUtils.isBlank(predicatesJsonString)) {
      return super.getPredicates();
    } else {
      return JSON.parseObject(predicatesJsonString, new TypeReference<List<PredicateDefinition>>() {
      });
    }
  }

  @Override
  public List<FilterDefinition> getFilters() {
    if (StringUtils.isBlank(filtersJsonString)) {
      return super.getFilters();
    } else {
      return JSON.parseObject(filtersJsonString, new TypeReference<List<FilterDefinition>>() {
      });
    }
  }

  @Override
  public URI getUri() {
    if (StringUtils.isBlank(this.url)) {
      return super.getUri();
    } else {
      return URI.create(this.getUrl());
    }

  }

  public String getUrl() {
    if (StringUtils.isBlank(url)) {
      return getUri().toString();
    }
    return url;
  }
}
