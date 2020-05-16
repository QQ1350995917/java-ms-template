package pwd.initializr.book.api.user.vo;

import io.swagger.annotations.ApiModel;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.book.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-05 22:55
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
@ApiModel(value = "bookArticleVO", description = "图书文章VO属性")
public class ArticleVO extends BaseVO {

  private Long bookId;
  private Set<String> paragraphs;
}
