package pwd.initializr.typeface.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.typeface.TypefaceApplication;
import pwd.initializr.typeface.business.FontService;
import pwd.initializr.typeface.business.bo.FontBO;

/**
 * pwd.initializr.typeface.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 23:00
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TypefaceApplication.class})// 指定启动类
public class FontInit {
  @Autowired
  private FontService fontService;
  @Test
  public void testFontInit() throws Exception {
    File file = new File("/Users/pwd/workspace/dingpw/hornbook-service/xresources/jsons/All.json");
    JSONArray jsonArray = JSON.parseObject(new FileInputStream(file), JSONArray.class, null);
    jsonArray.stream().forEach(jsonObject -> {
      String title = ((JSONObject) jsonObject).getString("title");
      if (!new File(
          "/Users/pwd/workspace/dingpw/hornbook-service/xresources/fonts/" + title + ".ttf")
          .exists()) {
        System.out.println(((JSONObject) jsonObject).toJSONString());
      } else {
        FontBO fontBO = new FontBO();
        fontBO.setName(title);
        fontBO.setFileUrl("font/" + title + ".ttf");
        fontBO.setThumbUrl("font/" + title + ".png");
        fontBO.setCreateTime(System.currentTimeMillis());
        fontBO.setUpdateTime(System.currentTimeMillis());
        fontService.save(fontBO);
      }
    });

  }
}
