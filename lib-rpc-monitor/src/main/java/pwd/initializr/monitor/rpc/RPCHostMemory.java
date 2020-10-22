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
 * date 2020-10-21 16:05
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
public class RPCHostMemory {
    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "总内存量")
    private Long total = 0L;
    @ApiModelProperty(value = "")
    private Long ram = 0L;
    @ApiModelProperty(value = "当前内存使用量")
    private Long used = 0L;
    @ApiModelProperty(value = "当前内存空闲量")
    private Long free = 0L;
    @ApiModelProperty(value = "实际内存使用量")
    private Long actualUsed = 0L;
    @ApiModelProperty(value = "实际内存空闲量")
    private Long actualFree = 0L;
    @ApiModelProperty(value = "使用百分比")
    private Double usedPercent = 0.0D;
    @ApiModelProperty(value = "空闲百分比")
    private Double freePercent = 0.0D;
}
