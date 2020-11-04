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
  private Long memTotal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "memFree", value = "", required = true, example = "0")
   @NotBlank(message = "memFree不能为空")
  private Long memFree;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "memAvailable", value = "", required = true, example = "0")
   @NotBlank(message = "memAvailable不能为空")
  private Long memAvailable;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "buffers", value = "", required = true, example = "0")
   @NotBlank(message = "buffers不能为空")
  private Long buffers;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "cached", value = "", required = true, example = "0")
   @NotBlank(message = "cached不能为空")
  private Long cached;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "swapCached", value = "", required = true, example = "0")
   @NotBlank(message = "swapCached不能为空")
  private Long swapCached;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "active", value = "", required = true, example = "0")
   @NotBlank(message = "active不能为空")
  private Long active;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "inactive", value = "", required = true, example = "0")
   @NotBlank(message = "inactive不能为空")
  private Long inactive;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "activeAnon", value = "", required = true, example = "0")
   @NotBlank(message = "activeAnon不能为空")
  private Long activeAnon;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "inactiveAnon", value = "", required = true, example = "0")
   @NotBlank(message = "inactiveAnon不能为空")
  private Long inactiveAnon;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "activeFile", value = "", required = true, example = "0")
   @NotBlank(message = "activeFile不能为空")
  private Long activeFile;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "inactiveFile", value = "", required = true, example = "0")
   @NotBlank(message = "inactiveFile不能为空")
  private Long inactiveFile;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "unevictable", value = "", required = true, example = "0")
   @NotBlank(message = "unevictable不能为空")
  private Long unevictable;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "mlocked", value = "", required = true, example = "0")
   @NotBlank(message = "mlocked不能为空")
  private Long mlocked;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "swapTotal", value = "", required = true, example = "0")
   @NotBlank(message = "swapTotal不能为空")
  private Long swapTotal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "swapFree", value = "", required = true, example = "0")
   @NotBlank(message = "swapFree不能为空")
  private Long swapFree;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "dirty", value = "", required = true, example = "0")
   @NotBlank(message = "dirty不能为空")
  private Long dirty;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "writeback", value = "", required = true, example = "0")
   @NotBlank(message = "writeback不能为空")
  private Long writeback;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "anonPages", value = "", required = true, example = "0")
   @NotBlank(message = "anonPages不能为空")
  private Long anonPages;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "mapped", value = "", required = true, example = "0")
   @NotBlank(message = "mapped不能为空")
  private Long mapped;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "shmem", value = "", required = true, example = "0")
   @NotBlank(message = "shmem不能为空")
  private Long shmem;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "slab", value = "", required = true, example = "0")
   @NotBlank(message = "slab不能为空")
  private Long slab;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "sReclaimable", value = "", required = true, example = "0")
   @NotBlank(message = "sReclaimable不能为空")
  private Long sReclaimable;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "sUnreclaim", value = "", required = true, example = "0")
   @NotBlank(message = "sUnreclaim不能为空")
  private Long sUnreclaim;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "kernelStack", value = "", required = true, example = "0")
   @NotBlank(message = "kernelStack不能为空")
  private Long kernelStack;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "pageTables", value = "", required = true, example = "0")
   @NotBlank(message = "pageTables不能为空")
  private Long pageTables;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "nFsunstable", value = "", required = true, example = "0")
   @NotBlank(message = "nFsunstable不能为空")
  private Long nFsunstable;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "bounce", value = "", required = true, example = "0")
   @NotBlank(message = "bounce不能为空")
  private Long bounce;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "writebackTmp", value = "", required = true, example = "0")
   @NotBlank(message = "writebackTmp不能为空")
  private Long writebackTmp;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "commitLimit", value = "", required = true, example = "0")
   @NotBlank(message = "commitLimit不能为空")
  private Long commitLimit;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "committedAs", value = "", required = true, example = "0")
   @NotBlank(message = "committedAs不能为空")
  private Long committedAs;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "vmallocTotal", value = "", required = true, example = "0")
   @NotBlank(message = "vmallocTotal不能为空")
  private Long vmallocTotal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "vmallocUsed", value = "", required = true, example = "0")
   @NotBlank(message = "vmallocUsed不能为空")
  private Long vmallocUsed;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "vmallocChunk", value = "", required = true, example = "0")
   @NotBlank(message = "vmallocChunk不能为空")
  private Long vmallocChunk;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hardwareCorrupted", value = "", required = true, example = "0")
   @NotBlank(message = "hardwareCorrupted不能为空")
  private Long hardwareCorrupted;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "anonHugePages", value = "", required = true, example = "0")
   @NotBlank(message = "anonHugePages不能为空")
  private Long anonHugePages;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesTotal", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesTotal不能为空")
  private Long hugePagesTotal;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesFree", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesFree不能为空")
  private Long hugePagesFree;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesRsvd", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesRsvd不能为空")
  private Long hugePagesRsvd;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesSurp", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesSurp不能为空")
  private Long hugePagesSurp;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "hugePagesize", value = "", required = true, example = "0")
   @NotBlank(message = "hugePagesize不能为空")
  private Long hugePagesize;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "directMap4k", value = "", required = true, example = "0")
   @NotBlank(message = "directMap4k不能为空")
  private Long directMap4k;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "directMap2m", value = "", required = true, example = "0")
   @NotBlank(message = "directMap2m不能为空")
  private Long directMap2m;
  /**
   * 
   * 可用状态：0:不可用；1:可用
   */
  @ApiModelProperty(name = "able", value = "可用状态：0:不可用；1:可用", required = true, example = "0")
   @NotBlank(message = "able不能为空")
  private Long able;
  /**
   * 
   * 删除状态：0:未删除；1:已删除
   */
  @ApiModelProperty(name = "del", value = "删除状态：0:未删除；1:已删除", required = true, example = "0")
   @NotBlank(message = "del不能为空")
  private Long del;
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
