package pwd.initializr.organization.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
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
@ComponentScan(basePackages = "pwd.initializr.organization.api")
public class Swagger2 {

    @Bean
    public Docket createUserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("UserApi")
            .apiInfo(apiInfo("UserApi", "Organization用户接口"))
            .select()
            .apis(RequestHandlerSelectors.basePackage("pwd.initializr.organization.api.user"))
            .paths(PathSelectors.any())
            .build();
    }

    @Bean
    public Docket createAdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("AdminApi")
            .apiInfo(apiInfo("AdminApi", "Organization管理接口"))
            .select()
            .apis(RequestHandlerSelectors.basePackage("pwd.initializr.organization.api.admin"))
            .paths(PathSelectors.any())
            .build();
    }


    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
            .title(title)
            .description(description)
            .termsOfServiceUrl("localhost:8080/")
            .version("1.0")
            .contact(new Contact("DingPengwei", "", "www.dingpengwei@foxmail"))
            .build();
    }

    @Bean
    public UiConfigurationBuilder uiConfig() {
        return UiConfigurationBuilder.builder();
    }
}