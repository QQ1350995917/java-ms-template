package pwd.initializr.search.persistence.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * pwd.initializr.search.persistence.entity@ms-web-initializr
 *
 * <h1>该类的定义不应该设置ES ID，应该根据不同的业务场景由实现类控制</h1>
 *
 * date 2020-05-12 18:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
//@Document(indexName = "document", type = "document", shards = 3, replicas = 1,createIndex = false)
//@Document(indexName = "book", type = "book", createIndex = false)
public class DocumentEntity implements Serializable {

  private static final long serialVersionUID = 3025999016837937935L;

  @Id
  private String id;

  @Field(type = FieldType.Keyword)
  private String able;

  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String title;

  @Field(type = FieldType.Keyword)
  private String source;

  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private Set<String> tags;

  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private List<String> contents;

  @Field(type = FieldType.Keyword)
  private String link;

  @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
  private String time;



  // 文档中的字段
  public static final String DOCUMENT_PROPERTIES_ID = "id";
  public static final String DOCUMENT_PROPERTIES_ABLE = "able";
  public static final String DOCUMENT_PROPERTIES_TITLE = "title";
  public static final String DOCUMENT_PROPERTIES_SOURCE = "source";
  public static final String DOCUMENT_PROPERTIES_TAGS = "tags";
  public static final String DOCUMENT_PROPERTIES_CONTENTS = "contents";
  public static final String DOCUMENT_PROPERTIES_LINK = "link";
  public static final String DOCUMENT_PROPERTIES_TIME = "time";

  // 全文检索需要命中的字段
  public static final Set<String> FULL_TEXT_SEARCH_PROPERTIES =
      Arrays.asList(
          DOCUMENT_PROPERTIES_TITLE,
          DOCUMENT_PROPERTIES_TAGS,
          DOCUMENT_PROPERTIES_CONTENTS)
      .stream().collect(Collectors.toSet());


}
