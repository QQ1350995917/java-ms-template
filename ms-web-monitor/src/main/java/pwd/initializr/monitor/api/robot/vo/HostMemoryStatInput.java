package pwd.initializr.monitor.api.robot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.Digits;
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
   @Digits(integer = 10, fraction = 0, message = "memTotal须为整数")
   private Integer memTotal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "memFree", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "memFree须为整数")
   private Integer memFree;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "memAvailable", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "memAvailable须为整数")
   private Integer memAvailable;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "buffers", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "buffers须为整数")
   private Integer buffers;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "cached", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "cached须为整数")
   private Integer cached;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "swapCached", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "swapCached须为整数")
   private Integer swapCached;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "active", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "active须为整数")
   private Integer active;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "inactive", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "inactive须为整数")
   private Integer inactive;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "activeAnon", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "activeAnon须为整数")
   private Integer activeAnon;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "inactiveAnon", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "inactiveAnon须为整数")
   private Integer inactiveAnon;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "activeFile", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "activeFile须为整数")
   private Integer activeFile;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "inactiveFile", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "inactiveFile须为整数")
   private Integer inactiveFile;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "unevictable", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "unevictable须为整数")
   private Integer unevictable;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "mlocked", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "mlocked须为整数")
   private Integer mlocked;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "swapTotal", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "swapTotal须为整数")
   private Integer swapTotal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "swapFree", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "swapFree须为整数")
   private Integer swapFree;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "dirty", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "dirty须为整数")
   private Integer dirty;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "writeback", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "writeback须为整数")
   private Integer writeback;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "anonPages", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "anonPages须为整数")
   private Integer anonPages;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "mapped", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "mapped须为整数")
   private Integer mapped;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "shmem", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "shmem须为整数")
   private Integer shmem;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "slab", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "slab须为整数")
   private Integer slab;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "sReclaimable", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "sReclaimable须为整数")
   private Integer sReclaimable;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "sUnreclaim", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "sUnreclaim须为整数")
   private Integer sUnreclaim;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "kernelStack", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "kernelStack须为整数")
   private Integer kernelStack;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "pageTables", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "pageTables须为整数")
   private Integer pageTables;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "nFsunstable", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "nFsunstable须为整数")
   private Integer nFsunstable;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "bounce", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "bounce须为整数")
   private Integer bounce;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "writebackTmp", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "writebackTmp须为整数")
   private Integer writebackTmp;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "commitLimit", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "commitLimit须为整数")
   private Integer commitLimit;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "committedAs", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "committedAs须为整数")
   private Integer committedAs;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "vmallocTotal", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "vmallocTotal须为整数")
   private Integer vmallocTotal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "vmallocUsed", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "vmallocUsed须为整数")
   private Integer vmallocUsed;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "vmallocChunk", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "vmallocChunk须为整数")
   private Integer vmallocChunk;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hardwareCorrupted", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "hardwareCorrupted须为整数")
   private Integer hardwareCorrupted;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "anonHugePages", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "anonHugePages须为整数")
   private Integer anonHugePages;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesTotal", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "hugePagesTotal须为整数")
   private Integer hugePagesTotal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesFree", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "hugePagesFree须为整数")
   private Integer hugePagesFree;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesRsvd", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "hugePagesRsvd须为整数")
   private Integer hugePagesRsvd;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesSurp", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "hugePagesSurp须为整数")
   private Integer hugePagesSurp;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "hugePagesize", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "hugePagesize须为整数")
   private Integer hugePagesize;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "directMap4k", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "directMap4k须为整数")
   private Integer directMap4k;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "directMap2m", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "directMap2m须为整数")
   private Integer directMap2m;

}
