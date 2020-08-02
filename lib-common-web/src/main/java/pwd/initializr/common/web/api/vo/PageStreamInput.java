package pwd.initializr.common.web.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.web.api.vo@ms-web-initializr
 *
 * <h1>API统一流式分页查询输入结构声明</h1>
 *
 * date 2020-08-02 22:49
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PageStreamInput implements Serializable {
  @ApiModelProperty(name = "offsetId", value = "当前页码", required = false, example = "0")
  private Long offsetId = 0L;
  @ApiModelProperty(name = "size", value = "当前页面容量", required = false, example = "12")
  private Long size = 12L;

}
