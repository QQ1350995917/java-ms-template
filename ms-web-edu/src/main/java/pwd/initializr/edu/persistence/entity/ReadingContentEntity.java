package pwd.initializr.edu.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>reading_content数据表实体类</h2>
 * date 2021-02-18 12:50
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ReadingContentEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * ID
    */
  private Long id;
  /**
    *
    * 上级ID
    */
  private Long pid;
  /**
    *
    * 标签名称
    */
  private String readingName;
  /**
    *
    *
    */
  private String bpmStatus;
  /**
    *
    *
    */
  private String sysOrgCode;
  /**
    *
    *
    */
  private String sysCompanyCode;
  /**
    *
    *
    */
  private String sort;
  /**
    *
    *
    */
  private String zuixiao;
  /**
    *
    *
    */
  private String zuida;
  /**
    *
    *
    */
  private String qusCount;
  /**
    *
    *
    */
  private String score;
  /**
    *
    *
    */
  private String open;
  /**
    *
    *
    */
  private String content;

  /**
    *
    * 可用性状态，0：不可用；1：可用；
    */
  private Integer able;
  /**
    *
    * 删除状态，0：未删除，1：已删除；
    */
  private Integer del;
  /**
    *
    * 创建时间
    */
  private Date createTime;
  /**
    *
    * 修改时间
    */
  private Date updateTime;

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ReadingContentEntity)) {
      return false;
    }
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    // fixme: 视情况而定是否修改或删除hashCode
    return super.hashCode();
  }
}
