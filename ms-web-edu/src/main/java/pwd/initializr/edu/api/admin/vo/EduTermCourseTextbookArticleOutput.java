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
 * pwd-initializr-app@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-03-08 17:38
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
@ApiModel(value = "EduTermCourseTextbookArticleInput", description = "EduTermCourseTextbookArticle响应参数")
public class EduTermCourseTextbookArticleOutput implements Serializable {

  /**
   * 主键
   * 数据ID
   */
  @ApiModelProperty(name = "id", value = "数据ID", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   *
   * 外键，edu_term_course_textbook.id，图书ID
   */
  @ApiModelProperty(name = "pid", value = "外键，edu_term_course_textbook.id，图书ID", required = false, example = "")
  private Long pid;
  /**
   *
   * 标题
   */
  @ApiModelProperty(name = "title", value = "标题", required = false, example = "")
  private String title;
  /**
   *
   * 课文内容
   */
  @ApiModelProperty(name = "text", value = "课文内容", required = true, example = "")
   @NotBlank(message = "text不能为空")
  private String text;
  /**
   *
   * 存储路径
   */
  @ApiModelProperty(name = "mediaPath", value = "存储路径", required = false, example = "")
  private String mediaPath;
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
