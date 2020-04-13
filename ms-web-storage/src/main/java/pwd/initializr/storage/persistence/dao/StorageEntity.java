package pwd.initializr.storage.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.description.field.FieldDescription.InGenericShape;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * pwd.initializr.storage.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 19:48
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
@Document(collection = "file")
public class StorageEntity {

  @Id
  @Field("id")
  private String id;
  @Field("uid")
  private Long userId;
  @Field("fn")
  private String filename;
  @Field("bn")
  private String bucketName;
  @Field("on")
  private String objectName;
  @Field("url")
  private String url;
  @Field("path")
  private String path;
  @Field("stat")
  private Integer status;
  @Field("ct")
  private Long createTime;
  @Field("ut")
  private Long updateTime;
}
