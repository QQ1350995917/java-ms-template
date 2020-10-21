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
 * date 2020-10-21 15:24
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
public class RPCServerCpuCoreUsage {
    @ApiModelProperty(value = "用户使用率")
    private String user;
    @ApiModelProperty(value = "系统使用率")
    private String sys;
    @ApiModelProperty(value = "当前错误率")
    private String nice;
    @ApiModelProperty(value = "当前空闲率")
    private String idle;
    @ApiModelProperty(value = "当前等待率")
    private String wait;
    @ApiModelProperty(value = "")
    private String irq;
    @ApiModelProperty(value = "")
    private String softIrq;
    @ApiModelProperty(value = "")
    private String stolen;
    @ApiModelProperty(value = "总使用率")
    private String combined;
}
