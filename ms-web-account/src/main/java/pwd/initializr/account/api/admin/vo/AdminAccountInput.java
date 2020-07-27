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
 * <h1>请求参数：管理员账户参数</h1>
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
@ApiModel(value = "adminAccountInput", description = "管理员账户参数")
public class AdminAccountInput implements Serializable {
    @ApiModelProperty(name = "loginName", value = "登录名称", required = true, example = "pwd")
    @NotNull(message = "0")
    private String loginName;
    @ApiModelProperty(name = "loginPwd", value = "登录密码", required = true, example = "pwd")
    @NotNull(message = "0")
    private String loginPwd;
}
