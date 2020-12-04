package pwd.initializr.email;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.captcha.CaptchaArithmetic;
import pwd.initializr.common.captcha.CaptchaHelper;

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
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableTransactionManagement
@ComponentScan(basePackages = {"pwd.initializr.common.web", "pwd.initializr.account"})
@MapperScan("pwd.initializr.account.persistence")
public class EmailApplication {

  private CaptchaHelper captchaHelper = new CaptchaArithmetic();

  public static void main(String[] args) {
    SpringApplication.run(EmailApplication.class, args);
  }

  @GetMapping(value = "")
  public String index0() {
    return "this is account 0 index";
  }

  @GetMapping(value = "/")
  public String index1() {
    return "this is account 1 index";
  }

  @GetMapping(value = "/account")
  public String index2() {
    return "this is account 2 index";
  }
}
