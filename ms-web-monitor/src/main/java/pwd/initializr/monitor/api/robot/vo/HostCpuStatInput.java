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
@ApiModel(value = "HostCpuStatInput", description = "HostCpuStat请求参数")
public class HostCpuStatInput implements Serializable {

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
   @Digits(integer = 10, fraction = 0, message = "user须为整数")
   private Integer user;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "nice", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "nice须为整数")
   private Integer nice;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "system", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "system须为整数")
   private Integer system;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "idle", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "idle须为整数")
   private Integer idle;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "iowait", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "iowait须为整数")
   private Integer iowait;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "irq", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "irq须为整数")
   private Integer irq;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "softirq", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "softirq须为整数")
   private Integer softirq;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "steal", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "steal须为整数")
   private Integer steal;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "guest", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "guest须为整数")
   private Integer guest;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "guestNice", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "guestNice须为整数")
   private Integer guestNice;

}
