package pwd.initializr.email.business;

import pwd.initializr.email.business.bo.EmailBO;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-05 16:30
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface EmailService {

    /**
     * <h2>发送邮件接口，同时保存发送记录</h2>
     * date 2020-12-05 16:32
     *
     * @return java.lang.Long 数据ID
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    Long sendEmail(EmailBO bo);

}
