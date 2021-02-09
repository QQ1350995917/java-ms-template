package pwd.initializr.account.rpc;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-11 21:28
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RPCSession implements Serializable {

  /**
   * 用户ID
   */
  @JSONField(serializeUsing = ToStringSerializer.class)
  private Long uid;

  /**
   * 用户名
   */
  private String uName;
  /**
   * 登录的账号ID
   */
  @JSONField(serializeUsing = ToStringSerializer.class)
  private Long aid;
  /**
   * 登录账号名
   */
  private String aName;
  /**
   * 生成的token
   */
  private String token;
  /**
   * 登录时间戳
   */
  @JSONField(serializeUsing = ToStringSerializer.class)
  private Long timestamp;

}
