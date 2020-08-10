package pwd.initializr.common.web.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.common.web.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-08-10 17:47
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@ToString
public enum EntityScopeHit {
    EQUALS("EM", "Exact matching", "精确匹配"),
    EQUALS_NOT("ENM", "Exact not matching", "精确不匹配"),
    LIKE("AL", "Around like", "前后模糊匹配"),
    LEFT_LIKE("LL", "Left like", "前模糊匹配"),
    RIGHT_LIKE("RL", "Right like", "后模糊匹配"),
    SCOPE("SM", "Scope matching", "范围区间");

    private String hit;
    private String enus;
    private String zhcn;

    EntityScopeHit(String hit, String enus, String zhcn) {
        this.hit = hit;
        this.enus = enus;
        this.zhcn = zhcn;
    }


}
