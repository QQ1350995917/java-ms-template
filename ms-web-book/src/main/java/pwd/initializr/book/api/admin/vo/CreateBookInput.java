package pwd.initializr.book.api.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import pwd.initializr.book.persistence.entity.AutoIncKey;

/**
 * pwd.initializr.book.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:17
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
public class CreateBookInput implements Serializable {

  @ApiModelProperty(name = "isbn", value = "International Standard Book Number", required = false, example = "9787229124410")
  @NotNull(message = "0")
  private String isbn;
  @ApiModelProperty(name = "title", value = "图书名称", required = true, example = "三体")
  @NotNull(message = "0")
  private String title;
  @ApiModelProperty(name = "subTitle", value = "图书副名称", required = false, example = "三体")
  @NotNull(message = "0")
  private String subTitle;
  @ApiModelProperty(name = "authorId", value = "作者ID", required = false, example = "pwd")
  @NotNull(message = "0")
  private String authorId;
  @ApiModelProperty(name = "authorName", value = "作者名称", required = false, example = "刘慈欣")
  @NotNull(message = "0")
  private String authorName;
  @ApiModelProperty(name = "summary", value = "图书简介", required = false, example = "邪乎到家必有鬼")
  @NotNull(message = "0")
  private String summary;
  @ApiModelProperty(name = "thumbs", value = "图片", required = false, example = "https://img11.360buyimg.com/n1/jfs/t7084/309/1723776720/329560/b5511e85/598d1fe1N95cad7bc.jpg")
  @NotNull(message = "0")
  private LinkedHashSet<String> thumbs;
  @ApiModelProperty(name = "labels", value = "标签", required = false, example = "科幻")
  @NotNull(message = "0")
  private LinkedHashSet<String> labels;
  @ApiModelProperty(name = "publisher", value = "出版社", required = false, example = "重庆出版社")
  @NotNull(message = "0")
  private String publisher;
  @ApiModelProperty(name = "publicationTime", value = "出版时间", required = false, example = "2017-07-01")
  @NotNull(message = "0")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private String publicationTime;
  @ApiModelProperty(name = "status", value = "状态", required = false, example = "0")
  @NotNull(message = "0")
  private Integer status;
}
