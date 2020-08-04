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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public enum AccountType {
  ByAdmin(0, "管理员开通账户"),
  ByNamePwd(1,"账号密码"),
  ByPhoneNumber(2, "手机号码注册账户") {
    @Override
    public boolean isAdmin() {
      return super.isAdmin();
    }
  },
  ByEmail(3, "邮箱注册账户");

  String desc;
  @EnumValue
  Integer type;

  AccountType(Integer type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  public boolean isAdmin() {
    return false;
  }

}
