package pwd.initializr.search.rpc;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * pwd.initializr.search.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-19 14:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@ApiModel
public class RPCDocument implements Serializable {

  private static final long serialVersionUID = -1659590069838613585L;

  @ApiModelProperty(name = "id", value = "id", required = false, example = "id", dataType = "java.lang.String")
  private String id;
  @ApiModelProperty(name = "able", value = "able", required = false, example = "able", dataType = "java.lang.String")
  private String able;
  @ApiModelProperty(name = "title", value = "title", required = false, example = "title", dataType = "java.lang.String")
  private String title;
  @ApiModelProperty(name = "source", value = "source", required = false, example = "source", dataType = "java.lang.String")
  private String source;
  @ApiModelProperty(name = "tags", value = "tags", required = false, example = "tags", dataType = "java.util.List")
  private Set<String> tags;
  @ApiModelProperty(name = "contents", value = "contents", required = false, example = "contents", dataType = "java.util.List")
  private List<String> contents;
  @ApiModelProperty(name = "link", value = "link", required = false, example = "link", dataType = "java.lang.String")
  private String link;
  @ApiModelProperty(name = "time", value = "time", required = false, example = "time", dataType = "java.lang.Date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private String time;
}
