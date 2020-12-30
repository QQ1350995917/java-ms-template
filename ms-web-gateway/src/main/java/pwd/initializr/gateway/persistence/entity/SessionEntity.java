package pwd.initializr.gateway.persistence.entity;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.gateway.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-30 22:53
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SessionEntity implements Comparable<SessionEntity> {

  private Long id;
  private Long version = 0L;
  private Integer weight;
  private String method;
  private String expression;
  private String createTime;

  @Override
  public int compareTo(SessionEntity entity) {
    return entity.getWeight() - this.getWeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, expression);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (!(obj instanceof SessionEntity)) {
      return false;
    }

    SessionEntity other = (SessionEntity) obj;
    return this.getMethod().equals(other.getMethod()) && this.getExpression()
        .equals(other.getExpression());
  }
}
