package pwd.initializr.account.business.session.bo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.account.business.session.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-07 14:00
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@ToString
public enum  SessionType {
    /**
     *
     */
    ANONYMOUS(0,"anonymous","Anonymous"),
    /**
     *
     */
    NAMED(1,"named","named");

    @EnumValue
    Integer type;
    String label;
    String desc;

    SessionType(Integer type, String label, String desc) {
        this.type = type;
        this.label = label;
        this.desc = desc;
    }
}
