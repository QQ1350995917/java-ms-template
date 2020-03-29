package pwd.initializr.typeface.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.common.web.api.vo.PageOutput;

/**
 * pwd.initializr.typeface.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 21:23
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "listPaintingOutput", description = "我的图片列表输出参数")
public class PaintingListOutput<T> extends PageOutput<T> {

  @ApiModelProperty(name = "content", value = "图片内容")
  @NotNull(message = "0")
  private String content;
  @ApiModelProperty(name = "fontId", value = "字体ID")
  @NotNull(message = "0")
  private Long fontId;
}
