package pwd.initializr.organization.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-16 20:25
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
@ApiModel(value = "orgCreateInput", description = "组织创建请求参数")
public class OrgCreateInput {
  /**
   *
   */
  @ApiModelProperty(name = "pid", value = "上级组织ID", required = false, example = "0")
  @Null(message = "0")
  private Long pid;
  @ApiModelProperty(name = "name", value = "组织名称", required = true, example = "一个组织名称")
  @NotNull(message = "0")
  private String name;
  @ApiModelProperty(name = "logo", value = "组织logo", required = false, example = "http://account.initializer.pwd/logo.png")
  @Null(message = "0")
  private String logo;
  @ApiModelProperty(name = "description", value = "组织描述", required = false, example = "一个组织描述")
  @Null(message = "0")
  private String description;
  @ApiModelProperty(name = "slogan", value = "组织口号", required = false, example = "一个组织口号")
  @Null(message = "0")
  private String slogan;
}
