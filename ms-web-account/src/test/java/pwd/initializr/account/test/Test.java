package pwd.initializr.account.test;

import static pwd.initializr.account.api.vo.SessionCreateFailOutput.FailType.CaptchaISError;

import com.alibaba.fastjson.JSON;
import pwd.initializr.account.api.vo.SessionCreateFailOutput;

/**
 * pwd.initializr.account.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-25 15:30
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Test {

  public static void main(String[] args) {
    String jsonString = JSON.toJSONString(new SessionCreateFailOutput(CaptchaISError));
    System.out.println(jsonString);
  }
}
