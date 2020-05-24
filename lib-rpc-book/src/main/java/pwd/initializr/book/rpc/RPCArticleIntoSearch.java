package pwd.initializr.book.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.storage.api.robot.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:43
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
public class RPCArticleIntoSearch extends RPCBookIntoSearchBaseInfo {

  @ApiModelProperty(name = "bookId", value = "bookId", required = false, example = "0", dataType = "java.lang.Long")
  private Long bookId;
  @ApiModelProperty(name = "paragraphs", value = "paragraphs", required = false, example = "段落内容", dataType = "java.util.LinkedHashSet")
  private LinkedList<String> paragraphs;
}
