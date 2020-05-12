package pwd.initializr.book.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
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
@Document(collection = "book")
public class BookEntity {

  @Id
  @AutoIncKey
  @Field("id")
  private Long id;
  @Field("uid")
  private Long uid;
  @Field("isbn")
  private String isbn;
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
  @Field("thumbs")
  private Set<String> thumbs;
  @Field("labels")
  private Set<String> labels;
  @Field("publisher")
  private String publisher;
  @Field("publication_time")
  private String publicationTime;
  @Field("status")
  private Integer status;
  @Field("create_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date createTime;
  @Field("update_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date updateTime;

}
