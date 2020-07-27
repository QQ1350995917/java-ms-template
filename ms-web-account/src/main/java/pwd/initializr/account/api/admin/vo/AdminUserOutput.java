package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import javax.xml.crypto.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>请求参数：管理员用户参数</h1>
 *
 * date 2020-07-27 16:39
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
@ApiModel(value = "adminUserOutput", description = "管理员用户接口响应参数")
public class AdminUserOutput extends AdminUserInput {
    private Long id;
    private Integer able;
    private Data createTime;
    private Data updateTime;
}
