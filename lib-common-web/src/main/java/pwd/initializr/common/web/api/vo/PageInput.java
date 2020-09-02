package pwd.initializr.common.web.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.LinkedList;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.web.api.vo@ms-web-initializr
 *
 * <h1>API统一分页查询输入结构声明</h1>
 *
 * date 2020-03-29 20:46
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
public class PageInput implements Serializable {

  @ApiModelProperty(name = "index", value = "当前页码", required = false, example = "0")
  @Min(value = 0)
  private Long index = 0L;
  @ApiModelProperty(name = "size", value = "当前页面容量", required = false, example = "12")
  @Min(value = 1)
  @Max(value = 500)
  private Long size = 12L;



}
