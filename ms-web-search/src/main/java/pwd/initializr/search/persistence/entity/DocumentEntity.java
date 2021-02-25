package pwd.initializr.search.persistence.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
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
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String content;
  @Field(type = FieldType.Keyword)
  private String linkTo;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String updateTime;
  @Field(type = FieldType.Long)
  private Long version;

}
