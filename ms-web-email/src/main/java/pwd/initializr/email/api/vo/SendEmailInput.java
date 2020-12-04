package pwd.initializr.email.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
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
 * <h1>请求参数：发送邮件参数</h1>
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
@ApiModel(value = "sendEmailInput", description = "发送邮件参数")
public class SendEmailInput implements Serializable {

  @ApiModelProperty(name = "meta", value = "邮件元数据", required = true)
  @NotNull(message = "元数据不能为空")
  private EmailMeta meta;

  @ApiModelProperty(name = "header", value = "邮件收发地址信息", required = true)
  @NotNull(message = "邮件收发地址信息不能为空")
  private EmailHeader header;

  @ApiModelProperty(name = "body", value = "邮件消息体", required = true)
  @NotNull(message = "邮件主题体不能为空")
  private EmailBody body;

}
