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
@ApiModel(value = "HostDiskStatInput", description = "HostDiskStat请求参数")
public class HostDiskStatInput implements Serializable {

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
   @Digits(integer = 10, fraction = 0, message = "majorDeviceNumber须为整数")
   private Integer majorDeviceNumber;

   /**
    *
    *
    */
   @ApiModelProperty(name = "minorDeviceNumber", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "minorDeviceNumber须为整数")
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
   private Double read;

   /**
    *
    *
    */
   @ApiModelProperty(name = "readMerge", value = "", required = true, example = "0")
   private Double readMerge;

   /**
    *
    *
    */
   @ApiModelProperty(name = "readSector", value = "", required = true, example = "0")
   private Double readSector;

   /**
    *
    *
    */
   @ApiModelProperty(name = "readSpentMilliseconds", value = "", required = true, example = "0")
   private Double readSpentMilliseconds;

   /**
    *
    *
    */
   @ApiModelProperty(name = "write", value = "", required = true, example = "0")
   private Double write;

   /**
    *
    *
    */
   @ApiModelProperty(name = "writeMerge", value = "", required = true, example = "0")
   private Double writeMerge;

   /**
    *
    *
    */
   @ApiModelProperty(name = "writeSector", value = "", required = true, example = "0")
   private Double writeSector;

   /**
    *
    *
    */
   @ApiModelProperty(name = "writeSpentMilliseconds", value = "", required = true, example = "0")
   private Double writeSpentMilliseconds;

   /**
    *
    *
    */
   @ApiModelProperty(name = "ioRequest", value = "", required = true, example = "0")
   private Double ioRequest;

   /**
    *
    *
    */
   @ApiModelProperty(name = "ioSpentMilliseconds", value = "", required = true, example = "0")
   private Double ioSpentMilliseconds;

   /**
    *
    *
    */
   @ApiModelProperty(name = "ioSpentAllMilliseconds", value = "", required = true, example = "0")
   private Double ioSpentAllMilliseconds;

}
