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
 * date 2020-10-21 16:16
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
public class RPCHostMemorySwap implements Serializable {

    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "swap总量")
    private Long total;
    @ApiModelProperty(value = "swap使用量")
    private Long used;
    @ApiModelProperty(value = "swap空闲量")
    private Long free;
    @ApiModelProperty(value = "swapPageIn")
    private Long pageIn;
    @ApiModelProperty(value = "swapPageOut")
    private Long pageOut;
}
