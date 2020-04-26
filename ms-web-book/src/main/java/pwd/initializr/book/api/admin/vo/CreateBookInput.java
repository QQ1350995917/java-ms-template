package pwd.initializr.book.api.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * pwd.initializr.book.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:17
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
public class CreateBookInput {
  @ApiModelProperty(name = "title", value = "图书名称", required = true, example = "古文观止")
  @NotNull(message = "0")
  private String title;
  @ApiModelProperty(name = "subTitle", value = "图书副名称", required = false, example = "古文观止")
  @NotNull(message = "0")
  private String subTitle;
  @ApiModelProperty(name = "summary", value = "图书简介", required = false, example = "《古文观止》是清代吴楚材、吴调侯于康熙三十三年（1694年）选定的古代散文选本。该书是清朝康熙年间选编的一部供学塾使用的文学读本，此书是为学生编的教材，康熙三十四年（1695年）正式镌版印刷。")
  @NotNull(message = "0")
  private String summary;
  @ApiModelProperty(name = "labels", value = "标签", required = false, example = "文言文")
  @NotNull(message = "0")
  private Set<String> labels;
  @ApiModelProperty(name = "thumbs", value = "图片", required = false, example = "http://www.image.pwd/guwenguanzhi.png")
  @NotNull(message = "0")
  private Set<String> thumbs;
}
