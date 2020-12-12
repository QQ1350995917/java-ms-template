package pwd.initializr.registry;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * pwd.initializr.registry@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-13 20:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@SpringBootApplication
@EnableEurekaServer
@Component
@Slf4j
public class RegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class, args);
    }

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event){
        log.warn(event.getServerId()+"\t"+event.getAppName()+"服务下线");
    }

    @EventListener
    public void listen( EurekaInstanceRegisteredEvent event){
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info(instanceInfo.getAppName()+"进行注册");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event){
        log.debug(event.getServerId()+"\t"+event.getAppName()+"服务进行续约");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event){
        log.info("注册中心启动");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event){
        log.info("Eureka Server启动");
    }
}
