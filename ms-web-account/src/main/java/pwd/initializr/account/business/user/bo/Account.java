package pwd.initializr.account.business.user.bo;

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
 * date 2019-09-21 14:03
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
public class Account {

  private Long id;
  private Long userId;
  private String identify;
  private String password;
  private Integer type;
  private Integer status;
  private Long createTime;
  private Long updateTime;
}
