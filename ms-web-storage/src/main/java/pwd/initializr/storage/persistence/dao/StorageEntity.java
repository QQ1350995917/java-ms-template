package pwd.initializr.storage.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.description.field.FieldDescription.InGenericShape;

/**
 * pwd.initializr.storage.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 19:48
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class StorageEntity {
  private Long id;
  private Long userId;
  private String name;
  private String url;
  private String path;
  private Integer status;
  private Long createTime;
  private Long updateTime;
}
