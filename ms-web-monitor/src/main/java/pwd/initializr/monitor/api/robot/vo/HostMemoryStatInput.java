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
@ApiModel(value = "HostMemoryStatInput", description = "HostMemoryStat请求参数")
public class HostMemoryStatInput implements Serializable {

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
   private Long memTotal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "memFree", value = "", required = true, example = "0")
   private Long memFree;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "memAvailable", value = "", required = true, example = "0")
   private Long memAvailable;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "buffers", value = "", required = true, example = "0")
   private Long buffers;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "cached", value = "", required = true, example = "0")
   private Long cached;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "swapCached", value = "", required = true, example = "0")
   private Long swapCached;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "active", value = "", required = true, example = "0")
   private Long active;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "inactive", value = "", required = true, example = "0")
   private Long inactive;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "activeAnon", value = "", required = true, example = "0")
   private Long activeAnon;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "inactiveAnon", value = "", required = true, example = "0")
   private Long inactiveAnon;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "activeFile", value = "", required = true, example = "0")
   private Long activeFile;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "inactiveFile", value = "", required = true, example = "0")
   private Long inactiveFile;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "unevictable", value = "", required = true, example = "0")
   private Long unevictable;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "mlocked", value = "", required = true, example = "0")
   private Long mlocked;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "swapTotal", value = "", required = true, example = "0")
   private Long swapTotal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "swapFree", value = "", required = true, example = "0")
   private Long swapFree;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "dirty", value = "", required = true, example = "0")
   private Long dirty;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "writeback", value = "", required = true, example = "0")
   private Long writeback;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "anonPages", value = "", required = true, example = "0")
   private Long anonPages;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "mapped", value = "", required = true, example = "0")
   private Long mapped;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "shmem", value = "", required = true, example = "0")
   private Long shmem;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "slab", value = "", required = true, example = "0")
   private Long slab;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "sReclaimable", value = "", required = true, example = "0")
   private Long sReclaimable;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "sUnreclaim", value = "", required = true, example = "0")
   private Long sUnreclaim;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "kernelStack", value = "", required = true, example = "0")
   private Long kernelStack;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "pageTables", value = "", required = true, example = "0")
   private Long pageTables;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "nFsunstable", value = "", required = true, example = "0")
   private Long nFsunstable;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "bounce", value = "", required = true, example = "0")
   private Long bounce;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "writebackTmp", value = "", required = true, example = "0")
   private Long writebackTmp;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "commitLimit", value = "", required = true, example = "0")
   private Long commitLimit;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "committedAs", value = "", required = true, example = "0")
   private Long committedAs;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "vmallocTotal", value = "", required = true, example = "0")
   private Long vmallocTotal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "vmallocUsed", value = "", required = true, example = "0")
   private Long vmallocUsed;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "vmallocChunk", value = "", required = true, example = "0")
   private Long vmallocChunk;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hardwareCorrupted", value = "", required = true, example = "0")
   private Long hardwareCorrupted;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "anonHugePages", value = "", required = true, example = "0")
   private Long anonHugePages;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesTotal", value = "", required = true, example = "0")
   private Long hugePagesTotal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesFree", value = "", required = true, example = "0")
   private Long hugePagesFree;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesRsvd", value = "", required = true, example = "0")
   private Long hugePagesRsvd;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesSurp", value = "", required = true, example = "0")
   private Long hugePagesSurp;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesize", value = "", required = true, example = "0")
   private Long hugePagesize;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "directMap4k", value = "", required = true, example = "0")
   private Long directMap4k;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "directMap2m", value = "", required = true, example = "0")
   private Long directMap2m;

}
