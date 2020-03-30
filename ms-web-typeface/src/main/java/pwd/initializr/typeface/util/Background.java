package pwd.initializr.typeface.util;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import pwd.initializr.typeface.util.table.AGraph;
import pwd.initializr.typeface.util.table.Field;
import pwd.initializr.typeface.util.table.Frame;
import pwd.initializr.typeface.util.table.IBorder;

/**
 * TODO
 *
 * @author www.dingpengwei@foxmail.com on 2018-11-30 11:26.
 */
public class Background extends AGraph {

  private int columns = 7;
  private Field field;
  private int fieldMargin = 6;
  private Frame frame;
  private int frameMargin = 10;
  private int rows = 1;
  private int sideWidth = 128;


  public Background(int rows, int columns, int sideWidth, int fieldMargin, String color) {
    this.rows = rows;
    this.columns = columns;
    this.sideWidth = sideWidth;
    this.fieldMargin = fieldMargin;
    this.frame = new Frame(rows, columns, sideWidth, sideWidth, fieldMargin, color);
    this.field = new Field(sideWidth, color);
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
  public List<IBorder> getBorders() {
    return new ArrayList<>();
  }

  @Override
  public void draw(Graphics2D graphics) {
    graphics.translate(frameMargin, frameMargin);
    frame.draw(graphics);
    for (int i = 0; i < rows; i++) {
      int translateY = i * (sideWidth + fieldMargin * 2) + fieldMargin;
      for (int j = 0; j < columns; j++) {
        int translateX = j * (sideWidth + fieldMargin * 2) + fieldMargin;
        graphics.translate(translateX, translateY);
        field.draw(graphics);
        graphics.translate(-translateX, -translateY);
      }
    }
  }
}
