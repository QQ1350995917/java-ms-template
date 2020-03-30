package pwd.initializr.typeface.util.table;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author www.dingpengwei@foxmail.com on 2018-12-03 09:36.
 */
public class Frame extends AGraph {

  private String color = "#3E9461";
  private int columnWidth;
  private int columns;
  private int edgeBorderWidth = 5;
  private int insideBorderWidth = 3;
  private int insideMargin = 8;
  private int rowHeight;
  private int rows = 1;


  public Frame(int row, int column, int rowHeight, int columnWidth, int insideMargin,
      String color) {
    this(column, rowHeight, columnWidth, insideMargin, color);
    this.rows = row;
  }

  public Frame(int column, int rowHeight, int columnWidth, int insideMargin, String color) {
    this.columns = column;
    this.rowHeight = rowHeight;
    this.columnWidth = columnWidth;
    this.insideMargin = insideMargin;
    this.color = color;
  }

  public String getColor() {
    return color;
  }  @Override
  public List<IBorder> getBorders() {
    List<IBorder> borders = new ArrayList<>();
    borders.addAll(this.getEdgeBorders());
    borders.addAll(this.getInsideBorders());
    return borders;
  }

  public void setColor(String color) {
    this.color = color;
  }  @Override
  public int getImageWidth() {
    return columns * (columnWidth + insideMargin * 2);
  }

  public int getColumnWidth() {
    return columnWidth;
  }  @Override
  public int getImageHeight() {
    return rows * (rowHeight + insideMargin * 2);
  }

  public void setColumnWidth(int columnWidth) {
    this.columnWidth = columnWidth;
  }  public List<IBorder> getEdgeBorders() {
    int startY = 0;
    int startX = 0;
    int endX = this.getImageWidth();
    int endY = this.getImageHeight();

    List<IBorder> edgeBorders = new ArrayList<>();

    // 左边框
    IBorder frameBorderLeft = new Line(this.edgeBorderWidth, this.color, startX, startY, startX,
        endY);
    // 上边框
    IBorder frameBorderTop = new Line(this.edgeBorderWidth, this.color, startX, startY, endX,
        startY);
    // 右边框
    IBorder frameBorderRight = new Line(this.edgeBorderWidth, this.color, endX, startY, endX,
        endY);
    // 下边框
    IBorder frameBorderBottom = new Line(this.edgeBorderWidth, this.color, startX, endY, endX,
        endY);
    edgeBorders.add(frameBorderLeft);
    edgeBorders.add(frameBorderTop);
    edgeBorders.add(frameBorderRight);
    edgeBorders.add(frameBorderBottom);
    return edgeBorders;
  }

  public int getColumns() {
    return columns;
  }  public List<IBorder> getInsideBorders() {
    List<IBorder> insideBorders = new ArrayList<>();

    for (int i = 1; i < rows; i++) {
      int startX = 0;
      int startY = i * rowHeight + i * insideMargin * 2;
      int endX = this.getImageWidth();
      int endY = startY;
      IBorder innerBorder = new Line(insideBorderWidth, color, startX, startY, endX, endY);
      insideBorders.add(innerBorder);
    }

    for (int i = 1; i < columns; i++) {
      int startX = i * columnWidth + i * insideMargin * 2;
      int startY = 0;
      int endX = startX;
      int endY = this.getImageHeight();
      IBorder innerBorder = new Line(insideBorderWidth, color, startX, startY, endX, endY);
      insideBorders.add(innerBorder);
    }

    return insideBorders;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }

  public int getEdgeBorderWidth() {
    return edgeBorderWidth;
  }

  public void setEdgeBorderWidth(int edgeBorderWidth) {
    this.edgeBorderWidth = edgeBorderWidth;
  }

  public int getInsideBorderWidth() {
    return insideBorderWidth;
  }

  public void setInsideBorderWidth(int insideBorderWidth) {
    this.insideBorderWidth = insideBorderWidth;
  }

  public int getRowHeight() {
    return rowHeight;
  }

  public void setRowHeight(int rowHeight) {
    this.rowHeight = rowHeight;
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }










}
