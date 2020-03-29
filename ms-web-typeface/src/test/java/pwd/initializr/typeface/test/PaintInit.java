package pwd.initializr.typeface.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.typeface.TypefaceApplication;
import pwd.initializr.typeface.business.FontService;
import pwd.initializr.typeface.business.PaintingService;
import pwd.initializr.typeface.business.bo.PaintingBO;

/**
 * pwd.initializr.typeface.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 23:58
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TypefaceApplication.class})// 指定启动类
public class PaintInit {

  @Autowired
  private PaintingService paintingService;
  @Test
  public void testFontInit() throws Exception {
    for (int i = 0; i < 26; i++) {
      PaintingBO paintingBO = new PaintingBO();
      paintingBO.setUserId(0L);
      paintingBO.setFontId(0L);
      paintingBO.setFontSize(0.0F);
      paintingBO.setContent(i +"");
      paintingBO.setBackground("#00000000");
      paintingBO.setForeground("#00000000");
      paintingBO.setWidth(0);
      paintingBO.setHeight(0);
      paintingBO.setImageUrl("/");
      paintingBO.setStatus(0);
      paintingBO.setCreateTime(System.currentTimeMillis());
      paintingBO.setUpdateTime(System.currentTimeMillis());
      paintingService.save(paintingBO);
    }

  }
}
