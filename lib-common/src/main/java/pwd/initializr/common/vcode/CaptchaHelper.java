package pwd.initializr.common.vcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import sun.misc.BASE64Encoder;

/**
 * pwd.initializr.common.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 18:54
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class CaptchaHelper {

    int width = 300;
    int height = 60;
    int fontSize = 50;

    public CodeMessage productMessage() {
        return productMessage(null);
    }

    protected CodeMessage productMessage(Integer length) {
        if (length == null) {
            length = 6;
        }

        if (length < 1) {
            length = 1;
        }

        if (length > 9) {
            length = 9;
        }
        String originCode = getOriginCode();
        if (originCode == null || "".equals(originCode.trim())) {
            originCode = "test";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char code = originCode.charAt(random.nextInt(originCode.length()));
            stringBuilder.append(code);
        }
        return new CodeMessage(stringBuilder.toString(), stringBuilder.toString());
    }

    protected abstract String getOriginCode();

    public abstract BufferedImage productImage();

    public abstract BufferedImage productImage(String codeMessage);

    public String productBase64Image(String codeMessage) {
        BufferedImage bufferedImage = this.productImage(codeMessage);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String encode = new BASE64Encoder().encode(byteArrayOutputStream.toByteArray())
            .replaceAll("\r|\n", "");
        return "data:image/png/;base64,"+ encode;
    }


    protected BufferedImage draw(String codeMessage) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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
            String code = codeMessage.substring(i, i + 1);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            graphics.setColor(new Color(red, green, blue));
            graphics.drawString(code, (i + 1) * 40, 45);
        }
        graphics.dispose();
        return bufferedImage;
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
