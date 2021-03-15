package pwd.initializr.search.business.bo;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.search.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 22:57
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MappingFieldBO {

  @NotNull
  private String key;
  @NotNull
  private Object value;

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof MappingFieldBO)) {
      return false;
    }
    MappingFieldBO bo = (MappingFieldBO) obj;
    String key = bo.getKey();
    if (!key.equals(this.key)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return this.key.hashCode();
  }
}
