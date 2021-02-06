package pwd.initializr.account.persistence.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.account.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-11 21:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@ToString
public enum AccountType {
  /**
   *
   */
  LoginNameAndLoginPwd(1, "普通登录名+登录密码；"),
  EmailAndLoginPwd(2,"邮箱登录名+登录密码；"),
  PhoneAndSMC(3, "电话登录名+短信验证码账号"),
  QQ(4, "QQ账号"),
  WeChat(5, "微信认证账号"),
  Weibo(6, "微博认证账号");

  @EnumValue
  Integer type;
  String desc;

  AccountType(Integer type, String desc) {
    this.type = type;
    this.desc = desc;
  }

}
