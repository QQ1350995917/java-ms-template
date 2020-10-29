package pwd.initializr.monitor.api.robot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * project-generator-test@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2020-10-29 11:44
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "HostInput", description = "Host请求参数")
public class HostInput implements Serializable {

    /**
     * 逻辑分组名称
     */
    @ApiModelProperty(name = "groupName", value = "逻辑分组名称", required = false, example = "")
    private String groupName;
    /**
     * 主机名
     */
    @ApiModelProperty(name = "nodeName", value = "主机名称", required = true, example = "")
    @NotBlank(message = "nodeName不能为空")
    private String nodeName;
    /**
     * 操作系统名称
     */
    @ApiModelProperty(name = "operatingSystem", value = "操作系统名称", required = false, example = "")
    private String operatingSystem;

    /**
     * 硬件平台（x86_64）
     */
    @ApiModelProperty(name = "hardwarePlatform", value = "硬件平台（x86_64）", required = false, example = "")
    private String hardwarePlatform;

    /**
     * 启动时间
     */
    @ApiModelProperty(name = "systemUpSince", value = "启动时间", required = false, example = "")
    private String systemUpSince;

    /**
     * 内核名称
     */
    @ApiModelProperty(name = "kernelName", value = "内核名称", required = false, example = "")
    private String kernelName;

    /**
     * 内核版本
     */
    @ApiModelProperty(name = "kernelVersion", value = "内核版本", required = false, example = "")
    private String kernelVersion;

    /**
     * 内核发布
     */
    @ApiModelProperty(name = "kernelRelease", value = "内核发布", required = false, example = "")
    private String kernelRelease;

    /**
     * 型号
     */
    @ApiModelProperty(name = "machine", value = "型号", required = false, example = "")
    private String machine;

    /**
     * 类型
     */
    @ApiModelProperty(name = "processor", value = "类型", required = false, example = "")
    private String processor;

}
