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
public class RPCHostEthernet implements Serializable {

    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "网络设备名")
    private String name;
    @ApiModelProperty(value = "网卡MAC地址")
    private String hwaddr;
    @ApiModelProperty(value = "网卡类型")
    private String type;
    @ApiModelProperty(value = "网卡描述信息")
    private String description;
    @ApiModelProperty(value = "IP地址")
    private String address;
    @ApiModelProperty(value = "目标地址")
    private String destination;
    @ApiModelProperty(value = "网关广播地址")
    private String broadcast;
    @ApiModelProperty(value = "子网掩码")
    private String netmask;
    @ApiModelProperty(value = "网卡标记")
    private Long flags;
    @ApiModelProperty(value = "网卡MTU")
    private Long mtu;
    @ApiModelProperty(value = "跃点数量")
    private Long metric;
}
