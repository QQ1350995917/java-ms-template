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
    * 主机名
    */
   @ApiModelProperty(name = "cpu", value = "cpu", required = true, example = "")
   private String cpuCoreName;

   /**
    *
    *
    */
   @ApiModelProperty(name = "user", value = "", required = true, example = "0")
   private Long user;

   /**
    *
    *
    */
   @ApiModelProperty(name = "nice", value = "", required = true, example = "0")
   private Long nice;

   /**
    *
    *
    */
   @ApiModelProperty(name = "system", value = "", required = true, example = "0")
   private Long system;

   /**
    *
    *
    */
   @ApiModelProperty(name = "idle", value = "", required = true, example = "0")
   private Long idle;

   /**
    *
    *
    */
   @ApiModelProperty(name = "iowait", value = "", required = true, example = "0")
   private Long iowait;

   /**
    *
    *
    */
   @ApiModelProperty(name = "irq", value = "", required = true, example = "0")
   private Long irq;

   /**
    *
    *
    */
   @ApiModelProperty(name = "softirq", value = "", required = true, example = "0")
   private Long softirq;

   /**
    *
    *
    */
   @ApiModelProperty(name = "steal", value = "", required = true, example = "0")
   private Long steal;

   /**
    *
    *
    */
   @ApiModelProperty(name = "guest", value = "", required = true, example = "0")
   private Long guest;

   /**
    *
    *
    */
   @ApiModelProperty(name = "guestNice", value = "", required = true, example = "0")
   private Long guestNice;

}
