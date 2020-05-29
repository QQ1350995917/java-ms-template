package pwd.initializr.account.business.user.bo;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.business.user.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 14:04
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
public class UserBO {

  private Long id;
  private String name;
  private String phoneNumber;
  private Integer status = 0;
  private Date createTime = new Date();
  private Date updateTime = new Date();

  private List<UserAccountBO> accounts;

  public UserBO(String name, String phoneNumber) {
    this.name = name;
    this.phoneNumber = phoneNumber;
  }


}
