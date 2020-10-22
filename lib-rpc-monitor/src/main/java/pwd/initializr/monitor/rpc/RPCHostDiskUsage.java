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
 * date 2020-10-21 16:34
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
public class RPCHostDiskUsage {

    @ApiModelProperty(value = "设备名称")
    private String devName;
    @ApiModelProperty(value = "文件系统总大小")
    private Long total = 0L;
    @ApiModelProperty(value = "剩余大小")
    private Long free = 0L;
    @ApiModelProperty(value = "已经使用量")
    private Long used = 0L;
    @ApiModelProperty(value = "可用大小")
    private Long avail = 0L;
    private Long files = 0L;
    private Long freeFiles = 0L;
    private Long diskReads = 0L;
    private Long diskWrites = 0L;
    private Long diskReadBytes = 0L;
    private Long diskWriteBytes = 0L;
    private Double diskQueue = 0.0D;
    private Double diskServiceTime = 0.0D;
    @ApiModelProperty(value = "资源的利用率")
    private Double usePercent = 0.0D;
}
