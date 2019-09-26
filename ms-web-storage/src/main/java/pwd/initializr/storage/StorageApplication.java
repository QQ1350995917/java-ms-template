package pwd.initializr.storage;

import javax.servlet.MultipartConfigElement;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * pwd.initializr.storage@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 16:39
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan("pwd.initializr.storage.persistence.mapper")
public class StorageApplication implements WebMvcConfigurer {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(StorageApplication.class, args);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/").setViewName("login");
//    registry.addViewController("/login.html").setViewName("login");
//    registry.addViewController("/websocket.html").setViewName("websocket");
//    registry.addViewController("/error.html").setViewName("error");
//    registry.addViewController("/file.html").setViewName("file");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    // 单个数据大小
    factory.setMaxFileSize(DataSize.ofKilobytes(1024 * 5));
    // 总上传数据大小
    factory.setMaxRequestSize(DataSize.ofKilobytes(1024 * 1024));
    return factory.createMultipartConfig();
  }

}
