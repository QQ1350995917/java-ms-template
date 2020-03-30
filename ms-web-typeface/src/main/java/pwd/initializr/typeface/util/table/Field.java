package pwd.initializr.typeface.util.table;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author www.dingpengwei@foxmail.com on 2018-12-03 12:03.
 */
public class Field extends AGraph {

  private int borderWidth = 1;
  private float[] cellBorderDash = {4, 2, 4, 2};
  private String color = "#3E9461";
  private int sideWidth;

  public Field(int sideWidth, String color) {
    this.sideWidth = sideWidth;
    this.color = color;
  }

  public int getBorderWidth() {
    return borderWidth;
  }  @Override
  public int getImageWidth() {
    return sideWidth;
  }

  public void setBorderWidth(int borderWidth) {
    this.borderWidth = borderWidth;
  }  @Override
  public int getImageHeight() {
    return sideWidth;
  }

  public float[] getCellBorderDash() {
    return cellBorderDash;
  }  @Override
  public List<IBorder> getBorders() {
    List<IBorder> fieldBorders = new ArrayList<>();
    int startX = 0;
    int startY = 0;
    int endX = this.getImageWidth();
    int endY = this.getImageHeight();

    IBorder cellBorderLeft = new Line(borderWidth, color, startX, startY,
        startX, endY);
    IBorder cellBorderTop = new Line(borderWidth, color, startX, startY, endX,
        startY);
    IBorder cellBorderRight = new Line(borderWidth, color, endX, startY, endX,
        endY);
    IBorder cellBorderBottom = new Line(borderWidth, color, startX, endY, endX,
        endY);
    fieldBorders.add(cellBorderLeft);
    fieldBorders.add(cellBorderTop);
    fieldBorders.add(cellBorderRight);
    fieldBorders.add(cellBorderBottom);

    IBorder cellBorderLeftTopToRightBottom = new Line(borderWidth, color, startX,
        startY, endX, endY, cellBorderDash);
    IBorder cellBorderRightTopToLeftBottom = new Line(borderWidth, color, endX,
        startY, startX, endY, cellBorderDash);
    fieldBorders.add(cellBorderLeftTopToRightBottom);
    fieldBorders.add(cellBorderRightTopToLeftBottom);

    int centerHStartX = startX + this.sideWidth / 2;
    int centerHStartY = startY;
    int centerHEndX = centerHStartX;
    int centerHEndY = centerHStartY + this.sideWidth;
    IBorder cellBorderCenterH = new Line(borderWidth, color, centerHStartX,
        centerHStartY, centerHEndX, centerHEndY, cellBorderDash);
    fieldBorders.add(cellBorderCenterH);

    int centerVStartX = startX;
    int centerVStartY = startY + this.sideWidth / 2;
    int centerVEndX = centerVStartX + this.sideWidth;
    int centerVEndY = centerVStartY;
    IBorder cellBorderCenterV = new Line(borderWidth, color, centerVStartX,
        centerVStartY, centerVEndX, centerVEndY, cellBorderDash);
    fieldBorders.add(cellBorderCenterV);

    return fieldBorders;
  }

  public void setCellBorderDash(float[] cellBorderDash) {
    this.cellBorderDash = cellBorderDash;
  }

  public int getSideWidth() {
    return sideWidth;
  }

  public void setSideWidth(int sideWidth) {
    this.sideWidth = sideWidth;
  }






}
