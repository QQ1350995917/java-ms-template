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
 * pwd-initializr-edu-20210218125038934@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-18 12:50
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
@ApiModel(value = "ReadingContentInput", description = "ReadingContent响应参数")
public class ReadingContentOutput implements Serializable {

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
  @ApiModelProperty(name = "readingName", value = "标签名称", required = true, example = "")
   @NotBlank(message = "readingName不能为空")
  private String readingName;
  /**
   *
   *
   */
  @ApiModelProperty(name = "bpmStatus", value = "", required = false, example = "")
  private String bpmStatus;
  /**
   *
   *
   */
  @ApiModelProperty(name = "sysOrgCode", value = "", required = false, example = "")
  private String sysOrgCode;
  /**
   *
   *
   */
  @ApiModelProperty(name = "sysCompanyCode", value = "", required = false, example = "")
  private String sysCompanyCode;
  /**
   *
   *
   */
  @ApiModelProperty(name = "sort", value = "", required = false, example = "")
  private String sort;
  /**
   *
   *
   */
  @ApiModelProperty(name = "zuixiao", value = "", required = false, example = "")
  private String zuixiao;
  /**
   *
   *
   */
  @ApiModelProperty(name = "zuida", value = "", required = false, example = "")
  private String zuida;
  /**
   *
   *
   */
  @ApiModelProperty(name = "qusCount", value = "", required = false, example = "")
  private String qusCount;
  /**
   *
   *
   */
  @ApiModelProperty(name = "score", value = "", required = false, example = "")
  private String score;
  /**
   *
   *
   */
  @ApiModelProperty(name = "open", value = "", required = false, example = "")
  private String open;
  /**
   *
   *
   */
  @ApiModelProperty(name = "content", value = "", required = false, example = "")
  private String content;
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
