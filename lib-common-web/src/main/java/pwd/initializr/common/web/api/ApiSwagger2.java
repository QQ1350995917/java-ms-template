package pwd.initializr.common.web.api;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
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

/**
 * pwd.initializr.common.web.api@ms-web-initializr
 *
 * <h1>通用API文档设置</h1>
 *
 * date 2019-09-14 15:24
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class ApiSwagger2 {

  @Bean
  public Docket createAdminApi() {
    Customer customer = adminApiCustomer();
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName(customer.getGroupName())
        .apiInfo(apiInfo(customer.getInfoTitle(), customer.getInfoDesc()))
        .select()
        .apis(RequestHandlerSelectors.basePackage(customer.getBasePackage()))
        .paths(PathSelectors.any())
        .build().globalOperationParameters(buildGlobalOperationParameters());
  }

  protected abstract Customer adminApiCustomer();

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

  @Bean
  public Docket createRobotApi() {
    Customer customer = robotApiCustomer();
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName(customer.getGroupName())
        .apiInfo(apiInfo(customer.getInfoTitle(), customer.getInfoDesc()))
        .select()
        .apis(RequestHandlerSelectors.basePackage(customer.getBasePackage()))
        .paths(PathSelectors.any())
        .build().globalOperationParameters(buildGlobalOperationParameters());
  }

  protected abstract Customer robotApiCustomer();

  @Bean
  public Docket createUserApi() {
    Customer customer = userApiCustomer();
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName(customer.getGroupName())
        .apiInfo(apiInfo(customer.getInfoTitle(), customer.getInfoDesc()))
        .select()
        .apis(RequestHandlerSelectors.basePackage(customer.getBasePackage()))
        .paths(PathSelectors.any())
        .build().globalOperationParameters(buildGlobalOperationParameters());
  }

  protected abstract Customer userApiCustomer();

  @Bean
  public abstract UiConfigurationBuilder uiConfig();

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @ToString
  protected class Customer {

    private String groupName;
    private String infoTitle;
    private String infoDesc;
    private String basePackage;
  }

}
