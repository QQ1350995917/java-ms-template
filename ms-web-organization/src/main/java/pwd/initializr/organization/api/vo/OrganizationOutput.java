package pwd.initializr.organization.api.vo;

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
 * pwd-initializr-organization@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-20 22:30
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
@ApiModel(value = "OrganizationInput", description = "Organization响应参数")
public class OrganizationOutput implements Serializable {

  /**
   * 主键
   * ÷˜º¸
   */
  @ApiModelProperty(name = "id", value = "÷˜º¸", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   * 
   * …œº∂◊È÷Ø
   */
  @ApiModelProperty(name = "pid", value = "…œº∂◊È÷Ø", required = true, example = "0")
   @NotBlank(message = "pid不能为空")
  private Long pid;
  /**
   * 
   * ◊È÷Ø√˚≥∆
   */
  @ApiModelProperty(name = "name", value = "◊È÷Ø√˚≥∆", required = true, example = "")
   @NotBlank(message = "name不能为空")
  private String name;
  /**
   * 
   * ◊È÷Ølogo
   */
  @ApiModelProperty(name = "logo", value = "◊È÷Ølogo", required = false, example = "")
  private String logo;
  /**
   * 
   * ◊È÷Ø√Ë ˆ
   */
  @ApiModelProperty(name = "description", value = "◊È÷Ø√Ë ˆ", required = false, example = "")
  private String description;
  /**
   * 
   * ◊È÷Øslogan
   */
  @ApiModelProperty(name = "slogan", value = "◊È÷Øslogan", required = false, example = "")
  private String slogan;
  /**
   * 
   * ◊È÷Øµ»º∂
   */
  @ApiModelProperty(name = "level", value = "◊È÷Øµ»º∂", required = true, example = "0")
   @NotBlank(message = "level不能为空")
  private Integer level;
  /**
   * 
   * ◊È÷Ø≈≈–Ú
   */
  @ApiModelProperty(name = "sort", value = "◊È÷Ø≈≈–Ú", required = true, example = "0")
   @NotBlank(message = "sort不能为空")
  private Integer sort;
  /**
   * 
   * ◊È÷Ø≥…‘± ˝¡ø
   */
  @ApiModelProperty(name = "members", value = "◊È÷Ø≥…‘± ˝¡ø", required = true, example = "1")
   @NotBlank(message = "members不能为空")
  private Integer members;
  /**
   * 
   * ◊È÷Ø…Û∫ÀΩ¯∂»
   */
  @ApiModelProperty(name = "progress", value = "◊È÷Ø…Û∫ÀΩ¯∂»", required = true, example = "0")
   @NotBlank(message = "progress不能为空")
  private Integer progress;
  /**
   * 
   * ◊¥Ã¨£¨0Ω˚”√£¨1ø…”√
   */
  @ApiModelProperty(name = "able", value = "◊¥Ã¨£¨0Ω˚”√£¨1ø…”√", required = true, example = "0")
   @NotBlank(message = "able不能为空")
  private Integer able;
  /**
   * 
   * ◊¥Ã¨£¨0’˝≥££¨1…æ≥˝
   */
  @ApiModelProperty(name = "del", value = "◊¥Ã¨£¨0’˝≥££¨1…æ≥˝", required = true, example = "0")
   @NotBlank(message = "del不能为空")
  private Integer del;
  /**
   * 
   *  ◊¥Œ¥¥Ω® ±º‰
   */
  @ApiModelProperty(name = "createTime", value = " ◊¥Œ¥¥Ω® ±º‰", required = true, example = "")
   @NotBlank(message = "createTime不能为空")
  private Date createTime;
  /**
   * 
   * ◊ÓΩ¸–ﬁ∏ƒ ±º‰
   */
  @ApiModelProperty(name = "updateTime", value = "◊ÓΩ¸–ﬁ∏ƒ ±º‰", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
}
