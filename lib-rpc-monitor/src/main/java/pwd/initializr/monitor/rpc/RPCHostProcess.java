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
 * date 2020-10-22 14:38
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
public class RPCHostProcess {

    @ApiModelProperty(value = "主机名")
    private String id;
    @ApiModelProperty(value = "进程ID")
    private String pid;
    @ApiModelProperty(value = "进程名")
    private String name;
    @ApiModelProperty(value = "进程用户信息")
    private RPCHostProcessCredName credName;
    @ApiModelProperty(value = "进程时间")
    private RPCHostProcessTime time;
    @ApiModelProperty(value = "进程内存")
    private RPCHostProcessMemory memory;
    @ApiModelProperty(value = "进程状态")
    private RPCHostProcessState state;
    @ApiModelProperty(value = "进程CPU")
    private RPCHostProcessCpu cpu;
}
