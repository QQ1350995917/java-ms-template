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
@ApiModel(value = "EmailContentInput", description = "EmailContent请求参数")
public class EmailContentInput implements Serializable {

   /**
    *
    * 邮件标题
    */
   @ApiModelProperty(name = "subject", value = "邮件标题", required = true, example = "")
   @NotBlank(message = "subject不能为空")
   private String subject;

   /**
    *
    * 邮件内容
    */
   @ApiModelProperty(name = "content", value = "邮件内容", required = true, example = "")
   @NotBlank(message = "content不能为空")
   private String content;

}
