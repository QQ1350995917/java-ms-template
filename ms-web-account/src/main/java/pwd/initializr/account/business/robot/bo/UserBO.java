package pwd.initializr.account.business.robot.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.business.robot.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 15:37
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
  private String pin;
  private String name;
  private String gender;
  private String empNo;
  private String summary;
  private Integer able;
  private Integer del;
  private Long createTime;
  private Long updateTime;
}
