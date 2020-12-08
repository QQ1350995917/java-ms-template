package pwd.initializr.email.persistence.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * pwd.initializr.email.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-08 19:06
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EmailAttachmentEntity {
    @Field("fileName")
    private String fileName;
    @Field("cid")
    private String cid;
    @Field("contentType")
    private String contentType;
    @Field("url")
    private String url;
}
