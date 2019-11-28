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
 * date 2019-09-24 16:40
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
public class Entrance {

  private Long id;
  private Integer key;
  private String name;
  private String logo;
  private Integer type;
  private Integer status;
  private Long createTime;
  private Long updateTime;
}
