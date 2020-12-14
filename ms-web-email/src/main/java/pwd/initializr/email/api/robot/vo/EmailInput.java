package pwd.initializr.email.api.robot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * project-generator-test@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2020-12-14 16:13
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "EmailInput", description = "Email请求参数")
public class EmailInput implements Serializable {

    /**
     * 发送邮件的服务名
     */
    @ApiModelProperty(name = "app", value = "发送邮件的服务名", required = true, example = "")
    @NotBlank(message = "app不能为空")
    private String app;

    /**
     * 邮件发送状态，0：未发送；1：发送中；2：发送成功；3：发送失败
     */
    @ApiModelProperty(name = "sent", value = "邮件发送状态，0：未发送；1：发送中；2：发送成功；3：发送失败", required = true, example = "")
    @Digits(integer = 10, fraction = 0, message = "sent须为整数")
    private Integer sent;

}
