package pwd.initializr.monitor.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pwd.initializr.common.web.api.ApiSwagger2;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* project-generator-test@ms-web-initializr
*
* <h1>project-generator-test接口文档声明</h1>
*
* date 2020-10-29 11:44
*
* @author Automatic[www.dingpengwei@foxmail.com]
* @version 0.0.1-SNAPSHOT
* @since 0.0.1-SNAPSHOT
*/
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "pwd.initializr.monitor.api")
public class Swagger2 extends ApiSwagger2 {

  @Override
  protected Customer adminApiCustomer() {
    return new Customer("AdminApi", "AdminApi", "管理接口", "pwd.initializr.monitor.api.admin");
  }

  @Override
  protected Customer robotApiCustomer() {
    return new Customer("RobotApi", "RobotApi", "机器接口", "pwd.initializr.monitor.api.robot");
  }

  @Override
  protected Customer userApiCustomer() {
    return new Customer("UserApi", "UserApi", "用户接口", "pwd.initializr.monitor.api.user");
  }

  @Override
  public UiConfigurationBuilder uiConfig() {
    return UiConfigurationBuilder.builder();
  }
}
