package pwd.initializr.account.api.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-22 22:48
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
public class SessionInitOutput {

  /**
   * session 状态 0：匿名；1：具名
   */
  private Integer status = Status.ANONYMOUS.number;

  /**
   * token
   */
  private String token;
  /**
   * 过期时间（单位秒）
   */
  private Integer expires;

  /**
   * 过期时间（单位秒）
   */
  private Boolean captchaRequired;

  @Getter
  @ToString
  public enum Status {
      ANONYMOUS(0,"anonymous","Anonymous"),
      NAMED(1,"named","named");

      private int number;
      private String enus;
      private String zhcn;

      Status(int number, String enus, String zhcn) {
          this.number = number;
          this.enus = enus;
          this.zhcn = zhcn;
      }
  }


}