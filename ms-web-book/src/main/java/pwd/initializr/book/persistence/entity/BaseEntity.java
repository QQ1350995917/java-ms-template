package pwd.initializr.book.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * pwd.initializr.book.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 12:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = -8592352525736469396L;

  @Id
  @AutoIncKey
  @Field("id")
  private Long id;
  @Field("title")
  private String title;
  @Field("sub_title")
  private String subTitle;
  @Field("author_id")
  private String authorId;
  @Field("author_name")
  private String authorName;
  @Field("summary")
  private String summary;
  @Field("labels")
  private Set<String> labels;
  @Field("publication_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date publicationTime = new Date();
  @Field("status")
  private Integer status;
  @Field("create_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime = new Date();
  @Field("update_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime = new Date();
}
