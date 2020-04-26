package pwd.initializr.book.business.admin.bo;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * pwd.initializr.book.business.admin.bo@ms-web-initializr
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
public class BookBO {
  private Long id;
  private Long uid;
  private String title;
  private String subTitle;
  private String summary;
  private Set<String> labels;
  private Set<String> thumbs;
  private Integer status;
  private Long createTime;
  private Long updateTime;
}
