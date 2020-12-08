package pwd.initializr.storage.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ApiModel
public class RPCUploadInput implements Serializable {

  @ApiModelProperty(name = "app", value = "服务名称", required = true, example = "app")
  private String app;
  @ApiModelProperty(name = "bucketName", value = "文件桶名称", required = true, example = "bucketName")
  private String bucketName;
  @ApiModelProperty(name = "fileName", value = "文件名称，该名称将会作为下载的文件名", required = true, example = "objectName")
  private String fileName;
}
