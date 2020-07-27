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
 * <h1>请求参数：管理员用户参数</h1>
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
public class AdminUserInput implements Serializable {
    @ApiModelProperty(name = "pin", value = "身份证编号", required = false, example = "pwd")
    @NotNull(message = "0")
    private String pin;
    @ApiModelProperty(name = "name", value = "名称", required = true, example = "pwd")
    @NotNull(message = "0")
    private String name;
    @ApiModelProperty(name = "gender", value = "性别", required = true, example = "0")
    @NotNull(message = "0")
    private Integer gender;
    @ApiModelProperty(name = "empNo", value = "工号", required = false, example = "pwd")
    @NotNull(message = "0")
    private String empNo;
    @ApiModelProperty(name = "summary", value = "简介", required = true, example = "pwd")
    @NotNull(message = "0")
    private String summary;
    @ApiModelProperty(name = "level", value = "管理员等级", required = false, example = "0")
    @NotNull(message = "0")
    private Integer level;
}
