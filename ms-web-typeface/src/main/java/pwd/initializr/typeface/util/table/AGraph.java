package pwd.initializr.typeface.util.table;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * TODO
 *
 * @author www.dingpengwei@foxmail.com on 2018-11-30 18:32.
 */
public abstract class AGraph {

  public BufferedImage draw() {
    return this.draw(BufferedImage.TYPE_INT_ARGB);
  }

  public BufferedImage draw(int imageType) {
    BufferedImage bufferedImage = new BufferedImage(getImageWidth(), getImageHeight(), imageType);
    return this.draw(bufferedImage);
  }

  public abstract int getImageWidth();

  public abstract int getImageHeight();

  public BufferedImage draw(BufferedImage bufferedImage) {
    return this.draw(bufferedImage, Color.decode("#00FFFFFF"));
  }

  public BufferedImage draw(BufferedImage bufferedImage, Color backgroundColor) {
    Graphics2D graphics = bufferedImage.createGraphics();
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
    graphics.setColor(backgroundColor);
    graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
    this.draw(graphics);
    return bufferedImage;
  }

  public void draw(Graphics2D graphics) {
    List<IBorder> borders = this.getBorders();
    for (int i = 0; i < borders.size(); i++) {
      IBorder iLine = borders.get(i);
      graphics.setColor(Color.decode(iLine.getColor()));
      BasicStroke basicStroke = new BasicStroke(iLine.getWidth(), iLine.getCap(),
          iLine.getJoin(), 1, iLine.getDash(), 0.0f);
      graphics.setStroke(basicStroke);
      graphics
          .drawLine(iLine.getStartX(), iLine.getStartY(), iLine.getEndX(), iLine.getEndY());
    }
  }

  public abstract List<IBorder> getBorders();
}
