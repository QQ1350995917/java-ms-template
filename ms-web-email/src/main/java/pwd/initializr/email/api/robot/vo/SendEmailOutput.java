package pwd.initializr.email.api.robot.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.email.rpc.RPCEmailOutput;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>响应参数：发送邮件参数</h1>
 *
 * date 2020-07-27 16:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "sendEmailOutput", description = "发送邮件响应参数")
public class SendEmailOutput extends RPCEmailOutput {

}
