package pwd.initializr.common.web.api.vo;

import com.alibaba.fastjson.JSON;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

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
@Slf4j
public class PageInput implements Serializable {

  @ApiModelProperty(name = "index", value = "当前页码", required = false, example = "0")
  @Min(value = 0)
  private Long index = 0L;
  @ApiModelProperty(name = "size", value = "当前页面容量", required = false, example = "12")
  @Min(value = 1)
  @Max(value = 500)
  private Long size = 12L;

  public static PageInput parse(String pageJson) {
    if (StringUtils.isEmpty(pageJson)) {
      return new PageInput();
    }

    try {
      PageInput pageInput = JSON.parseObject(pageJson, PageInput.class);
      return pageInput;
    } catch (Exception e) {
      log.error("格式化" + pageJson + "JSON" + "发生异常",e);
      return new PageInput();
    }
  }


}
