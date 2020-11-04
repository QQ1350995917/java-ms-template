package pwd.initializr.monitor.api.admin.vo;

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
@ApiModel(value = "HostLoggedStatInput", description = "HostLoggedStat响应参数")
public class HostLoggedStatOutput implements Serializable {

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
   * 登录用户名称
   */
  @ApiModelProperty(name = "user", value = "登录用户名称", required = true, example = "")
   @NotBlank(message = "user不能为空")
  private String user;
  /**
   * 
   * 登录用户使用的终端名
   */
  @ApiModelProperty(name = "tty", value = "登录用户使用的终端名", required = true, example = "")
   @NotBlank(message = "tty不能为空")
  private String tty;
  /**
   * 
   * 登录用户来源的主机名或IP地址
   */
  @ApiModelProperty(name = "from", value = "登录用户来源的主机名或IP地址", required = true, example = "")
   @NotBlank(message = "from不能为空")
  private String from;
  /**
   * 
   * 用户登录时长
   */
  @ApiModelProperty(name = "login", value = "用户登录时长", required = true, example = "")
   @NotBlank(message = "login不能为空")
  private String login;
  /**
   * 
   * 自用户上一次与终端进行交互以来的空闲时间
   */
  @ApiModelProperty(name = "idle", value = "自用户上一次与终端进行交互以来的空闲时间", required = true, example = "")
   @NotBlank(message = "idle不能为空")
  private String idle;
  /**
   * 
   * 用户当前进程所用的时间
   */
  @ApiModelProperty(name = "jcpu", value = "用户当前进程所用的时间", required = true, example = "")
   @NotBlank(message = "jcpu不能为空")
  private String jcpu;
  /**
   * 
   * 用户当前的进程及选项/参数
   */
  @ApiModelProperty(name = "pcpu", value = "用户当前的进程及选项/参数", required = true, example = "")
   @NotBlank(message = "pcpu不能为空")
  private String pcpu;
  /**
   * 
   * 用户当前的进程及选项
   */
  @ApiModelProperty(name = "what", value = "用户当前的进程及选项", required = true, example = "")
   @NotBlank(message = "what不能为空")
  private String what;
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
