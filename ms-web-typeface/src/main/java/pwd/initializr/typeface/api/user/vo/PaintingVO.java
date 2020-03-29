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
 * date 2020-03-29 21:21
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
public class PaintingVO {

  @ApiModelProperty(name = "background", value = "背景色")
  @NotNull(message = "0")
  private String background;
  @ApiModelProperty(name = "content", value = "内容")
  @NotNull(message = "0")
  private String content;
  @ApiModelProperty(name = "createTime", value = "创建时间")
  @NotNull(message = "0")
  private Long createTime;
  @ApiModelProperty(name = "fontId", value = "字体ID")
  @NotNull(message = "0")
  private Long fontId;
  @ApiModelProperty(name = "fontSize", value = "内容字体大小")
  @NotNull(message = "0")
  private Float fontSize;
  @ApiModelProperty(name = "foreground", value = "前景色")
  @NotNull(message = "0")
  private String foreground;
  @ApiModelProperty(name = "height", value = "图片高度")
  @NotNull(message = "0")
  private Integer height;
  @ApiModelProperty(name = "id", value = "ID")
  @NotNull(message = "0")
  private Long id;
  @ApiModelProperty(name = "imageUrl", value = "图片地址")
  @NotNull(message = "0")
  private String imageUrl;
  @ApiModelProperty(name = "width", value = "图片宽度")
  @NotNull(message = "0")
  private Integer width;

  public PaintingVO(Long id, Long fontId, Float fontSize, String content, String background,
      String foreground, Integer width, Integer height, String imageUrl, Long createTime) {
    this.id = id;
    this.fontId = fontId;
    this.fontSize = fontSize;
    this.content = content;
    this.background = background;
    this.foreground = foreground;
    this.width = width;
    this.height = height;
    this.imageUrl = imageUrl;
    this.createTime = createTime;
  }


}
