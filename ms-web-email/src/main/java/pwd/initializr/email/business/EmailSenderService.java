package pwd.initializr.email.business;

import java.util.Set;

/**
 * pwd.initializr.email.business@ms-web-email
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-29 18:10
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface EmailSenderService {

    /**
     * <h2>查询缓冲队列是否有足够容量</h2>
     * date 2021-01-29 18:11
     *
     * @return boolean 缓冲队列是否有足够容量
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    boolean isQueueRemainingCapacityAble();

    /**
     * <h2>异步发送邮件</h2>
     * date 2021-01-29 18:12
     *
     * @return java.util.Set<java.lang.Long> 缓存中待发送的邮件ID
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    Set<Long> send();

}
