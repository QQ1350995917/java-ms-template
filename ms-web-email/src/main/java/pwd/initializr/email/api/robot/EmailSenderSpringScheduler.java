package pwd.initializr.email.api.robot;

import com.alibaba.fastjson.JSON;
import java.util.Set;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pwd.initializr.email.business.EmailSenderServiceImpl;

/**
 * pwd.initializr.email.api.robot@ms-web-email
 *
 * <h1>邮件服务自我调度发送业务</h1>
 *
 * date 2021-01-29 17:48
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Slf4j
@Component
@EnableScheduling
@ConditionalOnProperty(value = "email.client.auto.schedule.enable", matchIfMissing = false)
public class EmailSenderSpringScheduler {

    @Resource
    private EmailSenderServiceImpl emailSenderService;

    @Scheduled(cron = "${email.client.auto.schedule.cron}")
    public void schedule() {
        if (emailSenderService.isQueueRemainingCapacityAble()) {
            Set<Long> send = emailSenderService.send();
            if (send == null) {
              log.info("没有待发送的邮件");
            } else {
              log.info("待发送排队中的邮件ID:", JSON.toJSON(send));
            }
        } else {
            log.warn("缓冲空间不足，应该检查网络负载或者减小发送频率");
        }
    }
}
