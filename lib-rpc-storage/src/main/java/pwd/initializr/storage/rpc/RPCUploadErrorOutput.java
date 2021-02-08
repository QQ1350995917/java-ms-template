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
 * date 2020-03-31 21:49
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
public class RPCUploadErrorOutput implements Serializable {

  @ApiModelProperty(name = "error", value = "错误描述", required = true, example = "file size is 0")
  private String error;
  @ApiModelProperty(name = "index", value = "文件顺序", required = true, example = "0")
  private Integer index;
  @ApiModelProperty(name = "originalFileName", value = "文件名称", required = true, example = "originalFileName")
  private String originalFileName;
}
