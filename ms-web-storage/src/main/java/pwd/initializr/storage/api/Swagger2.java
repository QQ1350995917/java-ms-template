package pwd.initializr.storage.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pwd.initializr.common.web.api.ApiSwagger2;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * pwd.initializr.logger.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-15 10:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "pwd.initializr.storage.api")
public class Swagger2 extends ApiSwagger2 {

  @Bean
  public Docket createAdminApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("AdminApi")
        .apiInfo(apiInfo("AdminApi", "管理接口"))
        .select()
        .apis(RequestHandlerSelectors.basePackage("pwd.initializr.storage.api.admin"))
        .paths(PathSelectors.any())
        .build().globalOperationParameters(buildGlobalOperationParameters());
  }

  @Bean
  public Docket createRobotApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("RobotApi")
        .apiInfo(apiInfo("RobotApi", "管理接口"))
        .select()
        .apis(RequestHandlerSelectors.basePackage("pwd.initializr.storage.api.robot"))
        .paths(PathSelectors.any())
        .build().globalOperationParameters(buildGlobalOperationParameters());
  }

  @Bean
  public Docket createUserApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("UserApi")
        .apiInfo(apiInfo("UserApi", "用户接口"))
        .select()
        .apis(RequestHandlerSelectors.basePackage("pwd.initializr.storage.api.user"))
        .paths(PathSelectors.any())
        .build().globalOperationParameters(buildGlobalOperationParameters());
  }
}
