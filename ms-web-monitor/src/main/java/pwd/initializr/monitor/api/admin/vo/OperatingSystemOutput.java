package pwd.initializr.monitor.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.monitor.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 17:33
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel(value = "OperatingSystemOutput", description = "操作系统信息参数")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OperatingSystemOutput implements Serializable {

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
