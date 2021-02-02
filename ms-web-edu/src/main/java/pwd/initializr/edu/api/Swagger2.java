package pwd.initializr.edu.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pwd.initializr.common.web.api.ApiSwagger2;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* project-generator-test-20210202183035372@ms-web-initializr
*
* <h1>project-generator-test-20210202183035372接口文档声明</h1>
*
* date 2021-02-02 18:30
*
* @author Automatic[www.dingpengwei@foxmail.com]
* @version 0.0.1-SNAPSHOT
* @since 0.0.1-SNAPSHOT
*/
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "pwd.initializr.edu.api")
public class Swagger2 extends ApiSwagger2 {

  @Override
  protected Customer adminApiCustomer() {
    return new Customer("AdminApi", "AdminApi", "管理接口", "pwd.initializr.edu.api");
  }

  @Override
  protected Customer robotApiCustomer() {
    return new Customer("RobotApi", "RobotApi", "机器接口", "pwd.initializr.edu.api");
  }

  @Override
  protected Customer userApiCustomer() {
    return new Customer("UserApi", "UserApi", "用户接口", "pwd.initializr.edu.api");
  }

  @Override
  public UiConfigurationBuilder uiConfig() {
    return UiConfigurationBuilder.builder();
  }
}
