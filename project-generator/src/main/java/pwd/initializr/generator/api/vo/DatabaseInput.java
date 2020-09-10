package pwd.initializr.generator.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
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
 * date 2020-09-10 11:37
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
@ApiModel(value = "databaseInput", description = "数据库连接测试参数")
public class DatabaseInput {
    @ApiModelProperty(name = "name", value = "数据库名称", required = true, example = "initializr_account")
    @NotBlank(message = "数据库名称不能为空")
    private String name;
    @ApiModelProperty(name = "url", value = "数据库连接", required = true, example = "jdbc:mysql://localhost:3306/initializr_account?characterEncoding=utf-8&autoReconnect=true&serverTimezone=GMT")
    @NotBlank(message = "数据库连接不能为空")
    private String url;
    @ApiModelProperty(name = "driver", value = "数据库驱动", required = true, example = "com.mysql.jdbc.Driver")
    @NotBlank(message = "数据库驱动不能为空")
    private String driver;
    @ApiModelProperty(name = "user", value = "数据库用户", required = true, example = "root")
    @NotBlank(message = "数据库用户不能为空")
    private String user;
    @ApiModelProperty(name = "pwd", value = "数据库密码", required = true, example = "root")
    @NotBlank(message = "数据库密码不能为空")
    private String pwd;
}
