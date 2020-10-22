package pwd.initializr.monitor.rpc;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-21 15:03
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
public class RPCHostWho {

    @ApiModelProperty(value = "主机名")
    private String id;
    @ApiModelProperty(value = "系统进程表中的用户名")
    private String user;
    @ApiModelProperty(value = "系统进程表中的控制台名称")
    private String device;
    @ApiModelProperty(value = "系统进程表中的HOST名称")
    private String host;
    @ApiModelProperty(value = "系统进程时长")
    private Long time;
}
