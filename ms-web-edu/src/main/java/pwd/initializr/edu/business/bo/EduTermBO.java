package pwd.initializr.edu.business.bo;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.edu.persistence.entity.EduTermEntity;

/**
 * <h2>服务层逻辑对象封装：EduTermEntity信息</h2>
 * date 2021-02-28 22:45
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EduTermBO extends EduTermEntity {

  public EduTermBO(Long id, Long pid, String zhcnName, String enusName, Integer scholastic,
      Integer order, Integer leaf) {
    super(id, pid, zhcnName, enusName, scholastic, order, leaf, 0, 0, new Date(), new Date());
  }
}
