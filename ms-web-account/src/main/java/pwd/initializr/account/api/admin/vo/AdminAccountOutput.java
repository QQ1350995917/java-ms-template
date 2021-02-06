package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>请求参数：管理员账户参数</h1>
 *
 * date 2020-07-27 16:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "adminAccountOutput", description = "管理员账户响应参数")
public class AdminAccountOutput extends AdminAccountInput {

  private Long id;
  private Integer type;
  private Integer able;
  private Integer del;
  private Date createTime;
  private Date updateTime;
}
