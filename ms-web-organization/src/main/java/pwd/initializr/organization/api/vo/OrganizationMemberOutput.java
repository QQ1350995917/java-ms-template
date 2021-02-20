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
@ApiModel(value = "OrganizationMemberInput", description = "OrganizationMember响应参数")
public class OrganizationMemberOutput implements Serializable {

  /**
   * 主键
   * Õ‚º¸£¨◊È÷ØID
   */
  @ApiModelProperty(name = "orgId", value = "Õ‚º¸£¨◊È÷ØID", required = true, example = "")
   @NotBlank(message = "orgId不能为空")
  private Long orgId;
  /**
   * 主键
   * Õ‚º¸£¨≥…‘±ID
   */
  @ApiModelProperty(name = "memId", value = "Õ‚º¸£¨≥…‘±ID", required = true, example = "")
   @NotBlank(message = "memId不能为空")
  private Long memId;
  /**
   * 
   * ≈≈–Ú
   */
  @ApiModelProperty(name = "sort", value = "≈≈–Ú", required = true, example = "0")
   @NotBlank(message = "sort不能为空")
  private Integer sort;
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
