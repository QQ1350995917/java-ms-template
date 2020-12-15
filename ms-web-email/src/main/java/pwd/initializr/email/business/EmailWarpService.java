package pwd.initializr.email.business;

import java.util.Set;
import org.springframework.stereotype.Service;
import pwd.initializr.email.business.bo.EmailAttachmentBO;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.business.bo.EmailBoxBO;
import pwd.initializr.email.business.bo.EmailContentBO;
import pwd.initializr.email.business.bo.EmailExtendBO;
import pwd.initializr.email.business.bo.EmailWrapBO;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-14 16:22
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface EmailWarpService {

    /**
     * <h2>创建Email数据记录</h2>
     * date 2020-12-15 14:00
     *
     * @param emailBO email主体记录对象
     * @param emailBoxBOS email收发件箱对象
     * @param emailContentBO email内容对象
     * @param emailAttachmentBOS email附件对象
     * @param emailExtendBOS email附加信息对象
     * @return pwd.initializr.email.business.bo.EmailBO
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    EmailBO createEmail(EmailBO emailBO, Set<EmailBoxBO> emailBoxBOS, EmailContentBO emailContentBO,
        Set<EmailAttachmentBO> emailAttachmentBOS, Set<EmailExtendBO> emailExtendBOS);

    /**
     * <h2>待发送邮件查询查询，同时修改其状态为发送中状态，使用该接口前最好清楚业务正在做什么</h2>
     * date 2020-12-15 14:01
     *
     * @param pageSize 查询量
     * @return java.util.Set<java.lang.Long>
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    Set<Long> queryWaitingForSendEmail(Long pageSize);

    /**
     * <h2>根据ID查询邮件的收发件箱，内容，附件</h2>
     * date 2020-12-15 14:54
     *
     * @param id 邮件ID
     * @return pwd.initializr.email.business.bo.EmailWrapBO
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    EmailWrapBO queryById(Long id);

}
