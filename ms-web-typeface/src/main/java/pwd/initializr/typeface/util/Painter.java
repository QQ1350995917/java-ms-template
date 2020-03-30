package pwd.initializr.typeface.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * TODO
 *
 * @author www.dingpengwei@foxmail.com on 2018-11-29 15:38.
 */
public class Painter {

  public static BufferedImage createImage(String content, String fontPath) {
    return createImage(Style.Square, content, fontPath);
  }

  public static BufferedImage createImage(Style style, String content, String fontPath) {
    if (content == null || content.trim().equals("")) {
      content = "请输入内容";
    }
    if (style == null) {
      style = Style.Square;
    }

    int columns = 3;
    int rows = 3;

    if (style == Style.OneLine) {
      columns = content.length();
      rows = 1;

    } else if (style == Style.Square) {
      double sqrtOfContentLength = Math.sqrt(content.length());
      columns = (int) Math.ceil(sqrtOfContentLength);
      rows = (int) Math.ceil((float) content.length() / columns);
    }
    String[] contents = new String[rows];
    for (int i = 0; i < rows; i++) {
      int start = i * columns;
      if (start + columns > content.length()) {
        contents[i] = content.substring(start, content.length());
      } else {
        contents[i] = content.substring(start, start + columns);
      }
    }
    return createImage(contents, fontPath);
  }

  public static BufferedImage createImage(String[] contents, String fontPath) {
    Background background = new Background(contents.length, contents[0].length(), 128, 5,
        "#3E9461");

    BufferedImage bufferedImage = new BufferedImage(background.getImageWidth(),
        background.getImageHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = bufferedImage.createGraphics();
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
    graphics.setColor(Color.decode("#00FFFFFF"));
    graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
    background.draw(graphics);
    Foreground foreground = new Foreground(fontPath, contents, 128, 5);
    foreground.draw(graphics);
    graphics.dispose();
    return bufferedImage;
  }

  public enum Style {
    OneLine, Square
  }

}
