package pwd.initializr.account;

import com.google.code.kaptcha.Constants;
import io.swagger.annotations.ApiOperation;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.vcode.ArithmeticCode;
import pwd.initializr.common.vcode.CodeMessage;
import pwd.initializr.common.vcode.VCodeHelper;

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
@ComponentScan(nameGenerator = FullPathNameGenerator.class)
@MapperScan("pwd.initializr.account.persistence.mapper")
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
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

    private VCodeHelper vCodeHelper = new ArithmeticCode();

    @ApiOperation(value = "获取一个验证码")
    @GetMapping(value = {"/vcode"}, produces = "application/json;charset=UTF-8")
    public void applyVCode(HttpServletRequest request, HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        CodeMessage codeMessage = vCodeHelper.productMessage();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, codeMessage.getExpected());
        BufferedImage bufferedImage = vCodeHelper.productImage(codeMessage.getPresented());
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", out);
            out.flush();
        } catch (IOException e) {
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
