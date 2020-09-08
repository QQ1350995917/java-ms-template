package pwd.initializr.common.captcha;

import java.util.Random;

/**
 * pwd.initializr.common.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 19:18
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CaptchaArithmetic extends CaptchaHelper {

  private String presented;
  private String expected;

  public CaptchaArithmetic() {
    Random random = new Random();
    int num1 = (int) (Math.random() * 10 + 1);
    int num2 = (int) (Math.random() * 10 + 1);
    int operatorType = random.nextInt(3);
    String operator;
    int expectedResult = 0;
    switch (operatorType) {
      case 0:
        operator = "+";
        expectedResult = num1 + num2;
        break;
      case 1:
        operator = "-";
        expectedResult = num1 - num2;
        break;
      case 2:
        operator = "*";
        expectedResult = num1 * num2;
        break;
      default:
        num1 = 0;
        num2 = 0;
        operator = "+";
        expectedResult = 0;
    }
    //图片显示的算术文字
    this.presented = num1 + "" + operator + "" + num2 + "= ?";
    this.expected = String.valueOf(expectedResult);
  }

  @Override
  protected String getPresented() {
    return this.presented;
  }

  @Override
  protected String getExpected() {
    return this.expected;
  }
}
