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
@ApiModel(value = "HostEthernetStatInput", description = "HostEthernetStat请求参数")
public class HostEthernetStatInput implements Serializable {

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
   @ApiModelProperty(name = "interFace", value = "", required = true, example = "")
   @NotBlank(message = "interFace不能为空")
   private String interFace;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "receiveBytes", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "receiveBytes须为整数")
   private Integer receiveBytes;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "receivePackets", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "receivePackets须为整数")
   private Integer receivePackets;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "receiveErrs", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "receiveErrs须为整数")
   private Integer receiveErrs;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "receiveDrop", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "receiveDrop须为整数")
   private Integer receiveDrop;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "receiveFifo", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "receiveFifo须为整数")
   private Integer receiveFifo;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "receiveFrame", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "receiveFrame须为整数")
   private Integer receiveFrame;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "receiveCompressed", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "receiveCompressed须为整数")
   private Integer receiveCompressed;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "receiveMulticast", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "receiveMulticast须为整数")
   private Integer receiveMulticast;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "transmitBytes", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "transmitBytes须为整数")
   private Integer transmitBytes;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "transmitPackets", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "transmitPackets须为整数")
   private Integer transmitPackets;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "transmitErrs", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "transmitErrs须为整数")
   private Integer transmitErrs;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "transmitDrop", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "transmitDrop须为整数")
   private Integer transmitDrop;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "transmitFifo", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "transmitFifo须为整数")
   private Integer transmitFifo;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "transmitColls", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "transmitColls须为整数")
   private Integer transmitColls;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "transmitCarrier", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "transmitCarrier须为整数")
   private Integer transmitCarrier;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "transmitCompressed", value = "", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "transmitCompressed须为整数")
   private Integer transmitCompressed;

}
