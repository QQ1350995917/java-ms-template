package pwd.initializr.common.vcode;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * pwd.initializr.common.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 00:22
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class GoogleCode extends VCodeHelper {
    private final String originCode = "abcdefghigklmnopqrstuvwxyzZXCVBNMASDFGHJKLQWERTYUIOP0123456789";

    @Override
    public String getOriginCode() {
        return originCode;
    }

    @Override
    public CodeMessage productMessage() {
        return productMessage(null);
    }

    @Override
    protected CodeMessage productMessage(Integer length) {
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(createConfig());
        String text = captchaProducer.createText();
        return new CodeMessage(text,text);
    }

    @Override
    public BufferedImage productImage() {
        return productImage(productMessage().getPresented());
    }

    @Override
    public BufferedImage productImage(String codeMessage) {
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(createConfig());
        return captchaProducer.createImage(codeMessage);
    }


    private Config createConfig(){
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "black");
        //边框厚度
        properties.setProperty("kaptcha.border.thickness", "1");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "200");
        // 图片高
        properties.setProperty("kaptcha.image.height", "50");
        //图片实现类
        properties.setProperty("kaptcha.producer.impl", "com.google.code.kaptcha.impl.DefaultKaptcha");
        //文本实现类
        properties.setProperty("kaptcha.textproducer.impl", "com.google.code.kaptcha.text.impl.DefaultTextCreator");
        //文本集合，验证码值从此集合中获取
        properties.setProperty("kaptcha.textproducer.char.string", originCode);
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "6");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "Fixedsys");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        //干扰实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        //干扰颜色
        properties.setProperty("kaptcha.noise.color", "blue");
        //干扰图片样式
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        //背景实现类
        properties.setProperty("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");
        //背景颜色渐变，结束颜色
        properties.setProperty("kaptcha.background.clear.to", "white");
        //文字渲染器
        properties.setProperty("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        Config config = new Config(properties);
        return config;
    }
}
