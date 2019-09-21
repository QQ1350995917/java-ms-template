package pwd.initializr.account.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-20 23:49
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AccountEntity {

  private Long id;
  private Long userId;
  private String identify;
  private String password;
  private Integer type;
  private Integer status;
  private Long createTime;
  private Long updateTime;

  public enum Type {
    GRANT(0),
    PHONE(1),
    EMAIL(2);

    private int value;

    Type(int value) {
      this.value = value;
    }

    public int value(){
      return value;
    }
  }
}
