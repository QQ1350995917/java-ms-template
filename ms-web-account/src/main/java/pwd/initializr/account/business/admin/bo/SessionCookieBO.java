package pwd.initializr.account.business.admin.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.business.admin.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-22 14:46
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
public class SessionCookieBO {

    /**
     * cookie码
     */
    private String cookie;

    /**
     * 已经尝试登录次数
     */
    private Integer times;

    /**
     * 期望验证码值
     */
    private String captcha;


    public SessionCookieBO(String cookie) {
        this.cookie = cookie;
    }


    public SessionCookieBO(String cookie,Integer times) {
        this.cookie = cookie;
        this.times = times;
    }


}
