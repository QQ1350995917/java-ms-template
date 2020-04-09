package pwd.initializr.typeface.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * pwd.initializr.typeface.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 22:25
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class FontInitUpload {

  public static void main(String[] args) throws Exception {

    File file = new File("/Users/pwd/workspace/dingpw/hornbook-service/xresources/jsons/All.json");
    JSONArray jsonArray = JSON.parseObject(new FileInputStream(file), JSONArray.class, null);
    jsonArray.stream().forEach(jsonObject -> {
      String title = ((JSONObject) jsonObject).getString("title");
      String id = ((JSONObject) jsonObject).getString("id");
      if (!new File(
          "/Users/pwd/workspace/dingpw/hornbook-service/xresources/fonts/" + title + ".ttf")
          .exists()) {
        System.out.println(((JSONObject) jsonObject).toJSONString());
      } else {
        try {
          InputStream thumbInputStream = new FileInputStream(
              new File(
                  "/Users/pwd/workspace/dingpw/hornbook-service/xresources/thumb/" + id + ".png"));
          String thumbName = "font/" + title + ".jpg";

          //minioClient.putObject(bucketName, thumbName, thumbInputStream, "image/jpeg");

          InputStream ttfInputStream = new FileInputStream(
              new File("/Users/pwd/workspace/dingpw/hornbook-service/xresources/fonts/" + title
                  + ".ttf"));
          String ttfName = "font/" + title + ".ttf";
          //minioClient.putObject(bucketName, ttfName, ttfInputStream, "application/x-font-ttf");

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

}
