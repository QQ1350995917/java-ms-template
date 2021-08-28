package pwd.initializr.access.api.admin.vo;

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
 * pwd-initializr-access@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-08-08 15:20
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
@ApiModel(value = "FunctionInput", description = "Function响应参数")
public class FunctionOutput implements Serializable {

  /**
   * 主键
   * 主键
   */
  @ApiModelProperty(name = "id", value = "主键", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   * 
   * 权限名称
   */
  @ApiModelProperty(name = "name", value = "权限名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
  private String name;
  /**
   * 
   * 资源URI
   */
  @ApiModelProperty(name = "uri", value = "资源URI", required = true, example = "")
   @NotBlank(message = "uri不能为空")
  private String uri;
  /**
   * 
   * 请求方式
   */
  @ApiModelProperty(name = "method", value = "请求方式", required = true, example = "")
   @NotBlank(message = "method不能为空")
  private String method;
  /**
   * 
   * 描述
   */
  @ApiModelProperty(name = "summary", value = "描述", required = true, example = "")
   @NotBlank(message = "summary不能为空")
  private String summary;
  /**
   * 
   * 可用状态：0:不可用；1:可用
   */
  @ApiModelProperty(name = "able", value = "可用状态：0:不可用；1:可用", required = true, example = "0")
   @NotBlank(message = "able不能为空")
  private Integer able;
  /**
   * 
   * 删除状态：0:未删除；1:已删除
   */
  @ApiModelProperty(name = "del", value = "删除状态：0:未删除；1:已删除", required = true, example = "0")
   @NotBlank(message = "del不能为空")
  private Integer del;
  /**
   * 
   * 数据创建时间
   */
  @ApiModelProperty(name = "createTime", value = "数据创建时间", required = true, example = "")
   @NotBlank(message = "createTime不能为空")
  private Date createTime;
  /**
   * 
   * 最近更新时间
   */
  @ApiModelProperty(name = "updateTime", value = "最近更新时间", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
  /**
   * 
   * 数据版本号
   */
  @ApiModelProperty(name = "version", value = "数据版本号", required = true, example = "0")
   @NotBlank(message = "version不能为空")
  private Long version;
}
