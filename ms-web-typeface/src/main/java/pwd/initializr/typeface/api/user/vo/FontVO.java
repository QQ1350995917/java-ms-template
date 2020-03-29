package pwd.initializr.typeface.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.typeface.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 20:52
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@ApiModel
public class FontVO {

  @ApiModelProperty(name = "createTime", value = "真实姓名")
  @NotNull(message = "0")
  private Long createTime;
  @ApiModelProperty(name = "id", value = "ID")
  @NotNull(message = "0")
  private Long id;
  @ApiModelProperty(name = "name", value = "字体名称")
  @NotNull(message = "0")
  private String name;
  @ApiModelProperty(name = "thumbUrl", value = "图片地址")
  @NotNull(message = "0")
  private String thumbUrl;
  @ApiModelProperty(name = "updateTime", value = "更新时间")
  @NotNull(message = "0")
  private Long updateTime;

  public FontVO(Long id, String name, String thumbUrl, Long createTime, Long updateTime) {
    this.id = id;
    this.name = name;
    this.thumbUrl = thumbUrl;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
