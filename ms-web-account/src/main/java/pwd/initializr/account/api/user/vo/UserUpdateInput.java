package pwd.initializr.account.api.user.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-28 23:14
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserUpdateInput implements Serializable {

  @ApiModelProperty(name = "pin", value = "身份证号", required = true, example = "111111111111111111")
//  @NotBlank(message = "账号不能为空")
//  @Size(min = 18, max = 18, message = "身份证号长度必须是18")
  private String pin;

  @ApiModelProperty(name = "name", value = "姓名", required = true, example = "pwd")
  @NotBlank(message = "姓名不能为空")
  @Size(min = 2, max = 20, message = "姓名长度必须在[2,20]之间")
  private String name;

  @ApiModelProperty(name = "gender", value = "姓别", required = true, example = "0")
  @NotBlank(message = "姓别不能为空")
  @Size(min = 1, max = 2, message = "姓别长度必须在[1,2]之间")
  private String gender;

  @ApiModelProperty(name = "empNo", value = "工号", required = true, example = "0")
//  @NotBlank(message = "工号不能为空")
//  @Size(min = 6, max = 8, message = "工号长度必须在[6,8]之间")
  private String empNo;

  @ApiModelProperty(name = "summary", value = "简介", required = true, example = "0")
//  @NotBlank(message = "简介不能为空")
//  @Size(min = 0, max = 200, message = "简介长度必须在[0,200]之间")
  private String summary;

}
