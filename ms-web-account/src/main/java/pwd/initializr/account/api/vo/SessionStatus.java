package pwd.initializr.account.api.vo;

import lombok.Getter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-08-27 14:25
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Getter
@ToString
public enum  SessionStatus {
    ANONYMOUS(0,"anonymous","Anonymous"),
    NAMED(1,"named","named");

    private int number;
    private String enus;
    private String zhcn;

    SessionStatus(int number, String enus, String zhcn) {
        this.number = number;
        this.enus = enus;
        this.zhcn = zhcn;
    }
}
