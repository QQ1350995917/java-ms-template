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

/**
 * pwd-initializr-edu@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-28 22:45
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
@ApiModel(value = "EduTermInput", description = "EduTerm响应参数")
public class EduTermOutput implements Serializable {

  /**
   * 主键
   * 主键 
   */
  @ApiModelProperty(name = "id", value = "主键 ", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   * 
   * 父节点ID
   */
  @ApiModelProperty(name = "pid", value = "父节点ID", required = false, example = "")
  private Long pid;
  /**
   * 
   * 中文名
   */
  @ApiModelProperty(name = "zhcnName", value = "中文名", required = true, example = "")
   @NotBlank(message = "zhcnName不能为空")
  private String zhcnName;
  /**
   * 
   * 英文名
   */
  @ApiModelProperty(name = "enusName", value = "英文名", required = true, example = "")
   @NotBlank(message = "enusName不能为空")
  private String enusName;
  /**
   * 
   * 学校的
   */
  @ApiModelProperty(name = "scholastic", value = "学校的", required = true, example = "")
   @NotBlank(message = "scholastic不能为空")
  private String scholastic;
  /**
   * 
   * 排序
   */
  @ApiModelProperty(name = "order", value = "排序", required = true, example = "0")
   @NotBlank(message = "order不能为空")
  private Integer order;
  /**
   * 
   * 叶子节点，0：否；1是
   */
  @ApiModelProperty(name = "leaf", value = "叶子节点，0：否；1是", required = false, example = "")
  private Integer leaf;
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
