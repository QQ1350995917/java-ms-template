package pwd.initializr.edu.business.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.edu.persistence.entity.WordTableEntity;

/**
 * <h2>服务层逻辑对象封装：WordTableEntity信息</h2>
 * date 2021-02-16 15:23
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class WordTableBO extends WordTableEntity {
  private String bookName;

}
