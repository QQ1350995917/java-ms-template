package pwd.initializr.typeface.business.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.typeface.persistence.entity.FontEntity;

/**
 * pwd.initializr.typeface.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 15:22
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FontBO extends FontEntity {

  public FontBO(Long id, String title, String name, String fileUrl, String thumbUrl,
      Integer status, Long createTime, Long updateTime) {
    super(id, title, name, fileUrl, thumbUrl, status, createTime, updateTime);
  }
}
