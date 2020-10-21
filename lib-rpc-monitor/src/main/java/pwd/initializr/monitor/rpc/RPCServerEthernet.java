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
public class RPCServerEthernet {
    @ApiModelProperty(value = "网络设备名")
    private String name = null;
    @ApiModelProperty(value = "网卡MAC地址")
    private String hwaddr = null;
    @ApiModelProperty(value = "网卡类型")
    private String type = null;
    @ApiModelProperty(value = "网卡描述信息")
    private String description = null;
    @ApiModelProperty(value = "IP地址")
    private String address = null;
    private String destination = null;
    @ApiModelProperty(value = "网关广播地址")
    private String broadcast = null;
    @ApiModelProperty(value = "子网掩码")
    private String netmask = null;
    private Long flags = 0L;
    private Long mtu = 0L;
    private Long metric = 0L;
}
