package pwd.initializr.common.web.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * pwd.initializr.common.web.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:24
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@EnableSwagger2
public class ApiSwagger2 {

  @Bean
  public UiConfigurationBuilder uiConfig() {
    return UiConfigurationBuilder.builder();
  }

  // @Bean
  public Docket createTestApi() {
    return new Docket(DocumentationType.SWAGGER_12)
        .groupName("Test")
        .apiInfo(apiInfo("TestApi", "TestApi"))
        .select()
        .apis(RequestHandlerSelectors.basePackage("pwd.common.api.test"))
        .paths(PathSelectors.any())
        .build().globalOperationParameters(buildGlobalOperationParameters());
  }

  protected ApiInfo apiInfo(String title, String description) {
    return new ApiInfoBuilder()
        .title(title)
        .description(description)
        .termsOfServiceUrl("localhost:8080/")
        .version("1.0.0")
        .contact(new Contact("DingPengwei", "", "www.dingpengwei@foxmail"))
        .build();
  }


  protected List<Parameter> buildGlobalOperationParameters() {
    List<Parameter> globalOperationParameters = new ArrayList<>();
    globalOperationParameters.add(new ParameterBuilder()
        .name(ApiConstant.HTTP_HEADER_KEY_TOKEN).defaultValue("x-token")
        .description("认证信息")
        .modelRef(new ModelRef("String"))
        .parameterType("header")
        .required(true).build());
    globalOperationParameters.add(new ParameterBuilder()
        .name(ApiConstant.HTTP_HEADER_KEY_UID).defaultValue("1")
        .description("用户ID")
        .modelRef(new ModelRef("Long"))
        .parameterType("header")
        .required(true).build());
    globalOperationParameters.add(new ParameterBuilder()
        .name(ApiConstant.HTTP_HEADER_KEY_SERVICE_VERSION).defaultValue("1.0.0")
        .description("服务版本号")
        .modelRef(new ModelRef("String"))
        .parameterType("header")
        .required(true).build());
    globalOperationParameters.add(new ParameterBuilder()
        .name(ApiConstant.HTTP_HEADER_KEY_OS).defaultValue("iOS")
        .description("操作系统")
        .modelRef(new ModelRef("String"))
        .parameterType("header")
        .required(true).build());
    return globalOperationParameters;
  }

}
