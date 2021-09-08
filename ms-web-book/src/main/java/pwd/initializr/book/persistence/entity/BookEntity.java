package pwd.initializr.book.persistence.entity;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * pwd.initializr.book.persistence.entity@ms-web-initializr
 *
 * <h1>图书类</h1>
 * 是{@link ArticleEntity}的集合。
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
@Document(collection = "book")
public class BookEntity extends BaseEntity {

  /***
   * 0：待整理
   * 1：纸质书
   * 2：电子书
   */
  @Field("type")
  private Integer type;
  @Field("isbn")
  private String isbn;
  @Field("thumbs")
  private LinkedHashSet<String> thumbs;
  @Field("publisher")
  private String publisher;
  @Field("articles")
  private LinkedHashSet<ArticleEntity> articles;
  @Field("tags")
  private Set<String> tags;

}
