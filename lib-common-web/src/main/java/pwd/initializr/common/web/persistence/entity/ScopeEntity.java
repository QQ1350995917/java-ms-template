package pwd.initializr.common.web.persistence.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.web.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-08-10 16:40
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ScopeEntity implements Serializable {

    // 指定查询方式[E:精确,AL:前后模糊,LL:左模糊,RL:右模糊,S:范围
    /**
     * @see EntityScopeHit
     */
    private String hit = "RL";
    private String fieldName;
    private String fieldValue;
    private String start;
    private String end;
}
