package pwd.initializr.account.rpc;

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
public enum UserAccountType {
  ByAdmin(0, "管理员开通账户"),
  ByPhoneNumber(1, "手机号码注册账户") {
    @Override
    public boolean isAdmin() {
      return super.isAdmin();
    }
  },
  ByEmail(2, "邮箱注册账户");

  String desc;
  Integer type;

  UserAccountType(Integer type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

  public Integer getValue() {
    return type;
  }

  public boolean isAdmin() {
    return false;
  }
}
