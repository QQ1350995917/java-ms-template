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
@ApiModel(value = "EmailInput", description = "Email响应参数")
public class EmailOutput implements Serializable {

  /**
   * 主键
   * 主键
   */
  @ApiModelProperty(name = "id", value = "主键", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   *
   * 发送邮件的服务名
   */
  @ApiModelProperty(name = "app", value = "发送邮件的服务名", required = true, example = "")
   @NotBlank(message = "app不能为空")
  private String app;
  /**
   *
   * 邮件发送状态，0：未发送；1：发送中；2：发送成功；3：发送失败
   */
  @ApiModelProperty(name = "sent", value = "邮件发送状态，0：未发送；1：发送中；2：发送成功；3：发送失败", required = true, example = "")
   @NotBlank(message = "sent不能为空")
  private Integer sent;
  /**
   *
   * 数据删除状态，0：未删除；1：已删除
   */
  @ApiModelProperty(name = "del", value = "数据删除状态，0：未删除；1：已删除", required = true, example = "")
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
   * 更新时间
   */
  @ApiModelProperty(name = "updateTime", value = "更新时间", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
}
