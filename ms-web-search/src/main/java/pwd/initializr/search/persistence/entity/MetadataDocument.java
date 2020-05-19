package pwd.initializr.search.persistence.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class MetadataDocument implements Serializable {

  private static final long serialVersionUID = 3025999016837937935L;

  @Field(type = FieldType.Keyword)
  private String esAppId;
  @Field(type = FieldType.Keyword)
  private String esAppName;
  @Field(type = FieldType.Keyword)
  private String esSecretKey;
  @Field(type = FieldType.Keyword)
  private String esVisibility;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String esTitle;
  @Field(type = FieldType.Keyword)
  private String esType;
  @Field(type = FieldType.Keyword)
  private String esLinkTo;
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private Date esUpdateTime;

}
