package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
 * date 2021-07-30 22:53
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "adminContactCreateInput", description = "创建管理员联系方式请求参数")
public class AdminContactCreateInput {

  @ApiModelProperty(name = "type", value = "联系方式类型", required = true, example = "1")
  @NotNull(message = "联系方式类型")
  @Min(value = 1, message = "1：手机号码")
  @Max(value = 2, message = "2：邮箱")
  private Integer type;

  @ApiModelProperty(name = "value", value = "联系方式", required = true, example = "www.dingpengwei@foxmail.com")
  @NotBlank(message = "联系方式")
//  @Pattern(regexp = "^((13[0-9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\\\d{8}$|^\\w{1,64}@[a-z0-9\\-]{1,256}(\\.[a-z]{2,6}){1,2}$", message = "联系方式格式不正确")
  private String value;
}
