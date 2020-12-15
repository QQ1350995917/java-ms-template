package pwd.initializr.email.business;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * pwd.initializr.email@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-15 17:09
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@AutoConfigureBefore(EmailScheduleSenderService.class)
public class EmailScheduleSenderServiceConfigure {

    @Value("${email.client.executor.queue.max.capacity}")
    private Integer queueMaxCapacity;
    @Value("${email.client.executor.pool.core.size}")
    private Integer corePoolSize;
    @Value("${email.client.executor.pool.max.size}")
    private Integer maxPoolSize;
    @Value("${email.client.executor.thread.keepalive.milliseconds}")
    private Long keepaliveMilliseconds;

    @Bean(name = "emailPostManThreadPoolQueue")
    public ArrayBlockingQueue<Runnable> getEmailPostManThreadPoolQueue() {
        return new ArrayBlockingQueue<>(this.queueMaxCapacity);
    }

    @Autowired
    @Bean(name = "emailPostManThreadPool")
    public ThreadPoolExecutor getEmailPostManThreadPool(
        @Qualifier(value = "emailPostManThreadPoolQueue") ArrayBlockingQueue<Runnable> queue) {
        return new ThreadPoolExecutor(this.queueMaxCapacity,
            this.maxPoolSize, this.keepaliveMilliseconds, TimeUnit.MILLISECONDS,
            queue, (runnable) -> {
            return new Thread(runnable, "email-postman");
        });
    }

}
