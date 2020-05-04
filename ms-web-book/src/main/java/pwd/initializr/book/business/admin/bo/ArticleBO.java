package pwd.initializr.book.business.admin.bo;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.book.business.admin.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:39
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
public class ArticleBO {

  private Long id;
  private Long bookId;
  private String title;
  private String subTitle;
  private String authorId;
  private String authorName;
  private String summary;
  private Set<String> thumbs;
  private Boolean paragraphTokenizer;
  private Set<String> paragraphs;
  private Set<String> labels;
  private String fromUrl;
  private Integer status;
  private String createTime;
  private String updateTime;
}
