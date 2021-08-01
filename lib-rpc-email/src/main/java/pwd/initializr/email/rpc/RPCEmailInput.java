package pwd.initializr.email.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
 * date 2020-05-19 14:34
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
@ApiModel("发送邮件请求参数")
public class  RPCEmailInput implements Serializable {

  private static final long serialVersionUID = -1659590069838613585L;

  @ApiModelProperty(name = "app", value = "服务名称", required = true, dataType = "java.lang.String")
  @NotBlank(message = "服务名不能为空")
  private String app;
  @ApiModelProperty(name = "box", value = "收件箱", required = true, dataType = "pwd.initializr.email.rpc.RPCEmailBoxVO")
  @NotNull(message = "收件箱不能为空")
  private RPCEmailBoxVO box = new RPCEmailBoxVO();
  @ApiModelProperty(name = "content", value = "邮件内容", required = true, dataType = "pwd.initializr.email.rpc.RPCEmailContentVO")
  @NotNull(message = "content不能为空")
  private RPCEmailContentVO content;
  @ApiModelProperty(name = "attachments", value = "附件", required = false, dataType = "java.utls.List<pwd.initializr.email.rpc.RPCEmailAttachmentVO>")
  private Set<RPCEmailAttachmentVO> attachments;
  @ApiModelProperty(name = "extend", value = "扩展属性", required = false, dataType = "java.util.HashMap<java.lang.String,java.lang.String>")
  private HashMap<String, String> extend;


}
