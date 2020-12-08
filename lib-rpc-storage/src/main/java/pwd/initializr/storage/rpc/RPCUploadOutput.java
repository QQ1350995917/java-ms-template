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
public class RPCUploadOutput implements Serializable {

  @ApiModelProperty(name = "id", value = "文件ID", required = true, example = "id")
  private String id;
  @ApiModelProperty(name = "url", value = "文件访问url", required = true, example = "url")
  private String url;
  @ApiModelProperty(name = "createTime", value = "文件存储时间", required = true, example = "createTime")
  private Long createTime;
}
