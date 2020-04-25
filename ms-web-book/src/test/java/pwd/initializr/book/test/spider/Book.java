package pwd.initializr.book.test.spider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * pwd.initializr.book.test.spider@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-21 11:42
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Book {

  public static void main(String[] args) {



    // 参数
    StringBuffer params = new StringBuffer();
    try {
      // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
      params.append("name=" + URLEncoder.encode("&", "utf-8"));
      params.append("&");
      params.append("age=24");
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }


  }
}
