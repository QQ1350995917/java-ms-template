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
 * pwd-initializr-organization-20210220185831978@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-20 18:58
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
   * 主键
   */
  @ApiModelProperty(name = "id", value = "主键", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
  /**
   * 
   * 上级组织
   */
  @ApiModelProperty(name = "pid", value = "上级组织", required = true, example = "0")
   @NotBlank(message = "pid不能为空")
  private Long pid;
  /**
   * 
   * 组织名称
   */
  @ApiModelProperty(name = "name", value = "组织名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
  private String name;
  /**
   * 
   * 组织logo
   */
  @ApiModelProperty(name = "logo", value = "组织logo", required = false, example = "")
  private String logo;
  /**
   * 
   * 组织描述
   */
  @ApiModelProperty(name = "description", value = "组织描述", required = false, example = "")
  private String description;
  /**
   * 
   * 组织slogan
   */
  @ApiModelProperty(name = "slogan", value = "组织slogan", required = false, example = "")
  private String slogan;
  /**
   * 
   * 组织等级
   */
  @ApiModelProperty(name = "level", value = "组织等级", required = true, example = "0")
   @NotBlank(message = "level不能为空")
  private Integer level;
  /**
   * 
   * 组织排序
   */
  @ApiModelProperty(name = "sort", value = "组织排序", required = true, example = "0")
   @NotBlank(message = "sort不能为空")
  private Integer sort;
  /**
   * 
   * 组织成员数量
   */
  @ApiModelProperty(name = "members", value = "组织成员数量", required = true, example = "1")
   @NotBlank(message = "members不能为空")
  private Integer members;
  /**
   * 
   * 组织审核进度
   */
  @ApiModelProperty(name = "progress", value = "组织审核进度", required = true, example = "0")
   @NotBlank(message = "progress不能为空")
  private Integer progress;
  /**
   * 
   * 状态，0禁用，1可用
   */
  @ApiModelProperty(name = "able", value = "状态，0禁用，1可用", required = true, example = "0")
   @NotBlank(message = "able不能为空")
  private Integer able;
  /**
   * 
   * 状态，0正常，1删除
   */
  @ApiModelProperty(name = "del", value = "状态，0正常，1删除", required = true, example = "0")
   @NotBlank(message = "del不能为空")
  private Integer del;
  /**
   * 
   * 首次创建时间
   */
  @ApiModelProperty(name = "createTime", value = "首次创建时间", required = true, example = "")
   @NotBlank(message = "createTime不能为空")
  private Date createTime;
  /**
   * 
   * 最近修改时间
   */
  @ApiModelProperty(name = "updateTime", value = "最近修改时间", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
}
