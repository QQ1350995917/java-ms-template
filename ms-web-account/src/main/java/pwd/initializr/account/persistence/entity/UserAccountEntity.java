package pwd.initializr.account.persistence.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
 * pwd.initializr.account.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-20 23:49
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
@TableName(value = "user_account")
public class UserAccountEntity {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  private Long userId;
  private String loginName;
  private String password;
  @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
  private UserAccountType type;
  private Integer status = 0;
  private Date createTime = new Date();
  private Date updateTime = new Date();

  public UserAccountEntity(Long userId, String loginName, String password,
      UserAccountType type) {
    this.userId = userId;
    this.loginName = loginName;
    this.password = password;
    this.type = type;
  }
}
