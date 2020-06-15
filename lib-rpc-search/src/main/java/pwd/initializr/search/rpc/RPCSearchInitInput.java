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

    @ApiModelProperty(name = "esHead", value = "esHead", required = false, example = "esHead", dataType = "java.lang.Object")
    private RPCSearchHeadVO esHead;

    @ApiModelProperty(name = "esBody", value = "esBody", required = false, example = "esBody", dataType = "java.util.List")
    private List<RPCSearchBodyVO> esBody;

}
