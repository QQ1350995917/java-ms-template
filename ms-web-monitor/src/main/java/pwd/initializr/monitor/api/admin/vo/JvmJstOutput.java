package pwd.initializr.monitor.api.admin.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.monitor.rpc.RPCJvmJst;

/**
 * pwd.initializr.monitor.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 18:50
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel(value = "JvmJstOutput", description = "JvmJstOutput")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class JvmJstOutput extends RPCJvmJst {

}
