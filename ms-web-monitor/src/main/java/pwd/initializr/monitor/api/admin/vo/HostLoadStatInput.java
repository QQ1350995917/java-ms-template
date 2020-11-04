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
@ApiModel(value = "HostLoadStatInput", description = "HostLoadStat请求参数")
public class HostLoadStatInput implements Serializable {

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
    * 1分钟统计负载情况
    */
   @ApiModelProperty(name = "loadIn1m", value = "1分钟统计负载情况", required = true, example = "")
   @NotBlank(message = "loadIn1m不能为空")
   private String loadIn1m;

   /**
    * 
    * 5分钟统计负载情况
    */
   @ApiModelProperty(name = "loadIn5m", value = "5分钟统计负载情况", required = true, example = "")
   @NotBlank(message = "loadIn5m不能为空")
   private String loadIn5m;

   /**
    * 
    * 15分钟统计负载情况
    */
   @ApiModelProperty(name = "loadIn15m", value = "15分钟统计负载情况", required = true, example = "")
   @NotBlank(message = "loadIn15m不能为空")
   private String loadIn15m;

   /**
    * 
    * 运行进程和总进程之比
    */
   @ApiModelProperty(name = "processRate", value = "运行进程和总进程之比", required = true, example = "")
   @NotBlank(message = "processRate不能为空")
   private String processRate;

   /**
    * 
    * 最近运行进程的ID
    */
   @ApiModelProperty(name = "lastProcessId", value = "最近运行进程的ID", required = true, example = "")
   @NotBlank(message = "lastProcessId不能为空")
   private String lastProcessId;

}
