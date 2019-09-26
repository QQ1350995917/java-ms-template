package pwd.initializr.storage.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.storage.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-26 18:14
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "downloadFileInput", description = "文件下载输入参数")
public class DownloadInput {

  @ApiModelProperty(name = "userId", value = "用户ID", required = true, example = "-1")
  @Null(message = "0")
  private Long userId;
  @ApiModelProperty(name = "url", value = "文件地址", required = true, example = "http://localhost:9000/test/2019-09-26/e8468e98-aa36-484e-9fec-0f476a217c16.jpg")
  @Null(message = "0")
  private String url;
}
