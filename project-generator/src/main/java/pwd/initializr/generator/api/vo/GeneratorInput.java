package pwd.initializr.generator.api.vo;

import io.swagger.annotations.ApiModel;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.generator.api.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-10 12:46
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "generatorInput", description = "代码生成请求参数")
public class GeneratorInput extends DatabaseInput {

    private String projectName = "project-generator-test";
    private String packageName = "me.taoqigui.automatic";
    private String projectVersion = "0.0.1-SNAPSHOT";
    private String applicationName = "AutomaticTestApplication";
    private Set<String> tables;
}
