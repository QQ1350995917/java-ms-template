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
@ApiModel(value = "EmailBoxInput", description = "EmailBox请求参数")
public class EmailBoxInput implements Serializable {

    /**
     * email表外键
     */
    @ApiModelProperty(name = "emailId", value = "email表外键", required = true, example = "")
    @Digits(integer = 19, fraction = 0, message = "emailId须为整数")
    private Long emailId;

    /**
     * 邮箱类型，0：发件箱，1：收件箱，2：抄送邮箱，3：密送邮箱
     */
    @ApiModelProperty(name = "type", value = "邮箱类型，0：发件箱，1：收件箱，2：抄送邮箱，3：密送邮箱", required = true, example = "")
    @Digits(integer = 10, fraction = 0, message = "type须为整数")
    private Integer type;

    /**
     * 邮箱地址
     */
    @ApiModelProperty(name = "mailbox", value = "邮箱地址", required = true, example = "")
    @NotBlank(message = "mailbox不能为空")
    private String mailbox;

}
