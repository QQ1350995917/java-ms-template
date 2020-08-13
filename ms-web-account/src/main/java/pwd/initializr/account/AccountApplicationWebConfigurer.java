package pwd.initializr.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pwd.initializr.account.api.user.unified.OwnershipInterceptor;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>全局添加配置信息</h1>
 *
 * date 2020-08-13 10:30
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
public class AccountApplicationWebConfigurer implements WebMvcConfigurer {

    @Autowired
    private OwnershipInterceptor ownershipInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ownershipInterceptor).addPathPatterns("/api/**");
    }
}
