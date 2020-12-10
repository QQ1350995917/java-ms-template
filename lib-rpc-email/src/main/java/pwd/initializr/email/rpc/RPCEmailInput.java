package pwd.initializr.email.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
public class RPCEmailInput implements Serializable {

  private static final long serialVersionUID = -1659590069838613585L;

  @ApiModelProperty(name = "app", value = "app", required = true, dataType = "java.lang.String")
  private String app;
  @ApiModelProperty(name = "header", value = "header", required = true, dataType = "pwd.initializr.email.rpc.RPCEmailHeaderVO")
  private RPCEmailHeaderVO  header;
  @ApiModelProperty(name = "content", value = "content", required = true, dataType = "pwd.initializr.email.rpc.RPCEmailContentVO")
  private RPCEmailContentVO content;
  @ApiModelProperty(name = "attachments", value = "附件", required = false, dataType = "java.utls.List<pwd.initializr.email.rpc.RPCEmailAttachmentVO>")
  private List<RPCEmailAttachmentVO> attachments;
  @ApiModelProperty(name = "extend", value = "extend", required = false, dataType = "java.util.HashMap<java.lang.String,java.lang.String>")
  private HashMap<String, String> extend;


}
