package pwd.initializr.email.api.robot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * project-generator-test@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
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
@ApiModel(value = "EmailContentInput", description = "EmailContent响应参数")
public class EmailContentOutput implements Serializable {

    /**
     * 主键 email表外键，同时作为主键
     */
    @ApiModelProperty(name = "emailId", value = "email表外键，同时作为主键", required = true, example = "")
    @NotBlank(message = "emailId不能为空")
    private Long emailId;
    /**
     * 邮件标题
     */
    @ApiModelProperty(name = "subject", value = "邮件标题", required = true, example = "")
    @NotBlank(message = "subject不能为空")
    private String subject;
    /**
     * 邮件内容
     */
    @ApiModelProperty(name = "content", value = "邮件内容", required = true, example = "")
    @NotBlank(message = "content不能为空")
    private String content;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", required = true, example = "")
    @NotBlank(message = "createTime不能为空")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间", required = true, example = "")
    @NotBlank(message = "updateTime不能为空")
    private Date updateTime;
}
