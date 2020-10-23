package pwd.initializr.common.mw.monitor;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * pwd.initializr.common.mw.montor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-20 09:39
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Singleton
@Slf4j
public abstract class MonitorClient {

    private final ScheduledExecutorService scheduler;
    private final ThreadPoolExecutor uploadExecutor;

    @Inject
    public MonitorClient() {
        try {
            // default size of 2 - 1 each for heartbeat and cacheRefresh
            scheduler = Executors.newScheduledThreadPool(1,
                new ThreadFactoryBuilder()
                    .setNameFormat("MonitorClient-%d")
                    .setDaemon(true)
                    .build());

            uploadExecutor = new ThreadPoolExecutor(
                1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactoryBuilder()
                    .setNameFormat("MonitorClient-UploadExecutor-%d")
                    .setDaemon(true)
                    .build()
            );

            initScheduledTasks();
            log.info("Monitor Client initialized at timestamp {}", System.currentTimeMillis());
        } catch (Throwable e) {
            throw new RuntimeException("Failed to initialize MonitorClient!", e);
        }
    }

    private void initScheduledTasks() {
        scheduler.schedule(
            new TimedSupervisorTask(
                getScheduleName(),
                scheduler,
                uploadExecutor, getScheduleSecondRate(),
                TimeUnit.SECONDS,
                1,
                new UploadThread()
            ),
            getScheduleSecondRate(), TimeUnit.SECONDS);
    }

    @VisibleForTesting
    protected abstract void refresh();

    protected abstract String getScheduleName();

    protected abstract int getScheduleSecondRate();

    class UploadThread implements Runnable {

        @Override
        public void run() {
            refresh();
        }
    }

}
