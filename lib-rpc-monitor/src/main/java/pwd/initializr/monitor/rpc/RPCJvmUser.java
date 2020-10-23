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
 * date 2020-10-21 14:44
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
public class RPCJvmUser implements Serializable {

    @ApiModelProperty(value = "用户名称")
    private String name;
    @ApiModelProperty(value = "用户目录")
    private String home;
    @ApiModelProperty(value = "用户工作目录")
    private String dir;

}
