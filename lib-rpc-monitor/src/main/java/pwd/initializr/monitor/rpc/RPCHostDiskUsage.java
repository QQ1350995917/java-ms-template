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
public class RPCHostDiskUsage implements Serializable {

    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "设备名称")
    private String devName;
    @ApiModelProperty(value = "文件系统总大小")
    private Long total;
    @ApiModelProperty(value = "剩余大小")
    private Long free;
    @ApiModelProperty(value = "已经使用量")
    private Long used;
    @ApiModelProperty(value = "可用大小")
    private Long avail;
    @ApiModelProperty(value = "")
    private Long files;
    @ApiModelProperty(value = "")
    private Long freeFiles;
    @ApiModelProperty(value = "")
    private Long diskReads;
    @ApiModelProperty(value = "")
    private Long diskWrites;
    @ApiModelProperty(value = "")
    private Long diskReadBytes;
    @ApiModelProperty(value = "")
    private Long diskWriteBytes;
    @ApiModelProperty(value = "")
    private Double diskQueue;
    @ApiModelProperty(value = "")
    private Double diskServiceTime;
    @ApiModelProperty(value = "资源的利用率")
    private Double usePercent;
}
