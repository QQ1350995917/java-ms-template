package pwd.initializr.account.api.robot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.robot.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 15:53
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
@ApiModel(value = "listUserInput", description = "用户信息查询请求参数")
public class ListUserInput {

  @ApiModelProperty(name = "userIds", value = "用户ID", required = true, example = "1")
  @NotNull(message = "0")
  private Long[] userIds;

}
