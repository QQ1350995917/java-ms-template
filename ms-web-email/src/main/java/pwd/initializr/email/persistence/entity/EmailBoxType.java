package pwd.initializr.email.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.email.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-14 14:52
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@ToString
public enum EmailBoxType {
    /**
     * from 发件邮箱
     */
    FROM(0),
    /**
     * to 收件邮箱
     */
    TO(1),
    /**
     * cc 抄送邮箱
     */
    CC(2),
    /**
     * bcc 密送邮箱
     */
    BCC(3);

    private int type;

    EmailBoxType(int type) {
        this.type = type;
    }}
