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
  private String background = "#00000000";
  @ApiModelProperty(name = "content", value = "内容")
  @NotNull(message = "0")
  private String content ="好雨知时节\r\n当春乃发生\r\n随风潜入夜\r\n润物细无声\r\n野径云俱黑\r\n江船火独明\r\n晓看红湿处\r\n花重锦官城";
  @ApiModelProperty(name = "createTime", value = "创建时间")
  @NotNull(message = "0")
  private Long createTime;
  @ApiModelProperty(name = "fontId", value = "字体ID")
  @NotNull(message = "0")
  private Long fontId = 74L;
  @ApiModelProperty(name = "fontSize", value = "内容字体大小")
  @NotNull(message = "0")
  private Float fontSize = 12.0F;
  @ApiModelProperty(name = "foreground", value = "前景色")
  @NotNull(message = "0")
  private String foreground = "#FFFFFFFF";
  @ApiModelProperty(name = "height", value = "图片高度")
  @NotNull(message = "0")
  private Integer height = 120;
  @ApiModelProperty(name = "id", value = "ID")
  @NotNull(message = "0")
  private Long id;
  @ApiModelProperty(name = "imageUrl", value = "图片地址")
  @NotNull(message = "0")
  private String imageUrl;
  @ApiModelProperty(name = "width", value = "图片宽度")
  @NotNull(message = "0")
  private Integer width = 120;

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
