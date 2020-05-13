package pwd.initializr.book.rpc;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
public class BookIntoSearch extends BaseInfo {

  @ApiModelProperty(name = "isbn", value = "isbn", required = false, example = "00000000000000", dataType = "java.lang.String")
  private String isbn;
  @ApiModelProperty(name = "thumbs", value = "thumbs", required = false, example = "http://domain.com/path/id.png", dataType = "java.util.LinkedHashSet")
  private LinkedHashSet<String> thumbs;
  @ApiModelProperty(name = "publisher", value = "publisher", required = false, example = "人民教育出版社", dataType = "java.lang.String")
  private String publisher;
}
