package pwd.initializr.email.api.robot.vo;

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
@ApiModel(value = "EmailAttachmentInput", description = "EmailAttachment请求参数")
public class EmailAttachmentInput implements Serializable {

    /**
     * email表外键
     */
    @ApiModelProperty(name = "emailId", value = "email表外键", required = true, example = "")
    @Digits(integer = 19, fraction = 0, message = "emailId须为整数")
    private Long emailId;

    /**
     * 附件名称
     */
    @ApiModelProperty(name = "fileName", value = "附件名称", required = true, example = "")
    @NotBlank(message = "fileName不能为空")
    private String fileName;

    /**
     * 邮件显示附件关联ID
     */
    @ApiModelProperty(name = "cid", value = "邮件显示附件关联ID", required = false, example = "")
    private String cid;

    /**
     * 附件MEMI类型
     */
    @ApiModelProperty(name = "contentType", value = "附件MEMI类型", required = false, example = "")
    private String contentType;

    /**
     * 附件地址
     */
    @ApiModelProperty(name = "url", value = "附件地址", required = true, example = "")
    @NotBlank(message = "url不能为空")
    private String url;

}
