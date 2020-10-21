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
 * <h1>JVM runtime</h1>
 *
 * date 2020-10-21 14:36
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
public class RPCJvmJrt {

    @ApiModelProperty(value = "JVM可以使用的总内存")
    private Long totalMemory;
    @ApiModelProperty(value = "JVM可以使用的剩余内存")
    private Long freeMemory;
    @ApiModelProperty(value = "JVM可以使用的处理器个数")
    private Integer availableProcessors;
}
