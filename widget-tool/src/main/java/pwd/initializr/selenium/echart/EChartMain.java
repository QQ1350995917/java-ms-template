package pwd.initializr.selenium.echart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sun.misc.BASE64Decoder;

/**
 * main.java.pwd.initializr.selenium.echart@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 15:56
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class EChartMain {

  public static void main(String[] args) throws Exception {
    System.setProperty("webdriver.chrome.driver",
        "C:\\Users\\Administrator\\Downloads\\chromedriver_win32\\chromedriver.exe");
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("-headless");
    WebDriver webDriver = new ChromeDriver(chromeOptions);
    webDriver
        .get("E:\\workspace\\github\\ms-web-initializr\\widget-tool\\src\\main\\resources\\1.html");
    String abc = webDriver.findElement(By.id("imageData")).getAttribute("value");
    System.out.println(abc);
    File file = new File(
        "E:\\workspace\\github\\ms-web-initializr\\widget-tool\\src\\main\\resources\\1.png");
//    file.deleteOnExit();
    generateImage(abc.split(",")[1], file.getAbsolutePath());
  }

  public static void generateImage(String base64, String path) throws IOException {
    BASE64Decoder decoder = new BASE64Decoder();
    try (OutputStream out = new FileOutputStream(path)) {
      // 解密
      byte[] b = decoder.decodeBuffer(base64);
      for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {
          b[i] += 256;
        }
      }
      out.write(b);
      out.flush();
    }
  }
}
