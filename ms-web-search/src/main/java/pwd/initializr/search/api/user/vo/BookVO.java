package pwd.initializr.search.api.user.vo;

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
 * date 2020-01-05 22:44
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
@ApiModel(value = "bookVO", description = "图书VO属性")
public class BookVO {

  private Long id;
  private String isbn;
  private String title;
  private String subTitle;
  private String authorId;
  private String authorName;
  private String summary;
  private Set<String> thumbs;
  private Set<String> labels;
  private String publisher;
  private String publicationTime;
  private Integer status;
  private Long createTime;
  private Long updateTime;
}