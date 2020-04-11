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
  //管理员开通账户
  ByAdmin(0),
  //手机号码账户
  ByPhoneNumber(1) {
    @Override
    public boolean isAdmin() {
      return super.isAdmin();
    }
  },
  // 邮件账户
  ByEmail(2);

  Integer type;

  UserAccountType(Integer type) {
    this.type = type;
  }

  public Integer getValue() {
    return type;
  }

  public boolean isAdmin() {
    return false;
  }
}
