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
 import pwd.initializr.account.rpc.RPCUser;

/**
 * pwd-initializr-organization@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-22 21:33
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
   * 外键，组织ID
   */
  @ApiModelProperty(name = "orgId", value = "外键，组织ID", required = true, example = "")
  @NotBlank(message = "orgId不能为空")
  private Long orgId;
  /**
   * 主键
   * 外键，成员ID
   */
  @ApiModelProperty(name = "memId", value = "外键，成员ID", required = true, example = "")
  @NotBlank(message = "memId不能为空")
  private Long memId;
  /**
   * 组织名称
   */
  @ApiModelProperty(name = "orgName", value = "组织名称", required = true, example = "")
  @NotBlank(message = "orgName不能为空")
  private String orgName;
  /**
   * 
   * 排序
   */
  @ApiModelProperty(name = "sort", value = "排序", required = true, example = "0")
  @NotBlank(message = "sort不能为空")
  private Integer sort;
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

  @ApiModelProperty(name = "user", value = "成员详情", required = true, example = "")
  @NotBlank(message = "member不能为空")
  private RPCUser member;
}
