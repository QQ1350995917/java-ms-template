package pwd.initializr.search.persistence.entity;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * pwd.initializr.book.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:32
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
@Data
@Document(indexName = "book", type = "article")
public class ArticleDocument {

  @Field(type = FieldType.Keyword)
  private Long id;
  @Field(type = FieldType.Keyword)
  private Long bookId;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String title;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String subTitle;
  @Field(type = FieldType.Keyword)
  private String authorId;
  @Field(type = FieldType.Keyword)
  private String authorName;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String summary;
  @Field(type = FieldType.Keyword)
  private Set<String> thumbs;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private Set<String> paragraphs;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private Set<String> labels;
  @Field(type = FieldType.Keyword)
  private String fromUrl;
  @Field(type = FieldType.Keyword)
  private Integer status;
  @Field(type = FieldType.Keyword)
  private String createTime;
  @Field(type = FieldType.Keyword)
  private String updateTime;

}







