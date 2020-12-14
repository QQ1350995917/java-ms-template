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
public enum EmailSendType {
    /**
     * 0：待发送；
     */
    WAITING(0),
    /**
     * 1：发送中；
     */
    SENDING(1),
    /**
     * 2：发送成功；
     */
    SENT(2),
    /**
     * 3：发送失败
     */
    FAILURE(3);

    private int type;

    EmailSendType(int type) {
        this.type = type;
    }}
