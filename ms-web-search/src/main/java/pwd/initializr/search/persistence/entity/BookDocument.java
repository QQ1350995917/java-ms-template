package pwd.initializr.search.persistence.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * pwd.initializr.book.persistence.entity@ms-web-initializr
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
@Setter
@Getter
@ToString
@Document(indexName = "book", type = "article")
public class BookDocument {

  private Long id;
  private Long uid;
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
  private String createTime;
  private String updateTime;
  private Set<String> words;

}
