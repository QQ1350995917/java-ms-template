package pwd.initializr.email.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.email.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-09 11:25
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@ApiModel("发送邮件响应参数")
public class RPCEmailOutput implements Serializable {

    @ApiModelProperty(name = "id", value = "邮件ID", required = true, dataType = "java.lang.String")
    @NotBlank(message = "id不能为空")
    private String id;
    @ApiModelProperty(name = "createTime", value = "发送时间", required = true, dataType = "java.util.Date")
    @NotBlank(message = "创建时间不能为空")
    private Date createTime;
}
