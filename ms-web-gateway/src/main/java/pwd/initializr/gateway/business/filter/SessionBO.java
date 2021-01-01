package pwd.initializr.gateway.business.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.gateway.persistence.entity.SessionEntity;

/**
 * pwd.initializr.gateway.business.filter@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-30 23:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SessionBO extends SessionEntity {

    public SessionBO(Long id, Integer version, Integer weight, String method, String expression,
        String createTime) {
        super(id, version, weight, method, expression, createTime);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
