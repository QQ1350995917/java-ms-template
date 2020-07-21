package pwd.initializr.account.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * (AdminAccountEntity)实体类
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AdminAccountEntity implements Serializable {

  private static final long serialVersionUID = 864136679056019403L;
  /**
   * 自增主键
   */
  private Long id;
  /**
   * 逻辑外键:admin_user.id
   */
  private Long uid;

  private String loginName;

  private String loginPwd;

  private Date pwdTime;
  /**
   * 1:授权账号；2：电话号码+短信验证码账号；3：电话号码+密码账号；4：邮箱账号+直接登录链接账号；5：邮箱账号+验证码账号；6：邮箱账号+密码账号；7：微信认证账号；8：微博认证账号；9：QQ账号；
   */
  private Integer type;
  /**
   * 可用状态：0:不可用；1:可用
   */
  private Integer enable;
  /**
   * 删除状态：0:未删除；1:已删除
   */
  private Integer del;
  /**
   * 数据创建时间
   */
  private Date createTime;
  /**
   * 最近更新时间
   */
  private Date updateTime;

}