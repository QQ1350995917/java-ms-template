package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>请求参数：用户请求参数</h1>
 *
 * date 2020-07-27 15:56
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
@ApiModel(value = "adminUserInput", description = "管理员用户接口请求参数")
public class AdminUserCreateInput implements Serializable {

  @ApiModelProperty(name = "pin", value = "身份证编号", required = false, example = "010010015501010000")
  // TODO 要么为空，要么格式合格
//  @Length(min = 18, max = 18, message = "身份证编号长度应为18位")
  private String pin;
  @ApiModelProperty(name = "name", value = "姓名", required = true, example = "曹操")
  @NotNull(message = "姓名不能为空")
  @Length(min = 2, max = 20, message = "姓名长度应是[2,20]位")
  private String name;
  @ApiModelProperty(name = "gender", value = "性别", required = true, example = "1")
  @NotNull(message = "性别不能为空")
  private String gender;
  @ApiModelProperty(name = "empNo", value = "工号", required = false, example = "010-000")
  // TODO 要么为空，要么格式合格
//  @Length(min = 6, max = 8, message = "工号长度应是[6,8]位")
  private String empNo;
  @ApiModelProperty(name = "summary", value = "简介", required = false, example = "只教我负天下人,休教天下人负我")
  private String summary;
}
