package pwd.initializr.common.vcode;

import java.awt.image.BufferedImage;
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
public class ArithmeticCode extends VCodeHelper {

  @Override
  protected String getOriginCode() {
    return null;
  }

  @Override
  public CodeMessage productMessage() {
    return productMessage(2);
  }

  @Override
  protected CodeMessage productMessage(Integer length) {
    Random random = new Random();
    int num1 = (int) (Math.random() * 10 + 1);
    int num2 = (int) (Math.random() * 10 + 1);
    int operatorType = random.nextInt(3);
    String operator = null;
    int result = 0;
    switch (operatorType) {
      case 0:
        operator = "+";
        result = num1 + num2;
        break;
      case 1:
        operator = "-";
        result = num1 - num2;
        break;
      case 2:
        operator = "*";
        result = num1 * num2;
        break;
    }
    //图片显示的算术文字
    String expression = num1 + "" + operator + "" + num2 + "= ?";
    return new CodeMessage(expression, String.valueOf(result));


  }

  @Override
  public BufferedImage productImage() {
    return productImage(productMessage().getPresented());
  }

  @Override
  public BufferedImage productImage(String codeMessage) {
    BufferedImage bufferedImage = draw(codeMessage);
    return bufferedImage;
  }


}
