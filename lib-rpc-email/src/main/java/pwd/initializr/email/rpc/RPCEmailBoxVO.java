package pwd.initializr.email.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.search.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-15 17:34
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
@ApiModel("发送邮件的发送目标参数")
public class RPCEmailBoxVO implements Serializable {

    private static final long serialVersionUID = -1659590069838613585L;

    @ApiModelProperty(name = "to", value = "to", required = true, dataType = "java.util.Set<java.lang.String>")
    @NotEmpty(message = "邮件发送对象至少有一个")
    private Set<String> to;
    @ApiModelProperty(name = "cc", value = "cc", required = true, dataType = "java.util.Set<java.lang.String>")
    private Set<String> cc;
    @ApiModelProperty(name = "bcc", value = "bcc", required = true, dataType = "java.util.Set<java.lang.String>")
    private Set<String> bcc;

}
