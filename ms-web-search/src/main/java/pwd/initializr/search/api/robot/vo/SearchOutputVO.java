package pwd.initializr.search.api.robot.vo;

import java.util.LinkedHashSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.book.rpc.SearchOutput;

/**
 * pwd.initializr.search.api.robot.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-17 16:11
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
public class SearchOutputVO extends SearchOutput {

  private Long bookId;
  private LinkedHashSet<String> paragraphs;
  private String fromUrl;
}
