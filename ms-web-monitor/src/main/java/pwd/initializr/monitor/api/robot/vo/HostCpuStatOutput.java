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
@ApiModel(value = "HostCpuStatInput", description = "HostCpuStat响应参数")
public class HostCpuStatOutput implements Serializable {

  /**
   * 
   * 逻辑组名
   */
  @ApiModelProperty(name = "groupName", value = "逻辑组名", required = true, example = "")
   @NotBlank(message = "groupName不能为空")
  private String groupName;
  /**
   * 
   * 主机名
   */
  @ApiModelProperty(name = "nodeName", value = "主机名", required = true, example = "")
   @NotBlank(message = "nodeName不能为空")
  private String nodeName;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "user", value = "", required = true, example = "0")
   @NotBlank(message = "user不能为空")
  private Integer user;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "nice", value = "", required = true, example = "0")
   @NotBlank(message = "nice不能为空")
  private Integer nice;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "system", value = "", required = true, example = "0")
   @NotBlank(message = "system不能为空")
  private Integer system;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "idle", value = "", required = true, example = "0")
   @NotBlank(message = "idle不能为空")
  private Integer idle;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "iowait", value = "", required = true, example = "0")
   @NotBlank(message = "iowait不能为空")
  private Integer iowait;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "irq", value = "", required = true, example = "0")
   @NotBlank(message = "irq不能为空")
  private Integer irq;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "softirq", value = "", required = true, example = "0")
   @NotBlank(message = "softirq不能为空")
  private Integer softirq;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "steal", value = "", required = true, example = "0")
   @NotBlank(message = "steal不能为空")
  private Integer steal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "guest", value = "", required = true, example = "0")
   @NotBlank(message = "guest不能为空")
  private Integer guest;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "guestNice", value = "", required = true, example = "0")
   @NotBlank(message = "guestNice不能为空")
  private Integer guestNice;
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
