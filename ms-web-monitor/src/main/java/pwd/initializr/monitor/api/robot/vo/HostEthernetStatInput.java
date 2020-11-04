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
   private Long receiveBytes;

   /**
    *
    *
    */
   @ApiModelProperty(name = "receivePackets", value = "", required = true, example = "0")
   private Long receivePackets;

   /**
    *
    *
    */
   @ApiModelProperty(name = "receiveErrs", value = "", required = true, example = "0")
   private Long receiveErrs;

   /**
    *
    *
    */
   @ApiModelProperty(name = "receiveDrop", value = "", required = true, example = "0")
   private Long receiveDrop;

   /**
    *
    *
    */
   @ApiModelProperty(name = "receiveFifo", value = "", required = true, example = "0")
   private Long receiveFifo;

   /**
    *
    *
    */
   @ApiModelProperty(name = "receiveFrame", value = "", required = true, example = "0")
   private Long receiveFrame;

   /**
    *
    *
    */
   @ApiModelProperty(name = "receiveCompressed", value = "", required = true, example = "0")
   private Long receiveCompressed;

   /**
    *
    *
    */
   @ApiModelProperty(name = "receiveMulticast", value = "", required = true, example = "0")
   private Long receiveMulticast;

   /**
    *
    *
    */
   @ApiModelProperty(name = "transmitBytes", value = "", required = true, example = "0")
   private Long transmitBytes;

   /**
    *
    *
    */
   @ApiModelProperty(name = "transmitPackets", value = "", required = true, example = "0")
   private Long transmitPackets;

   /**
    *
    *
    */
   @ApiModelProperty(name = "transmitErrs", value = "", required = true, example = "0")
   private Long transmitErrs;

   /**
    *
    *
    */
   @ApiModelProperty(name = "transmitDrop", value = "", required = true, example = "0")
   private Long transmitDrop;

   /**
    *
    *
    */
   @ApiModelProperty(name = "transmitFifo", value = "", required = true, example = "0")
   private Long transmitFifo;

   /**
    *
    *
    */
   @ApiModelProperty(name = "transmitColls", value = "", required = true, example = "0")
   private Long transmitColls;

   /**
    *
    *
    */
   @ApiModelProperty(name = "transmitCarrier", value = "", required = true, example = "0")
   private Long transmitCarrier;

   /**
    *
    *
    */
   @ApiModelProperty(name = "transmitCompressed", value = "", required = true, example = "0")
   private Long transmitCompressed;

}
