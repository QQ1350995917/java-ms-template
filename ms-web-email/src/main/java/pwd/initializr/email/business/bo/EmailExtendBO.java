package pwd.initializr.email.business.bo;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.email.persistence.entity.EmailExtendEntity;

/**
 * <h2>服务层逻辑对象封装：EmailExtendEntity信息</h2>
 * date 2020-12-14 15:55
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmailExtendBO extends EmailExtendEntity {

    public EmailExtendBO(String key, String value) {
        super(null, null, key, value, null, null);
    }
}
