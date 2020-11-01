package pwd.initializr.monitor.api.robot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * project-generator-test@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
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
@ApiModel(value = "HostInput", description = "Host响应参数")
public class HostOutput implements Serializable {

  /**
   * 主键
   * 逻辑组名
   */
  @ApiModelProperty(name = "groupName", value = "逻辑组名", required = true, example = "")
   @NotBlank(message = "groupName不能为空")
  private String groupName;
  /**
   * 主键
   * 主机名
   */
  @ApiModelProperty(name = "nodeName", value = "主机名", required = true, example = "")
   @NotBlank(message = "nodeName不能为空")
  private String nodeName;

  private String distributeId;
  private String distributeName;
  private String distributeIdLike;
  private String distributeVersion;
  private String distributeCodeName;
  private String distributeDescription;

  /**
   * 
   * 操作系统名称
   */
  @ApiModelProperty(name = "operatingSystem", value = "操作系统名称", required = true, example = "")
   @NotBlank(message = "operatingSystem不能为空")
  private String operatingSystem;
  /**
   * 
   * 硬件平台（x86_64）
   */
  @ApiModelProperty(name = "hardwarePlatform", value = "硬件平台（x86_64）", required = true, example = "")
   @NotBlank(message = "hardwarePlatform不能为空")
  private String hardwarePlatform;
  /**
   * 
   * 启动时间
   */
  @ApiModelProperty(name = "systemUpSince", value = "启动时间", required = true, example = "")
   @NotBlank(message = "systemUpSince不能为空")
  private String systemUpSince;
  /**
   * 
   * 内核名称
   */
  @ApiModelProperty(name = "kernelName", value = "内核名称", required = true, example = "")
   @NotBlank(message = "kernelName不能为空")
  private String kernelName;
  /**
   * 
   * 内核版本
   */
  @ApiModelProperty(name = "kernelVersion", value = "内核版本", required = true, example = "")
   @NotBlank(message = "kernelVersion不能为空")
  private String kernelVersion;
  /**
   * 
   * 内核发布
   */
  @ApiModelProperty(name = "kernelRelease", value = "内核发布", required = true, example = "")
   @NotBlank(message = "kernelRelease不能为空")
  private String kernelRelease;
  /**
   * 
   * 型号
   */
  @ApiModelProperty(name = "machine", value = "型号", required = true, example = "")
   @NotBlank(message = "machine不能为空")
  private String machine;
  /**
   * 
   * 类型
   */
  @ApiModelProperty(name = "processor", value = "类型", required = true, example = "")
   @NotBlank(message = "processor不能为空")
  private String processor;
  /**
   * 
   * 可用状态：0:不可用；1:可用
   */
  @ApiModelProperty(name = "able", value = "可用状态：0:不可用；1:可用", required = true, example = "0")
   @NotBlank(message = "able不能为空")
  private Integer able;
  /**
   * 
   * 删除状态：0:未删除；1:已删除
   */
  @ApiModelProperty(name = "del", value = "删除状态：0:未删除；1:已删除", required = true, example = "0")
   @NotBlank(message = "del不能为空")
  private Integer del;
  /**
   * 
   * 数据创建时间
   */
  @ApiModelProperty(name = "createTime", value = "数据创建时间", required = true, example = "")
   @NotBlank(message = "createTime不能为空")
  private Date createTime;
  /**
   * 
   * 最近更新时间
   */
  @ApiModelProperty(name = "updateTime", value = "最近更新时间", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
}
