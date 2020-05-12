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
@Document(collection = "article")
public class ArticleEntity {

  @Id
  @AutoIncKey
  @Field("id")
  private Long id;
  @Field("book_id")
  private Long bookId;
  @Field("uid")
  private Long uid;
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
  @Field("paragraphs")
  private Set<String> paragraphs;
  @Field("labels")
  private Set<String> labels;
  @Field("from_url")
  private String fromUrl;
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
