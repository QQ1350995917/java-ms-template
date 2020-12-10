package pwd.initializr.email.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.search.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-15 17:34
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
public class RPCEmailHeaderVO implements Serializable {

    private static final long serialVersionUID = -1659590069838613585L;

    @ApiModelProperty(name = "to", value = "to", required = true, dataType = "java.lang.String")
    private String to;
    @ApiModelProperty(name = "cc", value = "cc", required = true, dataType = "java.lang.String")
    private String cc;
    @ApiModelProperty(name = "bcc", value = "bcc", required = true, dataType = "java.lang.String")
    private String bcc;

}
