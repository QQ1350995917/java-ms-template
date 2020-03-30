package pwd.initializr.typeface.util.table;

import java.awt.BasicStroke;

/**
 * TODO
 *
 * @author www.dingpengwei@foxmail.com on 2018-11-30 14:17.
 */
public class Line implements IBorder {

  protected int cap = BasicStroke.CAP_SQUARE;
  protected String color;
  protected float[] dash;
  protected int endX;
  protected int endY;
  protected int join = BasicStroke.JOIN_MITER;
  protected int startX;
  protected int startY;
  protected int width;


  public Line() {
    super();
  }

  public Line(int width, String color, int startX, int startY, int endX, int endY, float[] dash) {
    this(width, color, startX, startY, endX, endY);
    this.dash = dash;
  }

  public Line(int width, String color, int startX, int startY, int endX, int endY) {
    this.width = width;
    this.color = color;
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getCap() {
    return cap;
  }

  @Override
  public int getJoin() {
    return join;
  }

  @Override
  public float[] getDash() {
    return dash;
  }

  @Override
  public String getColor() {
    return color;
  }

  @Override
  public int getStartX() {
    return startX;
  }

  @Override
  public int getStartY() {
    return startY;
  }

  @Override
  public int getEndX() {
    return endX;
  }

  @Override
  public int getEndY() {
    return endY;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
  }
}
