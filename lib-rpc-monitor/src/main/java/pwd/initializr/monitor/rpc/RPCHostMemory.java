package pwd.initializr.monitor.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
public class RPCHostMemory implements Serializable {

    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "总内存量")
    private Long total;
    @ApiModelProperty(value = "可用内存总量")
    private Long ram;
    @ApiModelProperty(value = "当前内存使用量")
    private Long used;
    @ApiModelProperty(value = "当前内存空闲量")
    private Long free;
    @ApiModelProperty(value = "实际内存使用量")
    private Long actualUsed;
    @ApiModelProperty(value = "实际内存空闲量")
    private Long actualFree;
    @ApiModelProperty(value = "使用百分比")
    private Double usedPercent;
    @ApiModelProperty(value = "空闲百分比")
    private Double freePercent;
}
