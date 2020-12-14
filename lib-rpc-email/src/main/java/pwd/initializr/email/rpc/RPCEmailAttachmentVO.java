package pwd.initializr.email.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.search.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-19 14:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@ApiModel("发送邮件的附件参数")
public class RPCEmailAttachmentVO implements Serializable {

    private static final long serialVersionUID = -4291776028708567323L;

    @ApiModelProperty(name = "fileName", value = "fileName", required = true, dataType = "java.lang.String")
    @NotBlank(message = "fileName不能为空")
    private String fileName;
    @ApiModelProperty(name = "cid", value = "cid", required = true, dataType = "java.lang.String")
    private String cid;
    @ApiModelProperty(name = "contentType", value = "contentType", required = true, dataType = "java.lang.String")
    @NotBlank(message = "contentType不能为空")
    private String contentType;
    @ApiModelProperty(name = "url", value = "url", required = true, dataType = "java.lang.String")
    @NotBlank(message = "url不能为空")
    private String url;
}
