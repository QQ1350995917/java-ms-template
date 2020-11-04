package pwd.initializr.monitor.api.admin.vo;

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
@ApiModel(value = "HostCpuInput", description = "HostCpu请求参数")
public class HostCpuInput implements Serializable {

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

   /**
    *
    *
    */
   @ApiModelProperty(name = "processor", value = "", required = false, example = "")
   private String processor;

   /**
    *
    *
    */
   @ApiModelProperty(name = "vendorId", value = "", required = false, example = "")
   private String vendorId;

   /**
    *
    *
    */
   @ApiModelProperty(name = "cpuFamily", value = "", required = false, example = "")
   private String cpuFamily;

   /**
    *
    *
    */
   @ApiModelProperty(name = "model", value = "", required = false, example = "")
   private String model;

   /**
    *
    *
    */
   @ApiModelProperty(name = "modelName", value = "", required = false, example = "")
   private String modelName;

   /**
    *
    *
    */
   @ApiModelProperty(name = "stepping", value = "", required = false, example = "")
   private String stepping;

   /**
    *
    *
    */
   @ApiModelProperty(name = "microcode", value = "", required = false, example = "")
   private String microcode;

   /**
    *
    *
    */
   @ApiModelProperty(name = "cpuMhz", value = "", required = false, example = "")
   private String cpuMhz;

   /**
    *
    *
    */
   @ApiModelProperty(name = "cacheSize", value = "", required = false, example = "")
   private String cacheSize;

   /**
    *
    *
    */
   @ApiModelProperty(name = "physicalId", value = "", required = false, example = "")
   private String physicalId;

   /**
    *
    *
    */
   @ApiModelProperty(name = "siblings", value = "", required = false, example = "")
   private String siblings;

   /**
    *
    *
    */
   @ApiModelProperty(name = "coreId", value = "", required = false, example = "")
   private String coreId;

   /**
    *
    *
    */
   @ApiModelProperty(name = "cpuCores", value = "", required = false, example = "")
   private String cpuCores;

   /**
    *
    *
    */
   @ApiModelProperty(name = "apicid", value = "", required = false, example = "")
   private String apicid;

   /**
    *
    *
    */
   @ApiModelProperty(name = "initialApicid", value = "", required = false, example = "")
   private String initialApicid;

   /**
    *
    *
    */
   @ApiModelProperty(name = "fpu", value = "", required = false, example = "")
   private String fpu;

   /**
    *
    *
    */
   @ApiModelProperty(name = "fpuException", value = "", required = false, example = "")
   private String fpuException;

   /**
    *
    *
    */
   @ApiModelProperty(name = "cpuidLevel", value = "", required = false, example = "")
   private String cpuidLevel;

   /**
    *
    *
    */
   @ApiModelProperty(name = "wp", value = "", required = false, example = "")
   private String wp;

   /**
    *
    *
    */
   @ApiModelProperty(name = "flags", value = "", required = false, example = "")
   private String flags;

   /**
    *
    *
    */
   @ApiModelProperty(name = "bogomips", value = "", required = false, example = "")
   private String bogomips;

   /**
    *
    *
    */
   @ApiModelProperty(name = "clflushSize", value = "", required = false, example = "")
   private String clflushSize;

   /**
    *
    *
    */
   @ApiModelProperty(name = "cacheAlignment", value = "", required = false, example = "")
   private String cacheAlignment;

   /**
    *
    *
    */
   @ApiModelProperty(name = "addressSizes", value = "", required = false, example = "")
   private String addressSizes;

   /**
    *
    *
    */
   @ApiModelProperty(name = "powerManagement", value = "", required = false, example = "")
   private String powerManagement;

}
