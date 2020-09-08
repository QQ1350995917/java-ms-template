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
public class CaptchaRandom extends CaptchaHelper {
  private static final char[] randomChar = {
      'A',  'a',  '1',
      'B',  'b',  '2',
      'C',  'c',  '3',
      'D',  'd',  '4',
      'E',  'e',  '5',
      'F',  'f',  '6',
      'G',  'g',  '7',
      'H',  'h',  '8',
      'I',  'i',  '9',
      'J',  'j',  '0',
      'K',  'k',
      'L',  'l',
      'M',  'm',
      'N',  'n',
      'O',  'o',
      'P',  'p',
      'Q',  'q',
      'R',  'r',
      'S',  's',
      'T',  't',
      'U',  'u',
      'V',  'v',
      'W',  'w',
      'X',  'x',
      'Y',  'y',
      'Z',  'z',
  };

  private String presented;

  private String expected;

  public CaptchaRandom(int length) {
    if (length == 0) {
      length = 6;
    }

    if (length < 1) {
      length = 1;
    }

    if (length > 9) {
      length = 9;
    }

    StringBuilder presentedStringBuilder = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      char code = randomChar[random.nextInt(randomChar.length)];
      presentedStringBuilder.append(code);
    }
    this.presented = presentedStringBuilder.toString();
  }

  @Override
  protected String getPresented() {
    return this.presented;
  }

  @Override
  protected String getExpected() {
    return this.expected;
  }

  public static void main(String[] args) {

  }


}
