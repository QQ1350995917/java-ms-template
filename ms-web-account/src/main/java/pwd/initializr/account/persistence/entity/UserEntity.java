package pwd.initializr.account.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-20 23:24
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
@TableName(value = "user")
public class UserEntity {
  @TableId(value = "id",type = IdType.AUTO)
  private Long id;
  private String name;
  private String phoneNumber;
  private Integer status = 0;
  private Date createTime = new Date();
  private Date updateTime = new Date();

  public UserEntity(String name, String phoneNumber) {
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

}
