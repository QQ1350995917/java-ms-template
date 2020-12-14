package pwd.initializr.email.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.storage.api.robot.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:43
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@ApiModel("发送邮件的内容参数")
public class RPCEmailContentVO implements Serializable {

    private static final long serialVersionUID = -1659590069838613585L;

    @ApiModelProperty(name = "subject", value = "subject", required = true, dataType = "java.lang.String")
    @NotBlank(message = "标题不能为空")
    private String subject;

    @ApiModelProperty(name = "content", value = "content", required = true, dataType = "java.lang.String")
    @NotBlank(message = "主题内容不能为空")
    private String content;

}
