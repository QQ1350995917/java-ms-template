package pwd.initializr.search.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
public class RPCSearchInitInput implements Serializable {

  private static final long serialVersionUID = -1659590069838613585L;

  @ApiModelProperty(name = "esAppId", value = "esAppId", required = false, example = "esAppId", dataType = "java.lang.String")
  private String esAppId;
  @ApiModelProperty(name = "esAppName", value = "esAppName", required = false, example = "esAppName", dataType = "java.lang.String")
  private String esAppName;
  @ApiModelProperty(name = "esSecretKey", value = "esSecretKey", required = false, example = "esSecretKey", dataType = "java.lang.String")
  private String esSecretKey;
  @ApiModelProperty(name = "esIndex", value = "esIndex", required = false, example = "esIndex", dataType = "java.lang.String")
  private String esIndex;
  @ApiModelProperty(name = "esBody", value = "esBody", required = false, example = "esBody", dataType = "java.util.List")
  private List<RPCSearchOutput> esBody;

}
