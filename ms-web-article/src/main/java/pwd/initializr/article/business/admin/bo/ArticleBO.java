package pwd.initializr.article.business.admin.bo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import pwd.initializr.article.persistence.dao.AutoIncKey;

/**
 * pwd.initializr.article.business.admin.bo@ms-web-initializr
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
@Getter
@Setter
@ToString
public class ArticleBO {

  private Long id;
  private Long bookId;
  private String title;
  private String subTitle;
  private List<String> paragraphs;
  private Integer status;
  private Long createTime;
  private Long updateTime;
}
