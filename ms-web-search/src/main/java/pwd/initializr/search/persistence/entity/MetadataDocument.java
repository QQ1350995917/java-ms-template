package pwd.initializr.search.persistence.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * pwd.initializr.search.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
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

  @Id
  @Field(type = FieldType.Auto)
  private Long esId;
  @Field(type = FieldType.Keyword)
  private String esAppId;
  @Field(type = FieldType.Keyword)
  private String esAppName;
  @Field(type = FieldType.Keyword)
  private String esSecretKey;
  @Field(type = FieldType.Keyword)
  private String esVisibility;
  @Field(type = FieldType.Keyword)
  private String esTitle;
  @Field(type = FieldType.Keyword)
  private String esLinkTo;
}
