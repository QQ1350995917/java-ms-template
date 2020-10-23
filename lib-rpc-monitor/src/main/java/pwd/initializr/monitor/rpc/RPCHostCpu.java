package pwd.initializr.monitor.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
public class RPCHostCpu implements Serializable {
    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "执行用户进程的耗时，包括ni耗时")
    private Long user;
    @ApiModelProperty(value = "内核运行耗时，包括IRQ和softirq耗时")
    private Long sys;
    @ApiModelProperty(value = "调整进程优先级耗时")
    private Long nice;
    @ApiModelProperty(value = "空闲期")
    private Long idle;
    @ApiModelProperty(value = "等待I/O操作完成耗时")
    private Long wait;
    @ApiModelProperty(value = "处理硬中断耗时")
    private Long irq;
    @ApiModelProperty(value = "处理软中断耗时")
    private Long softIrq;
    @ApiModelProperty(value = "等待虚拟CPU的耗时，此时hypervisor在为另一个虚拟处理器服务")
    private Long stolen;
    @ApiModelProperty(value = "总使用量")
    private Long total;
    @ApiModelProperty(value = "CPU核心")
    private List<RPCHostCpuCore> core;
}
