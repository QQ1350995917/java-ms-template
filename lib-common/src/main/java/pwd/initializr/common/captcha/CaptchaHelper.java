package pwd.initializr.common.captcha;

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

    private int width = 300;
    private int height = 60;
    private int fontSize = 50;

    protected int getWidth() {
        return width;
    }

    protected int getHeight() {
        return height;
    }

    protected int getFontSize() {
        return fontSize;
    }

    public CaptchaMessage createCaptcha() {
        String presentedForImage = getPresented();
        String expectedResult = getExpected();
        String presentedBase64Image = this.createBase64Image(presentedForImage);
        return new CaptchaMessage(presentedBase64Image, expectedResult);
    }

    public CaptchaMessage createCaptcha(int width, int height, int fontSize) {
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        return this.createCaptcha();
    }

    /**
     * <h2>验证码呈现内容</h2>
     * date 2020-09-08 11:25
     *
     * @return java.lang.String
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    protected abstract String getPresented();

    /**
     * <h2>验证码期望值</h2>
     * date 2020-09-08 11:24
     *
     * @return java.lang.String
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    protected abstract String getExpected();

    protected String createBase64Image(String presented) {
        BufferedImage bufferedImage = this.draw(presented);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpeg", byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String encode = new BASE64Encoder().encode(byteArrayOutputStream.toByteArray())
            .replaceAll("\r|\n", "");
        return "data:image/jpeg/;base64," + encode;
    }


    protected BufferedImage draw(String presented) {
        BufferedImage bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        if (presented == null || "".equals(presented.trim())) {
            presented = "内容为空";
        }
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(getRandomColor(240, 250));
        graphics.setFont(new Font("Fixedsys", Font.PLAIN, this.getFontSize()));
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        Random random = new Random();
        graphics.setColor(getRandomColor(180, 230));
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(this.getWidth());
            int y = random.nextInt(this.getHeight());
            int x1 = random.nextInt(60);
            int y1 = random.nextInt(60);
            graphics.drawLine(x, y, x1, y1);
        }
        graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
            20 + random.nextInt(110)));
        int red = 0, green = 0, blue = 0;
        for (int i = 0; i < presented.length(); i++) {
            String code = presented.substring(i, i + 1);
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
