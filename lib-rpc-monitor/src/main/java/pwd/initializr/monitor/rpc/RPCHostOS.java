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
 * date 2020-10-20 21:31
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RPCHostOS {

    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "操作系统名称")
    private String name;
    @ApiModelProperty(value = "操作系统的版本号")
    private String version;
    @ApiModelProperty(value = "操作系统内核架构如： 386、486、586等x86")
    private String arch;
    @ApiModelProperty(value = "machine")
    private String machine;
    @ApiModelProperty(value = "description")
    private String description;
    @ApiModelProperty(value = "patchLevel")
    private String patchLevel;
    @ApiModelProperty(value = "操作系统的卖主")
    private String vendor;
    @ApiModelProperty(value = "操作系统卖主类型")
    private String vendorVersion;
    @ApiModelProperty(value = "操作系统名称")
    private String vendorName;
    @ApiModelProperty(value = "卖主名称")
    private String vendorCodeName;
    @ApiModelProperty(value = "操作系统位数")
    private String dataModel;
    @ApiModelProperty(value = "操作系统CPU大端/小端")
    private String cpuEndian;
}
