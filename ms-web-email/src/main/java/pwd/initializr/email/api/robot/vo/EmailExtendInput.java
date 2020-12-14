package pwd.initializr.email.api.robot.vo;

import java.util.Date;
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
 * date 2020-12-14 16:13
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
@ApiModel(value = "EmailExtendInput", description = "EmailExtend请求参数")
public class EmailExtendInput implements Serializable {

   /**
    *
    * email表外键
    */
   @ApiModelProperty(name = "emailId", value = "email表外键", required = true, example = "")
   @Digits(integer = 19, fraction = 0, message = "emailId须为整数")
   private Long emailId;

   /**
    *
    * 扩展内容的key值
    */
   @ApiModelProperty(name = "key", value = "扩展内容的key值", required = true, example = "")
   @NotBlank(message = "key不能为空")
   private String key;

   /**
    *
    * 扩展内容的value值
    */
   @ApiModelProperty(name = "value", value = "扩展内容的value值", required = true, example = "")
   @NotBlank(message = "value不能为空")
   private String value;

}
