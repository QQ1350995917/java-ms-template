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
@ApiModel(value = "HostEthernetStatInput", description = "HostEthernetStat响应参数")
public class HostEthernetStatOutput implements Serializable {

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
  @ApiModelProperty(name = "interFace", value = "", required = true, example = "")
   @NotBlank(message = "interFace不能为空")
  private String interFace;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "receiveBytes", value = "", required = true, example = "0")
   @NotBlank(message = "receiveBytes不能为空")
  private Integer receiveBytes;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "receivePackets", value = "", required = true, example = "0")
   @NotBlank(message = "receivePackets不能为空")
  private Integer receivePackets;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "receiveErrs", value = "", required = true, example = "0")
   @NotBlank(message = "receiveErrs不能为空")
  private Integer receiveErrs;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "receiveDrop", value = "", required = true, example = "0")
   @NotBlank(message = "receiveDrop不能为空")
  private Integer receiveDrop;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "receiveFifo", value = "", required = true, example = "0")
   @NotBlank(message = "receiveFifo不能为空")
  private Integer receiveFifo;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "receiveFrame", value = "", required = true, example = "0")
   @NotBlank(message = "receiveFrame不能为空")
  private Integer receiveFrame;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "receiveCompressed", value = "", required = true, example = "0")
   @NotBlank(message = "receiveCompressed不能为空")
  private Integer receiveCompressed;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "receiveMulticast", value = "", required = true, example = "0")
   @NotBlank(message = "receiveMulticast不能为空")
  private Integer receiveMulticast;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "transmitBytes", value = "", required = true, example = "0")
   @NotBlank(message = "transmitBytes不能为空")
  private Integer transmitBytes;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "transmitPackets", value = "", required = true, example = "0")
   @NotBlank(message = "transmitPackets不能为空")
  private Integer transmitPackets;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "transmitErrs", value = "", required = true, example = "0")
   @NotBlank(message = "transmitErrs不能为空")
  private Integer transmitErrs;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "transmitDrop", value = "", required = true, example = "0")
   @NotBlank(message = "transmitDrop不能为空")
  private Integer transmitDrop;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "transmitFifo", value = "", required = true, example = "0")
   @NotBlank(message = "transmitFifo不能为空")
  private Integer transmitFifo;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "transmitColls", value = "", required = true, example = "0")
   @NotBlank(message = "transmitColls不能为空")
  private Integer transmitColls;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "transmitCarrier", value = "", required = true, example = "0")
   @NotBlank(message = "transmitCarrier不能为空")
  private Integer transmitCarrier;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "transmitCompressed", value = "", required = true, example = "0")
   @NotBlank(message = "transmitCompressed不能为空")
  private Integer transmitCompressed;
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
