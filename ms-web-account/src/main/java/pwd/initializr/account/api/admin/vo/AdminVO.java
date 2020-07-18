package pwd.initializr.account.api.admin.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-26 17:09
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminVO implements Serializable {

  private Long id;
  private String loginName;
  private String loginPassword;
  private String name;
  private Integer gender;
  private String summary;
  private Integer level;
  private Integer ableStatus;
  private Integer delStatus;
  private Date createTime;
  private Date updateTime;
}
