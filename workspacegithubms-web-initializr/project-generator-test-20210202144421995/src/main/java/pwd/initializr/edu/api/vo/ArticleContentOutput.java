package pwd.initializr.edu.api.vo;

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
 * project-generator-test-20210202144421995@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-02 14:44
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
@ApiModel(value = "ArticleContentInput", description = "ArticleContent响应参数")
public class ArticleContentOutput implements Serializable {

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
  @ApiModelProperty(name = "name", value = "标签名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
  private String name;
  /**
   * 
   * 叶子节点，0：非叶子节点；1：叶子节点
   */
  @ApiModelProperty(name = "leaf", value = "叶子节点，0：非叶子节点；1：叶子节点", required = true, example = "")
   @NotBlank(message = "leaf不能为空")
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
