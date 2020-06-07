package pwd.initializr.book.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * pwd.initializr.book.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 12:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = -8592352525736469396L;

  @Id
  @AutoIncKey
  @Field("id")
  private Long id;
  @Field("title")
  private String title;
  @Field("sub_title")
  private String subTitle;
  @Field("author_id")
  private String authorId;
  @Field("author_name")
  private String authorName;
  @Field("summary")
  private String summary;
  @Field("labels")
  private LinkedHashSet<String> labels;
  @Field("publication_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date publicationTime;
  /**
   * 是否已经删除 1：没删除 -1：删除
   */
  @Field("del_status")
  private Integer delStatus;
  /**
   * 是否可见 -1：不可见 1：可见
   */
  @Field("visibility")
  private Integer visibility;
  /**
   * 是否被推荐到首页 -1：不推荐 1：推荐
   */
  @Field("recommend")
  private Integer recommend;
  /**
   * 创建时间
   */
  @Field("create_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;
  @Field("update_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime = new Date();

}
