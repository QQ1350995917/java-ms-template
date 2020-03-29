package pwd.initializr.typeface.persistence.dao;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.typeface.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 15:00
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FontEntity implements Serializable {

  private Long createTime;
  private String fileUrl;
  private Long id;
  private String name;
  private Integer status;
  private String thumbUrl;
  private Long updateTime;

  public FontEntity(Long id, String name, String fileUrl, String thumbUrl, Integer status,
      Long createTime, Long updateTime) {
    this.id = id;
    this.name = name;
    this.fileUrl = fileUrl;
    this.thumbUrl = thumbUrl;
    this.status = status;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
