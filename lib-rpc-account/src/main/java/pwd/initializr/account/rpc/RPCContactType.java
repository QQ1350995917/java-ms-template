package pwd.initializr.account.rpc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.account.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-07-30 22:59
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@ToString
public enum RPCContactType {
  /**
   *
   */
  cellphone(1, "手机号码"),
  email(2, "邮箱");

  Integer type;
  String desc;

  RPCContactType(Integer type, String desc) {
    this.type = type;
    this.desc = desc;
  }

}
