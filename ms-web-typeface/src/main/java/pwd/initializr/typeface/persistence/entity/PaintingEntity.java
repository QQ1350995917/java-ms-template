package pwd.initializr.typeface.persistence.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.typeface.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 15:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PaintingEntity implements Serializable {

  private Long id;
  private Long userId;
  private Long fontId;
  private Float fontSize;
  private String content;
  private String background;
  private String foreground;
  private Integer width;
  private Integer height;
  private String imageUrl;
  private Integer status;
  private Long createTime;
  private Long updateTime;

  public PaintingEntity(Long id, Long userId, Long fontId, Float fontSize, String content,
      String background, String foreground, Integer width, Integer height, String imageUrl,
      Integer status, Long createTime, Long updateTime) {
    this.id = id;
    this.userId = userId;
    this.fontId = fontId;
    this.fontSize = fontSize;
    this.content = content;
    this.background = background;
    this.foreground = foreground;
    this.width = width;
    this.height = height;
    this.imageUrl = imageUrl;
    this.status = status;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
