package pwd.initializr.typeface.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("font")
public class FontEntity extends Model<FontEntity> {

  private Long id;
  private String title;
  private String name;
  private String fileUrl;
  private String thumbUrl;
  private Integer status;
  private Long createTime;
  private Long updateTime;

  public FontEntity(Long id, String title, String name, String fileUrl, String thumbUrl, Integer status,
      Long createTime, Long updateTime) {
    this.id = id;
    this.title = title;
    this.name = name;
    this.fileUrl = fileUrl;
    this.thumbUrl = thumbUrl;
    this.status = status;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
