package pwd.initializr.book.persistence.dao;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document(collection = "book")
public class ArticleEntity {

  @Id
  @AutoIncKey
  @Field("id")
  private Long id;
  @Field("bookId")
  private Long bookId;
  @Field("title")
  private String title;
  @Field("sub_title")
  private String subTitle;
  @Field("paragraphs")
  private List<String> paragraphs;
  @Field("status")
  private Integer status;
  @Field("create_time")
  private Long createTime;
  @Field("update_time")
  private Long updateTime;
}
