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
@ApiModel(value = "HostDiskStatInput", description = "HostDiskStat响应参数")
public class HostDiskStatOutput implements Serializable {

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
  @ApiModelProperty(name = "majorDeviceNumber", value = "", required = true, example = "0")
   @NotBlank(message = "majorDeviceNumber不能为空")
  private Integer majorDeviceNumber;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "minorDeviceNumber", value = "", required = true, example = "0")
   @NotBlank(message = "minorDeviceNumber不能为空")
  private Integer minorDeviceNumber;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "deviceName", value = "", required = true, example = "")
   @NotBlank(message = "deviceName不能为空")
  private String deviceName;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "read", value = "", required = true, example = "0")
   @NotBlank(message = "read不能为空")
  private Integer read;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "readMerge", value = "", required = true, example = "0")
   @NotBlank(message = "readMerge不能为空")
  private Integer readMerge;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "readSector", value = "", required = true, example = "0")
   @NotBlank(message = "readSector不能为空")
  private Integer readSector;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "readSpentMilliseconds", value = "", required = true, example = "0")
   @NotBlank(message = "readSpentMilliseconds不能为空")
  private Integer readSpentMilliseconds;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "write", value = "", required = true, example = "0")
   @NotBlank(message = "write不能为空")
  private Integer write;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "writeMerge", value = "", required = true, example = "0")
   @NotBlank(message = "writeMerge不能为空")
  private Integer writeMerge;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "writeSector", value = "", required = true, example = "0")
   @NotBlank(message = "writeSector不能为空")
  private Integer writeSector;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "writeSpentMilliseconds", value = "", required = true, example = "0")
   @NotBlank(message = "writeSpentMilliseconds不能为空")
  private Integer writeSpentMilliseconds;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "ioRequest", value = "", required = true, example = "0")
   @NotBlank(message = "ioRequest不能为空")
  private Integer ioRequest;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "ioSpentMilliseconds", value = "", required = true, example = "0")
   @NotBlank(message = "ioSpentMilliseconds不能为空")
  private Integer ioSpentMilliseconds;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "ioSpentAllMilliseconds", value = "", required = true, example = "0")
   @NotBlank(message = "ioSpentAllMilliseconds不能为空")
  private Integer ioSpentAllMilliseconds;
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
