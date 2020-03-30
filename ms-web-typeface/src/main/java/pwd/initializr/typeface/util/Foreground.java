package pwd.initializr.typeface.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import pwd.initializr.typeface.util.table.AGraph;
import pwd.initializr.typeface.util.table.IBorder;
import sun.font.FontDesignMetrics;

/**
 * TODO
 *
 * @author www.dingpengwei@foxmail.com on 2018-12-04 13:52.
 */
public class Foreground extends AGraph {

  private int columns = 7;
  private String[] contents;
  private int fieldMargin = 5;
  private Font font;
  private int frameMargin = 10;
  private FontDesignMetrics metrics;
  private int rows = 1;
  private int sideWidth = 128;


  public Foreground(String fontPath, String[] contents, int sideWidth, int fieldMargin) {
    this.contents = contents;
    this.sideWidth = sideWidth;
    this.fieldMargin = fieldMargin;

    try {
      InputStream is = new FileInputStream(fontPath);
      font = Font.createFont(Font.TRUETYPE_FONT, is);
      is.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

    }

    font = font.deriveFont(Font.PLAIN).deriveFont(Float.valueOf(sideWidth));
    metrics = FontDesignMetrics.getMetrics(font);
  }

  @Override
  public int getImageWidth() {
    return columns * sideWidth + columns * fieldMargin * 2 + frameMargin * 2;
  }

  @Override
  public int getImageHeight() {
    return rows * sideWidth + rows * fieldMargin * 2 + frameMargin * 2;
  }

  @Override
  public void draw(Graphics2D graphics) {
    graphics.setFont(font);
    graphics.setColor(Color.BLACK);

    int stroke = 1;
    graphics.setStroke(new BasicStroke(stroke));
    for (int i = 0; i < contents.length; i++) {
      String content = contents[i];
      int translateY = i * (sideWidth + fieldMargin * 2) + fieldMargin;
      for (int j = 0; j < content.length(); j++) {
        int translateX = j * (sideWidth + fieldMargin * 2) + fieldMargin;
        graphics.translate(translateX, translateY);
        int ascent = metrics.getAscent();
        String substring = content.substring(j, j + 1);
        graphics.drawString(substring, 0, ascent);
        graphics.translate(-translateX, -translateY);
      }
    }
  }

  @Override
  public List<IBorder> getBorders() {
    return new ArrayList<>();
  }
}
