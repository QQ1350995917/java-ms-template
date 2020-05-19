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
import pwd.initializr.search.rpc.RPCSearchMetadataInput;

/**
 * pwd.initializr.book.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 13:57
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
public class RPCBookIntoSearchBaseInfo extends RPCSearchMetadataInput implements Serializable {

  private static final long serialVersionUID = 8618679319107108176L;

  @ApiModelProperty(name = "id", value = "id", required = false, example = "0", dataType = "java.lang.Long")
  private Long id;
  @ApiModelProperty(name = "title", value = "title", required = false, example = "title", dataType = "java.lang.String")
  private String title;
  @ApiModelProperty(name = "subTitle", value = "subTitle", required = false, example = "subTitle", dataType = "java.lang.String")
  private String subTitle;
  @ApiModelProperty(name = "authorId", value = "authorId", required = false, example = "0", dataType = "java.lang.Long")
  private Long authorId;
  @ApiModelProperty(name = "authorName", value = "authorName", required = false, example = "孔子", dataType = "java.lang.String")
  private String authorName;
  @ApiModelProperty(name = "summary", value = "summary", required = false, example = "前进，前进，不择手段的前进", dataType = "java.lang.String")
  private String summary;
  @ApiModelProperty(name = "labels", value = "labels", required = false, example = "人教版", dataType = "java.util.LinkedHashSet")
  private LinkedHashSet<String> labels;
  @ApiModelProperty(name = "publicationTime", value = "publicationTime", required = false, example = "2020-02-02 23:56:12", dataType = "java.util.Date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date publicationTime;
  @ApiModelProperty(name = "status", value = "status", required = false, example = "0", dataType = "java.lang.Integer")
  private Integer status;
  @ApiModelProperty(name = "createTime", value = "createTime", required = false, example = "2020-02-02 23:56:12", dataType = "java.util.Date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;
  @ApiModelProperty(name = "updateTime", value = "updateTime", required = false, example = "2020-02-02 23:56:12", dataType = "java.util.Date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;
}
