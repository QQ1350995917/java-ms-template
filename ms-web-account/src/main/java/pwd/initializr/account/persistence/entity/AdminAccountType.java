package pwd.initializr.account.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.account.persistence.entity@ms-web-initializr
 *
 * <h1>枚举类：账号类型</h1>
 *
 * date 2020-07-26 22:39
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@ToString
public enum AdminAccountType {
  GRANT(1, "grant", "授权账号"),
  PHONE_SMS(2, "phone number and SMS code", "电话号码和短信验证码"),
  PHONE_PWD(3, "phone number and password", "电话号码和密码");

  private int number;
  private String enus;
  private String zhcn;

  AdminAccountType(int number, String enus, String zhcn) {
    this.number = number;
    this.enus = enus;
    this.zhcn = zhcn;
  }
}
