package pwd.initializr.typeface.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

/**
 * TODO
 *
 * @author www.dingpengwei@foxmail.com on 2018-12-25 16:52.
 */
public class Main {

  static String fontPath = "/Users/pwd/Documents/minio/initializr-typeface/font/瘦金体繁体字帖.ttf";
  static String output = "/Users/pwd/Documents/temp/image/";

  public static void main(String[] args) throws Exception {
    testStudio();
    testBackground();
    testForeground();
  }


  public static void testStudio() throws Exception {
    String backgroundName = "xsque.png";
    String content = "字";
    BufferedImage bufferedImage = Painter.createImage(content, fontPath);
    OutputStream os = new FileOutputStream(new File(output + backgroundName));
    ImageIO.write(bufferedImage, "PNG", os);
  }

  public static void testBackground() throws Exception {
    String backgroundName = "background15x10.png";
    long start = System.currentTimeMillis();
    Background background = new Background(15, 10, 100, 5, "#bdbdbd");
    BufferedImage bufferedImage = background.draw();
    OutputStream os = new FileOutputStream(new File(output + backgroundName));
    ImageIO.write(bufferedImage, "PNG", os);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }

  public static void testForeground() throws Exception {
    String backgroundName = "xstudio.png";
    String[] contents = {"好雨知时节", "当春乃发生", "随风潜入夜", "润物细无声", "野径云俱黑", "江船火独明", "晓看红湿处", "花重锦官城"};

    long start = System.currentTimeMillis();
    BufferedImage bufferedImage = Painter.createImage(contents, fontPath);
    OutputStream os = new FileOutputStream(new File(output + backgroundName));
    ImageIO.write(bufferedImage, "PNG", os);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }

}
