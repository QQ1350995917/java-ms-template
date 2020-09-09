package ${projectPackage}.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pwd.initializr.common.web.api.ApiSwagger2;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* ${projectName}@ms-web-initializr
*
* <h1>${projectName}接口文档声明</h1>
*
* date ${projectCreateDate}
*
* @author Automatic[www.dingpengwei@foxmail.com]
* @version ${projectVersion}
* @since ${projectVersion}
*/
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "${projectPackage}.api")
public class Swagger2 extends ApiSwagger2 {

  @Override
  protected Customer adminApiCustomer() {
    return new Customer("AdminApi", "AdminApi", "管理接口", "${projectPackage}.api.admin");
  }

  @Override
  protected Customer robotApiCustomer() {
    return new Customer("RobotApi", "RobotApi", "机器接口", "${projectPackage}.api.robot");
  }

  @Override
  protected Customer userApiCustomer() {
    return new Customer("UserApi", "UserApi", "用户接口", "${projectPackage}.api.user");
  }

  @Override
  public UiConfigurationBuilder uiConfig() {
    return UiConfigurationBuilder.builder();
  }
}
