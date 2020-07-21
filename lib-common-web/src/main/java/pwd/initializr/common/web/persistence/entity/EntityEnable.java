package pwd.initializr.common.web.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.common.web.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-21 22:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@ToString
public enum EntityEnable {
  DISABLE(0, "disable", "禁用"), ENABLE(1, "enable", "启用");

  private int number;
  private String enus;
  private String zhcn;
  EntityEnable(int number, String enus, String zhcn) {
    this.number = number;
    this.enus = enus;
    this.zhcn = zhcn;
  }

}
