package pwd.initializr.account.business.user.bo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.account.persistence.entity.AccountType;

/**
 * pwd.initializr.account.business.user.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 15:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserAccountBO {


  private Date createTime = new Date();
  private Long id;
  private String loginName;
  private String password;
  private Integer status = 0;
  private AccountType type;
  private Date updateTime = new Date();
  private Long userId;

  public UserAccountBO(String loginName, String password, AccountType type) {
    this(null, loginName, password, type);
  }

  public UserAccountBO(Long userId, String loginName, String password, AccountType type) {
    this.userId = userId;
    this.loginName = loginName;
    this.password = password;
    this.type = type;
  }
}
