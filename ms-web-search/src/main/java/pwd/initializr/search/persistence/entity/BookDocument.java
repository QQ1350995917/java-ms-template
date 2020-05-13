package pwd.initializr.search.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

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
@Document(indexName = "book", type = "book")
public class BookDocument extends MetadataDocument {

  @Id
  @Field(type = FieldType.Keyword)
  private Long id;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String isbn;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String title;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String subTitle;
  @Field(type = FieldType.Keyword)
  private Long authorId;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String authorName;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String summary;
  @Field(type = FieldType.Keyword)
  private LinkedHashSet<String> thumbs;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private LinkedHashSet<String> labels;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String publisher;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date publicationTime;
  @Field(type = FieldType.Keyword)
  private Integer status;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

}
