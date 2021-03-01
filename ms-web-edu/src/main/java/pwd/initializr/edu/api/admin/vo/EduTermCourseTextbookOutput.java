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
 * date 2021-03-01 22:01
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
@ApiModel(value = "EduTermCourseTextbookInput", description = "EduTermCourseTextbook响应参数")
public class EduTermCourseTextbookOutput implements Serializable {

  /**
   * 主键
   * 
   */
  @ApiModelProperty(name = "id", value = "", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   * 
   * 外键，course表
   */
  @ApiModelProperty(name = "cid", value = "外键，course表", required = false, example = "")
  private Long cid;
  /**
   * 
   * 外键，term表
   */
  @ApiModelProperty(name = "tid", value = "外键，term表", required = false, example = "")
  private Long tid;
  /**
   * 
   * 教材名称
   */
  @ApiModelProperty(name = "name", value = "教材名称", required = false, example = "")
  private String name;
  /**
   * 
   * 出版社名称
   */
  @ApiModelProperty(name = "publisher", value = "出版社名称", required = false, example = "")
  private String publisher;
  /**
   * 
   * 教材版本
   */
  @ApiModelProperty(name = "version", value = "教材版本", required = false, example = "")
  private String version;
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
