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
 * date 2020-07-28 15:40
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
public class UserUserOutput {

    private Long id;
    private String pin;
    private String name;
    private String gender;
    private String empNo;
    private String summary;
    private Integer able;
    private Integer del;
    private Date createTime;
    private Date updateTime;
}
