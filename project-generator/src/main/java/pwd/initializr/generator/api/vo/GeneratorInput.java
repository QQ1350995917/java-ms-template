package pwd.initializr.generator.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

  @ApiModelProperty(name = "projectName", value = "项目名称", required = true, example = "project-generator-test")
  @NotBlank(message = "项目名称不能为空")
  private String projectName = "project-generator-test";
  @ApiModelProperty(name = "packageName", value = "项目包名", required = true, example = "pwd.code.generator")
  @NotBlank(message = "项目包名不能为空")
  private String packageName = "pwd.code.generator";
  @ApiModelProperty(name = "projectVersion", value = "项目版本", required = true, example = "0.0.1-SNAPSHOT")
  @NotBlank(message = "项目版本不能为空")
  private String projectVersion = "0.0.1-SNAPSHOT";
  @ApiModelProperty(name = "applicationName", value = "启动类名", required = true, example = "CodeGeneratorApplication")
  @NotBlank(message = "启动类名不能为空")
  private String applicationName = "CodeGeneratorApplication";
  @ApiModelProperty(name = "applicationPort", value = "端口", required = true, example = "80")
  @NotBlank(message = "端口不能为空")
  private Integer applicationPort = 80;
  @ApiModelProperty(name = "tables", value = "指定表名", required = true, example = "[]")
  @NotEmpty(message = "表名不能为空")
  private Set<String> tables;
  @ApiModelProperty(name = "nullableMetaProperties", value = "表中可为空的元字段名", required = false, example = "[able,del,create_time,update_time,version]")
  @NotEmpty(message = "表中可为空的元字段名")
  private List<String> nullableMetaProperties = Arrays.asList("id","able","del","create_time","update_time","version");
}
