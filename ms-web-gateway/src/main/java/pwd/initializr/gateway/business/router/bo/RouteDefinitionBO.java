package pwd.initializr.gateway.business.router.bo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * pwd.initializr.gateway.business.router.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-25 22:23
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RouteDefinitionBO {

  private Long serialNumber;
  private List<RouteDefinition> routeDefinitions;

}
