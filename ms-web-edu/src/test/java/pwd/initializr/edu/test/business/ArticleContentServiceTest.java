package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.edu.business.ArticleContentService;
import pwd.initializr.edu.business.bo.ArticleContentBO;
import pwd.initializr.edu.persistence.dao.ArticleTableDao;
import pwd.initializr.edu.persistence.entity.ArticleTableEntity;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-02 18:32
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleContentServiceTest {

  @Autowired
  private ArticleTableDao articleTableDao;


  @Autowired
  private ArticleContentService articleContentService;

  public static void main(String[] args) {
    String online_audio = "http://houtai.i-wins.com/upload/files/20191125231331dTkfFZAV.mp3";

    try {
      downloadFile(online_audio,
          "e://" + online_audio.substring(online_audio.lastIndexOf("/") + 1));
    } catch (Exception e) {
      System.out.println("try again");
    }

  }

  private static void downloadFile(String source, String target) {
    HashMap<String, String> header = new HashMap<>();
    header.put("Accept", "*/*");
    header.put("Accept-Encoding", "identity;q=1, *;q=0");
    header.put("Accept-Language", "zh-CN,zh;q=0.9");
    header.put("Connection", "keep-alive");
    header.put("Host", "houtai.i-wins.com");
    header.put("Referer", "http://47.92.155.170/");
    header.put("User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");

    HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());

    httpXByHttpClient.getFile(source, header, new File(target));

  }

  @Test
  public void articleContent() throws Exception {
    Random random = new Random(1);
    List<ArticleTableEntity> entities = articleTableDao.queryAllLeafUsedBySpider();
    for (ArticleTableEntity entity : entities) {
      String data = entity.getData();
//      if (Long.parseLong(data) < 7024) {
//        continue;
//      }
      String ref = null;
      if (entity.getId() < 3767) {
        ref = "4028b4816460a6da016460b2548e0004";
      } else if (entity.getId() > 3767 && entity.getId() < 4999) {
        ref = "4028b4816460a6da016460b186140003";
      } else if (entity.getId() > 4999) {
        ref = "402880e66507589b0165076d65780005";
      }
      HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
      HashMap<String, String> header = new HashMap<>();
      header.put("Accept", "application/json, text/javascript, */*; q=0.01");
      header.put("Accept-Encoding", "gzip, deflate");
      header.put("Accept-Language", "zh-CN,zh;q=0.9");
      header.put("Connection", "keep-alive");
      header.put("Cookie", SpriderConfig.cookie);
      header.put("Host", "47.92.155.170");

      header.put("Referer",
          "http://47.92.155.170/Web/AiClassroom/tbkw_content/kewenId/" + data
              + "/edu/" + ref + ".html");
      header.put("User-Agent",
          "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");
      header.put("X-Requested-With", "XMLHttpRequest");

      String content = httpXByHttpClient
          .get("http://47.92.155.170/Web/AiClassroom/ajaxGetKewen.html?kewenId=" + data, header,
              null);

      content = StringEscapeUtils.unescapeJava(content);

//      Map map = null;
//      try {
//        map = JSON.parseObject(content, Map.class);
//      } catch (Exception e) {
//        System.out.println(content);
//        e.printStackTrace();
//        continue;
//      }
//      if (map == null) {
//        continue;
//      }
      ArticleContentBO articleContentBO = new ArticleContentBO();
      articleContentBO.setId(Long.parseLong(data));
      articleContentBO.setText(content);

//      if (map.containsKey("cn") && map.get("cn") != null) {
//        articleContentBO.setText(map.get("cn").toString());
//      }
//      if (map.containsKey("online_audio") && map.get("online_audio") != null) {
//        articleContentBO.setUrl(map.get("online_audio").toString());
//      }
//      if (map.containsKey("remark") && map.get("remark") != null) {
//        articleContentBO.setRemark(map.get("remark").toString());
//      }
//      if (map.containsKey("online_audio") && map.get("online_audio") != null) {
//        String onlineAudio = map.get("online_audio").toString();
//        if (onlineAudio.startsWith("http")) {
//          String path = SpriderConfig.fileStorage + "/" + onlineAudio
//              .substring(onlineAudio.lastIndexOf("/") + 1);
//
//          try {
//            downloadFile(onlineAudio, path);
//            articleContentBO.setPath(path);
//          } catch (Exception e) {
//            System.out.print("try again");
////                http://houtai.i-wins.com/upload/files/20191125231331dTkfFZAV.mp3
////                online_audio = "http://47.92.155.170:888/upload/files/20191125231331dTkfFZAV.mp3";
//            onlineAudio = onlineAudio
//                .replace("http://houtai.i-wins.com", "http://47.92.155.170:888");
//            try {
//              downloadFile(onlineAudio, path);
//              articleContentBO.setPath(path);
//              System.out.println("success");
//            } catch (Exception e1) {
//              e1.printStackTrace();
//              System.err.println("fail " + data);
//              articleContentBO.setPath("0");
//            }
//          }
//        }
//      } else {
//        articleContentBO.setPath("0");
//      }

      articleContentService.insert(articleContentBO);
      articleTableDao.ableById(entity.getId(),EntityAble.ENABLE.getNumber());
      Thread.sleep(random.nextInt(10) * 300);
    }
  }
}
