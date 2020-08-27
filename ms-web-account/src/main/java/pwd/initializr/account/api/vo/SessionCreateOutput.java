package pwd.initializr.account.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>VO数据模型：管理员登录成功响应参数</h1>
 *
 * date 2019-11-02 09:20
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "loginOutput", description = "管理员登录响应参数")
public class SessionCreateOutput<T> {

  /**
   * session 状态 0：匿名；1：具名
   */
  private Integer status = SessionStatus.ANONYMOUS.getNumber();

  private T session;

}
