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
 * date 2020-10-21 16:55
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
public class RPCHostEthernetStat {

    @ApiModelProperty(value = "网卡名称")
    private String name;
    @ApiModelProperty(value = "接收到的总字节数")
    private Long rxBytes = 0L;
    @ApiModelProperty(value = "接收的总包裹数")
    private Long rxPackets = 0L;
    @ApiModelProperty(value = "接收到的错误包数")
    private Long rxErrors = 0L;
    @ApiModelProperty(value = "接收时丢弃的包数")
    private Long rxDropped = 0L;
    private Long rxOverruns = 0L;
    private Long rxFrame = 0L;
    @ApiModelProperty(value = "发送的总字节数")
    private Long txBytes = 0L;
    @ApiModelProperty(value = "发送的总包裹数")
    private Long txPackets = 0L;
    @ApiModelProperty(value = "发送的错误包数")
    private Long txErrors = 0L;
    @ApiModelProperty(value = "发送时丢弃的包数")
    private Long txDropped = 0L;
    private Long txOverruns = 0L;
    private Long txCollisions = 0L;
    private Long txCarrier = 0L;
    private Long speed = 0L;
}
