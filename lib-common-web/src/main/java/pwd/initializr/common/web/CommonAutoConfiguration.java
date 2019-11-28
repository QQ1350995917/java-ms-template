package pwd.initializr.common.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pwd.initializr.common.web.business.SMSCodeServiceImpl;

/**
 * pwd.initializr.common.web@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-23 18:19
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@ConditionalOnWebApplication
@Import(SMSCodeServiceImpl.class)
public class CommonAutoConfiguration {

}
