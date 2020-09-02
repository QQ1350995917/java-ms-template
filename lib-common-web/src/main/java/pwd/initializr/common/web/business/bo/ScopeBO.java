package pwd.initializr.common.web.business.bo;

import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;

/**
 * pwd.initializr.common.web.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-08-10 22:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ScopeBO extends ScopeEntity {

    @Override
    public int hashCode() {
        return this.getFieldName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ScopeInput)) {
            return false;
        }

        ScopeInput scopeInput = (ScopeInput) obj;
        if (scopeInput.getFieldName() == null) {
            return false;
        }
        if (!scopeInput.getFieldName().equals(this.getFieldName())) {
            return false;
        }

        return true;
    }
}
