package pwd.initializr.email.api.robot.vo;

 import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * project-generator-test@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2020-12-14 16:13
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "EmailExtendInput", description = "EmailExtend响应参数")
public class EmailExtendOutput implements Serializable {

  /**
   * 主键
   * 主键
   */
  @ApiModelProperty(name = "id", value = "主键", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   *
   * email表外键
   */
  @ApiModelProperty(name = "emailId", value = "email表外键", required = true, example = "")
   @NotBlank(message = "emailId不能为空")
  private Long emailId;
  /**
   *
   * 扩展内容的key值
   */
  @ApiModelProperty(name = "key", value = "扩展内容的key值", required = true, example = "")
   @NotBlank(message = "key不能为空")
  private String key;
  /**
   *
   * 扩展内容的value值
   */
  @ApiModelProperty(name = "value", value = "扩展内容的value值", required = true, example = "")
   @NotBlank(message = "value不能为空")
  private String value;
  /**
   *
   * 创建时间
   */
  @ApiModelProperty(name = "createTime", value = "创建时间", required = true, example = "")
   @NotBlank(message = "createTime不能为空")
  private Date createTime;
  /**
   *
   * 更新时间
   */
  @ApiModelProperty(name = "updateTime", value = "更新时间", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
}
