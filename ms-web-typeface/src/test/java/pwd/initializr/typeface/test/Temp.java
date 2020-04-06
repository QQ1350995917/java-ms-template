package pwd.initializr.typeface.test;

import java.io.File;
import java.io.FileFilter;
import java.util.stream.Stream;

/**
 * pwd.initializr.typeface.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-06 19:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Temp {

  public static void main(String[] args) {
    String path = "/Users/pwd/Documents/minio/initializr-typeface/font";
    File[] files = new File(path).listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.getName().endsWith(".png")) {
          return false;
        } else if (pathname.getName().endsWith(".ttf")) {
          return true;
        } else {
          System.out.println(pathname.getName());
          return false;
        }
      }
    });
    Stream.of(files).forEach(file -> file.deleteOnExit());
  }

}
