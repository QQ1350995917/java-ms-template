package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>请求参数：用户响应参数</h1>
 *
 * date 2020-08-08 20:28
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "adminUserQueryOutput", description = "管理员用户查询接口响应参数")
public class AdminUserQueryOutput extends AdminUserOutput {

}
