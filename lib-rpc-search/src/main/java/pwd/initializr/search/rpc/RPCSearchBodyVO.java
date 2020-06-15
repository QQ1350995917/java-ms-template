package pwd.initializr.search.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class RPCSearchBodyVO implements Serializable {

  private static final long serialVersionUID = -1659590069838613585L;

  @ApiModelProperty(name = "esId", value = "esId", required = false, example = "esId", dataType = "java.lang.String")
  private String esId;
  @ApiModelProperty(name = "esVisibility", value = "esVisibility", required = false, example = "esVisibility", dataType = "java.lang.String")
  private String esVisibility;
  @ApiModelProperty(name = "esTitle", value = "esTitle", required = false, example = "esTitle", dataType = "java.lang.String")
  private String esTitle;
  @ApiModelProperty(name = "esContent", value = "esContent", required = false, example = "esContent", dataType = "java.util.List")
  private List<String> esContent;
  @ApiModelProperty(name = "esLinkTo", value = "esLinkTo", required = false, example = "esLinkTo", dataType = "java.lang.String")
  private String esLinkTo;
  @ApiModelProperty(name = "esUpdateTime", value = "esUpdateTime", required = false, example = "esUpdateTime", dataType = "java.lang.Date")
  private String esUpdateTime;
}
