package pwd.initializr.generator.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pwd.initializr.common.web.api.ApiSwagger2;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * pwd.initializr.account.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 21:10
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "pwd.initializr.generator.api")
public class Swagger2 extends ApiSwagger2 {

  @Override
  protected Customer adminApiCustomer() {
    return new Customer("AdminApi", "AdminApi", "管理接口", "pwd.initializr.automatic.api");
  }

  @Override
  protected Customer robotApiCustomer() {
    return new Customer("RobotApi", "RobotApi", "机器接口", "pwd.initializr.automatic.api");
  }

  @Override
  protected Customer userApiCustomer() {
    return new Customer("UserApi", "UserApi", "用户接口", "pwd.initializr.automatic.api");
  }

  @Override
  public UiConfigurationBuilder uiConfig() {
    return UiConfigurationBuilder.builder();
  }
}
