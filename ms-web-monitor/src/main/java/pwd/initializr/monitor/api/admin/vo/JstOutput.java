package pwd.initializr.monitor.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * date 2020-10-19 18:50
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel(value = "JstOutput", description = "JstOutput")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class JstOutput {

    @ApiModelProperty(value = "Java的运行环境版本")
    private String version;
    @ApiModelProperty(value = "Java的运行环境供应商")
    private String vendor;
    @ApiModelProperty(value = "Java供应商的URL")
    private String vendorUrl;
    @ApiModelProperty(value = "Java的安装路径")
    private String home;
    @ApiModelProperty(value = "Java运行时环境规范供应商")
    private String specificationVender;
    @ApiModelProperty(value = "Java运行时环境规范版本")
    private String specificationVersion;
    @ApiModelProperty(value = "Java运行时环境规范名称")
    private String specificationName;
    @ApiModelProperty(value = "Java的虚拟机实现版本")
    private String vmVersion;
    @ApiModelProperty(value = "Java的虚拟机实现供应商")
    private String vmVendor;
    @ApiModelProperty(value = "Java的虚拟机实现名称")
    private String vmName;
    @ApiModelProperty(value = "Java的虚拟机规范版本")
    private String vmSpecificationVersion;
    @ApiModelProperty(value = "Java的虚拟机规范供应商")
    private String vmSpecificationVendor;
    @ApiModelProperty(value = "Java的虚拟机规范名称")
    private String vmSpecificationName;
    @ApiModelProperty(value = "Java的类格式版本号")
    private String classVersion;
    @ApiModelProperty(value = "Java的类路径")
    private String classPath;
    @ApiModelProperty(value = "加载库时搜索的路径列表")
    private String libraryPath;
    @ApiModelProperty(value = "默认的临时文件路径")
    private String ioTmpdir;
    @ApiModelProperty(value = "一个或多个扩展目录的路径")
    private String extDirs;

}
