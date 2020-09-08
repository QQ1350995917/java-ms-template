package pwd.initializr.automatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.account@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-13 22:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@ComponentScan(basePackages = {"pwd.initializr.common.web", "pwd.initializr.automatic"})
public class AutomaticApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticApplication.class, args);
    }
}
