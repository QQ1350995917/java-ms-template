package pwd.initializr.common.web.api.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * pwd.initializr.common.web.api.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:29
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel
public class TestInput {

  @ApiModelProperty(value = "测试原生类型int", required = true)
  @NotNull(message = "anInt的值的范围是3~9")
  @Min(value = 3, message = "anInt的值不能小于最小值3")
  @Max(value = 9, message = "anInt的值不能大于最大值9")
  private int anInt;
  @ApiModelProperty(value = "测试包装类型Long", required = true)
  @NotNull(message = "aLong不能为空")
  private Long aLong;
  @ApiModelProperty(value = "测试引用类型String", required = true)
  @NotBlank(message = "string的值不能为空")
  @Pattern(regexp = "^(g)_(\\d){1,20}", message = "string的值必须使用g_开头的20字符以内的数字")
  private String string;
  @ApiModelProperty(value = "测试数组类型String", required = true)
  @NotNull(message = "strings数组不能为空")
  private List<String> strings;

  public int getAnInt() {
    return anInt;
  }

  public void setAnInt(int anInt) {
    this.anInt = anInt;
  }

  public Long getaLong() {
    return aLong;
  }

  public void setaLong(Long aLong) {
    this.aLong = aLong;
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public List<String> getStrings() {
    return strings;
  }

  public void setStrings(List<String> strings) {
    this.strings = strings;
  }
}