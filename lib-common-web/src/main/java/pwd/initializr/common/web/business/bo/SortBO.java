package pwd.initializr.common.web.business.bo;

import org.apache.commons.lang.StringUtils;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.persistence.entity.SortEntity;

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
public class SortBO extends SortEntity {

    @Override
    public int hashCode() {
        return this.getFieldName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SortInput)) {
            return false;
        }

        SortInput sortInput = (SortInput) obj;
        if (sortInput.getFieldName() == null) {
            return false;
        }
        if (!sortInput.getSort().equals(this.getFieldName())) {
            return false;
        }

        return true;
    }

}
