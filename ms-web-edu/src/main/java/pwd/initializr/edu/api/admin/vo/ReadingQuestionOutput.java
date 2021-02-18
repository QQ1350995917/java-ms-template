package pwd.initializr.edu.api.admin.vo;

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
import org.hibernate.validator.constraints.Length;

/**
 * pwd-initializr-edu-20210218151557967@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-18 15:15
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
@ApiModel(value = "ReadingQuestionInput", description = "ReadingQuestion响应参数")
public class ReadingQuestionOutput implements Serializable {

  /**
   * 主键
   * ID
   */
  @ApiModelProperty(name = "id", value = "ID", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   *
   * 上级ID
   */
  @ApiModelProperty(name = "pid", value = "上级ID", required = true, example = "")
   @NotBlank(message = "pid不能为空")
  private Long pid;
  /**
   *
   * 标签名称
   */
  @ApiModelProperty(name = "title", value = "标签名称", required = true, example = "")
   @NotBlank(message = "title不能为空")
  private String title;
  /**
   *
   * 正确答案
   */
  @ApiModelProperty(name = "a", value = "正确答案", required = false, example = "")
  private String a;
  /**
   *
   * 备选答案
   */
  @ApiModelProperty(name = "a1", value = "备选答案", required = false, example = "")
  private String a1;
  /**
   *
   * 备选答案
   */
  @ApiModelProperty(name = "a2", value = "备选答案", required = false, example = "")
  private String a2;
  /**
   *
   * 备选答案
   */
  @ApiModelProperty(name = "a3", value = "备选答案", required = false, example = "")
  private String a3;
  /**
   *
   * 备选答案
   */
  @ApiModelProperty(name = "a4", value = "备选答案", required = false, example = "")
  private String a4;
  /**
   *
   * 备选答案
   */
  @ApiModelProperty(name = "a5", value = "备选答案", required = false, example = "")
  private String a5;
  /**
   *
   * 可用性状态，0：不可用；1：可用；
   */
  @ApiModelProperty(name = "able", value = "可用性状态，0：不可用；1：可用；", required = true, example = "")
   @NotBlank(message = "able不能为空")
  private Integer able;
  /**
   *
   * 删除状态，0：未删除，1：已删除；
   */
  @ApiModelProperty(name = "del", value = "删除状态，0：未删除，1：已删除；", required = true, example = "")
   @NotBlank(message = "del不能为空")
  private Integer del;
  /**
   *
   * 创建时间
   */
  @ApiModelProperty(name = "createTime", value = "创建时间", required = true, example = "")
   @NotBlank(message = "createTime不能为空")
  private Date createTime;
  /**
   *
   * 修改时间
   */
  @ApiModelProperty(name = "updateTime", value = "修改时间", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
}
