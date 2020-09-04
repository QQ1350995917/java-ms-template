package pwd.initializr.account.api.admin.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-28 15:46
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
public class UserAccountOutput {
    private Long id;
    private Long uid;
    private String loginName;
    private Date pwdTime;
    private Integer type;
    private Integer able;
    private Integer del;
    private Date createTime;
    private Date updateTime;
}
