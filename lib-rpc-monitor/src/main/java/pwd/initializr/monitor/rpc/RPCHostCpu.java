package pwd.initializr.monitor.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
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
public class RPCHostCpu {
    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "用户使用量")
    private Long user;
    @ApiModelProperty(value = "系统使用量")
    private Long sys;
    @ApiModelProperty(value = "当前错误量")
    private Long nice;
    @ApiModelProperty(value = "当前空闲量")
    private Long idle;
    @ApiModelProperty(value = "当前等待量")
    private Long wait;
    @ApiModelProperty(value = "")
    private Long irq;
    @ApiModelProperty(value = "")
    private Long softIrq;
    @ApiModelProperty(value = "")
    private Long stolen;
    @ApiModelProperty(value = "总使用量")
    private Long total;
    @ApiModelProperty(value = "CPU核心")
    private List<RPCHostCpuCore> core;
}
