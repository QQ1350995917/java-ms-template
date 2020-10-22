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

    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "网卡MAC")
    private String hwaddr;
    @ApiModelProperty(value = "接收到的总字节数")
    private Long rxBytes;
    @ApiModelProperty(value = "接收的总包裹数")
    private Long rxPackets;
    @ApiModelProperty(value = "接收到的错误包数")
    private Long rxErrors;
    @ApiModelProperty(value = "系统接收时丢弃的包数，未进入系统内核")
    private Long rxDropped;
    @ApiModelProperty(value = "网卡接收时丢弃的包数，未进入网卡缓存")
    private Long rxOverruns;
    @ApiModelProperty(value = "网卡接收最大巨帧")
    private Long rxFrame;
    @ApiModelProperty(value = "发送的总字节数")
    private Long txBytes;
    @ApiModelProperty(value = "发送的总包裹数")
    private Long txPackets;
    @ApiModelProperty(value = "发送的错误包数")
    private Long txErrors;
    @ApiModelProperty(value = "系统发送时丢弃的包数，未进入系统内核")
    private Long txDropped;
    @ApiModelProperty(value = "网卡发送时丢弃的包数，未进入网卡缓存")
    private Long txOverruns;
    @ApiModelProperty(value = "")
    private Long txCollisions;
    @ApiModelProperty(value = "")
    private Long txCarrier;
    @ApiModelProperty(value = "")
    private Long speed;
}
