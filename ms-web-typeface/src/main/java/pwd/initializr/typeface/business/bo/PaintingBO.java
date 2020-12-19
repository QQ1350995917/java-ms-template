package pwd.initializr.typeface.business.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.typeface.persistence.entity.PaintingEntity;

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
public class PaintingBO extends PaintingEntity {

  public PaintingBO(Long id, Long userId, Long fontId, Float fontSize, String content,
      String background, String foreground, Integer width, Integer height, String imageUrl,
      Integer status, Long createTime, Long updateTime) {
    super(id, userId, fontId, fontSize, content, background, foreground, width, height, imageUrl,
        status, createTime, updateTime);
  }
}
