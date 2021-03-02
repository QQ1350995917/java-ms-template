package pwd.initializr.edu.business.bo;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.edu.persistence.entity.EduTermCourseEntity;

/**
 * <h2>服务层逻辑对象封装：EduTermCourseEntity信息</h2>
 * date 2021-03-01 22:01
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EduTermCourseBO extends EduTermCourseEntity {

    public EduTermCourseBO(Long id, Long tid, String name, Integer order) {
        super(id, tid, name, order, null,null,null,null);
    }
}
