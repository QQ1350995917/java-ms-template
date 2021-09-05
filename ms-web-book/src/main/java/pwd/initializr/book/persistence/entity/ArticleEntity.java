package pwd.initializr.book.persistence.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * pwd.initializr.book.persistence.entity@ms-web-initializr
 *
 * <h1>文章对象</h1>
 * 可独立描述一篇文章，不依托{@link BookEntity}。
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
public class ArticleEntity extends BaseEntity {

  @Field("paragraphs")
  private List<String> paragraphs;
  @Field("from_url")
  private String fromUrl;

}
