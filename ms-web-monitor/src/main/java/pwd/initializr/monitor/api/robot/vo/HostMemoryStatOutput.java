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
@ApiModel(value = "HostMemoryStatInput", description = "HostMemoryStat响应参数")
public class HostMemoryStatOutput implements Serializable {

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
  @ApiModelProperty(name = "memTotal", value = "", required = true, example = "0")
   @NotBlank(message = "memTotal不能为空")
  private Integer memTotal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "memFree", value = "", required = true, example = "0")
   @NotBlank(message = "memFree不能为空")
  private Integer memFree;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "memAvailable", value = "", required = true, example = "0")
   @NotBlank(message = "memAvailable不能为空")
  private Integer memAvailable;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "buffers", value = "", required = true, example = "0")
   @NotBlank(message = "buffers不能为空")
  private Integer buffers;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "cached", value = "", required = true, example = "0")
   @NotBlank(message = "cached不能为空")
  private Integer cached;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "swapCached", value = "", required = true, example = "0")
   @NotBlank(message = "swapCached不能为空")
  private Integer swapCached;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "active", value = "", required = true, example = "0")
   @NotBlank(message = "active不能为空")
  private Integer active;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "inactive", value = "", required = true, example = "0")
   @NotBlank(message = "inactive不能为空")
  private Integer inactive;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "activeAnon", value = "", required = true, example = "0")
   @NotBlank(message = "activeAnon不能为空")
  private Integer activeAnon;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "inactiveAnon", value = "", required = true, example = "0")
   @NotBlank(message = "inactiveAnon不能为空")
  private Integer inactiveAnon;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "activeFile", value = "", required = true, example = "0")
   @NotBlank(message = "activeFile不能为空")
  private Integer activeFile;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "inactiveFile", value = "", required = true, example = "0")
   @NotBlank(message = "inactiveFile不能为空")
  private Integer inactiveFile;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "unevictable", value = "", required = true, example = "0")
   @NotBlank(message = "unevictable不能为空")
  private Integer unevictable;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "mlocked", value = "", required = true, example = "0")
   @NotBlank(message = "mlocked不能为空")
  private Integer mlocked;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "swapTotal", value = "", required = true, example = "0")
   @NotBlank(message = "swapTotal不能为空")
  private Integer swapTotal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "swapFree", value = "", required = true, example = "0")
   @NotBlank(message = "swapFree不能为空")
  private Integer swapFree;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "dirty", value = "", required = true, example = "0")
   @NotBlank(message = "dirty不能为空")
  private Integer dirty;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "writeback", value = "", required = true, example = "0")
   @NotBlank(message = "writeback不能为空")
  private Integer writeback;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "anonPages", value = "", required = true, example = "0")
   @NotBlank(message = "anonPages不能为空")
  private Integer anonPages;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "mapped", value = "", required = true, example = "0")
   @NotBlank(message = "mapped不能为空")
  private Integer mapped;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "shmem", value = "", required = true, example = "0")
   @NotBlank(message = "shmem不能为空")
  private Integer shmem;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "slab", value = "", required = true, example = "0")
   @NotBlank(message = "slab不能为空")
  private Integer slab;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "sReclaimable", value = "", required = true, example = "0")
   @NotBlank(message = "sReclaimable不能为空")
  private Integer sReclaimable;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "sUnreclaim", value = "", required = true, example = "0")
   @NotBlank(message = "sUnreclaim不能为空")
  private Integer sUnreclaim;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "kernelStack", value = "", required = true, example = "0")
   @NotBlank(message = "kernelStack不能为空")
  private Integer kernelStack;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "pageTables", value = "", required = true, example = "0")
   @NotBlank(message = "pageTables不能为空")
  private Integer pageTables;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "nFsunstable", value = "", required = true, example = "0")
   @NotBlank(message = "nFsunstable不能为空")
  private Integer nFsunstable;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "bounce", value = "", required = true, example = "0")
   @NotBlank(message = "bounce不能为空")
  private Integer bounce;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "writebackTmp", value = "", required = true, example = "0")
   @NotBlank(message = "writebackTmp不能为空")
  private Integer writebackTmp;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "commitLimit", value = "", required = true, example = "0")
   @NotBlank(message = "commitLimit不能为空")
  private Integer commitLimit;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "committedAs", value = "", required = true, example = "0")
   @NotBlank(message = "committedAs不能为空")
  private Integer committedAs;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "vmallocTotal", value = "", required = true, example = "0")
   @NotBlank(message = "vmallocTotal不能为空")
  private Integer vmallocTotal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "vmallocUsed", value = "", required = true, example = "0")
   @NotBlank(message = "vmallocUsed不能为空")
  private Integer vmallocUsed;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "vmallocChunk", value = "", required = true, example = "0")
   @NotBlank(message = "vmallocChunk不能为空")
  private Integer vmallocChunk;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hardwareCorrupted", value = "", required = true, example = "0")
   @NotBlank(message = "hardwareCorrupted不能为空")
  private Integer hardwareCorrupted;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "anonHugePages", value = "", required = true, example = "0")
   @NotBlank(message = "anonHugePages不能为空")
  private Integer anonHugePages;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesTotal", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesTotal不能为空")
  private Integer hugePagesTotal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesFree", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesFree不能为空")
  private Integer hugePagesFree;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesRsvd", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesRsvd不能为空")
  private Integer hugePagesRsvd;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesSurp", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesSurp不能为空")
  private Integer hugePagesSurp;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesize", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesize不能为空")
  private Integer hugePagesize;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "directMap4k", value = "", required = true, example = "0")
   @NotBlank(message = "directMap4k不能为空")
  private Integer directMap4k;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "directMap2m", value = "", required = true, example = "0")
   @NotBlank(message = "directMap2m不能为空")
  private Integer directMap2m;
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
