package pwd.initializr.common.web.api;

import java.util.ArrayList;
import java.util.List;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

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
//@Configuration
//@EnableSwagger2
public class Swagger2 {

    //    @Bean
    public Docket createTestApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameterBuilder
            .name("x-ac-uid")
            .description("userId")
            .modelRef(new ModelRef("long"))
            .parameterType("header")
            .required(false)
            .name("x-ac-vc")
            .description("versionCode")
            .modelRef(new ModelRef("long"))
            .parameterType("header")
            .required(false)
            .name("x-a-p")
            .description("platform")
            .modelRef(new ModelRef("long"))
            .parameterType("header")
            .required(false)
            .build();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_12)
            .groupName("Test")
            .apiInfo(apiInfo("TestApi", "TestApi"))
            .select()
            .apis(RequestHandlerSelectors.basePackage("pwd.common.api.test"))
            .paths(PathSelectors.any())
            .build().globalOperationParameters(parameters);
    }



    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
            .title(title)
            .description(description)
            .termsOfServiceUrl("localhost:8080/")
            .version("1.0")
            .build();
    }

    //    @Bean
    public UiConfigurationBuilder uiConfig() {
        return UiConfigurationBuilder.builder();
    }
}
