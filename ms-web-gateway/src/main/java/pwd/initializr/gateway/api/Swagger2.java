package pwd.initializr.gateway.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * pwd.initializr.gateway.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-13 20:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
public class Swagger2 {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo())
        .select()
//        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .apis(RequestHandlerSelectors.basePackage("pwd.initializr.gateway.api"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Swagger3接口文档")
        .description("文档描述")
        .contact(new Contact("DingPengwei", "pwd", "www.dingpengwei@foxmail.com"))
        .version("1.0")
        .build();
  }

}
