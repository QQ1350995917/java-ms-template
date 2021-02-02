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
 * project-generator-test-20210202183035372@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-02 18:30
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
   * 数据ID
   */
  @ApiModelProperty(name = "id", value = "数据ID", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   * 
   * 课文内容
   */
  @ApiModelProperty(name = "text", value = "课文内容", required = true, example = "")
   @NotBlank(message = "text不能为空")
  private String text;
  /**
   * 
   * 线上地址
   */
  @ApiModelProperty(name = "url", value = "线上地址", required = true, example = "")
   @NotBlank(message = "url不能为空")
  private String url;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "remark", value = "", required = false, example = "")
  private String remark;
  /**
   * 
   * 存储路径
   */
  @ApiModelProperty(name = "path", value = "存储路径", required = true, example = "")
   @NotBlank(message = "path不能为空")
  private String path;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "able", value = "", required = true, example = "")
   @NotBlank(message = "able不能为空")
  private Integer able;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "del", value = "", required = true, example = "")
   @NotBlank(message = "del不能为空")
  private Integer del;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "createTime", value = "", required = true, example = "")
   @NotBlank(message = "createTime不能为空")
  private Date createTime;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "updateTime", value = "", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
}
