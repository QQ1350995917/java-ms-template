package pwd.initializr.common.vcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * pwd.initializr.common.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 18:54
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class VCodeHelper {

    int width = 300;
    int height = 60;
    int fontSize = 50;

    public abstract CodeMessage productMessage();

    protected abstract CodeMessage productMessage(Integer length);

    public abstract BufferedImage productImage();

    public abstract BufferedImage productImage(String codeMessage);

    protected void draw(BufferedImage bufferedImage, String codeMessage) {
        if (bufferedImage == null) {
            throw new NullPointerException("验证码画布不能为空");
        }

        if (codeMessage == null || "".equals(codeMessage.trim())) {
            throw new NullPointerException("验证码内容不能为空");
        }
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(getRandomColor(240, 250));
        graphics.setFont(new Font("Fixedsys", Font.PLAIN, fontSize));
        graphics.fillRect(0, 0, width, height);
        Random random = new Random();
        graphics.setColor(getRandomColor(180, 230));
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(60);
            int y1 = random.nextInt(60);
            graphics.drawLine(x, y, x1, y1);
        }
        graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
            20 + random.nextInt(110)));
        int red = 0, green = 0, blue = 0;
        for (int i = 0; i < codeMessage.length(); i++) {
            String code = codeMessage.substring(i,i+1);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            graphics.setColor(new Color(red, green, blue));
            graphics.drawString(code, (i + 1) * 40, 45);
        }
        graphics.dispose();
    }

    protected Color getRandomColor(int fc, int bc) {
        //利用随机数
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
