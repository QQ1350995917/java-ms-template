package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
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
 * date 2019-11-04 22:48
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
@ApiModel(value = "createAdminInput", description = "管理员创建请求参数")
public class CreateAdminInput implements Serializable {

  @ApiModelProperty(name = "loginName", value = "登录名称", required = true, example = "DingPengwei")
  @NotNull(message = "0")
  private String loginName;
  @ApiModelProperty(name = "loginPwd", value = "登录密码", required = true, example = "DingPengwei")
  @NotNull(message = "0")
  private String loginPassword;
  @ApiModelProperty(name = "name", value = "名称", required = true, example = "DingPengwei")
  @NotNull(message = "0")
  private String name;
  @ApiModelProperty(name = "gender", value = "性别", required = true, example = "0")
  @NotNull(message = "0")
  private Integer gender;
  @ApiModelProperty(name = "summary", value = "简介", required = true, example = "DingPengwei")
  @NotNull(message = "0")
  private String summary;
  @ApiModelProperty(name = "level", value = "管理员等级", required = true, example = "0")
  @NotNull(message = "0")
  private Integer level;

}
