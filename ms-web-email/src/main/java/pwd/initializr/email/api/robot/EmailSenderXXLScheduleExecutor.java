package pwd.initializr.email.api.robot;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import pwd.initializr.email.business.EmailSenderServiceImpl;

/**
 * pwd.initializr.email.api.robot@ms-web-email
 *
 * <h1>邮件服务依靠外部调度发送业务</h1>
 *
 * date 2021-01-29 17:48
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Slf4j
@Component
@Configuration
@ConditionalOnProperty(matchIfMissing = false,value = "xxl.job.enable")
public class EmailSenderXXLScheduleExecutor {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.address}")
    private String address;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Resource
    private EmailSenderServiceImpl emailSenderService;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

    /**
     * （Bean模式）
     */
    @XxlJob("EmailSenderJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        ReturnT<String> success = ReturnT.SUCCESS;
        if (emailSenderService.isQueueRemainingCapacityAble()) {
            Set<Long> send = emailSenderService.send();
            log.info("待发送排队中的邮件ID", JSON.toJSON(send));
            success.setContent(JSON.toJSONString(send));
        } else {
            log.warn("缓冲空间不足，应该检查网络负载或者减小发送频率");
            success.setContent("缓冲空间不足，应该检查网络负载或者减小发送频率");
        }
        return ReturnT.SUCCESS;
    }




}
