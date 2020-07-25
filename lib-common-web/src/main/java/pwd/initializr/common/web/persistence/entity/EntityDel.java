package pwd.initializr.common.web.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.common.web.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-21 22:37
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */

@NoArgsConstructor
@Getter
@ToString
public enum EntityDel {
    NO(0, "no", "未删除"), YES(1, "yes", "已删除");

    private int number;
    private String enus;
    private String zhcn;
    EntityDel(int number, String enus, String zhcn) {
        this.number = number;
        this.enus = enus;
        this.zhcn = zhcn;
    }
}
