package pwd.initializr.storage.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * pwd.initializr.storage.api.robot.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 23:43
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RPCUploadInput {

  @ApiModelProperty(name = "appName", value = "appName", required = true, example = "appName")
  private String appName;
  @ApiModelProperty(name = "bucketName", value = "bucketName", required = true, example = "bucketName")
  private String bucketName;
  @ApiModelProperty(name = "objectName", value = "objectName", required = true, example = "objectName")
  private String objectName;
}
