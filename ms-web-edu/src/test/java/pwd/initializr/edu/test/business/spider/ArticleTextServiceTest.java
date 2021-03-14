package pwd.initializr.edu.test.business.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.utils.StringUtils;
import pwd.initializr.edu.persistence.dao.EduTermCourseTextbookArticleDao;
import pwd.initializr.edu.persistence.entity.EduTermCourseTextbookArticleEntity;

/**
 * pwd.initializr.edu.test.business.spider@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-03-02 15:32
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTextServiceTest {

  @Resource
  private EduTermCourseTextbookArticleDao eduTermCourseTextbookArticleDao;

  @Test
  public void articleText() throws Exception {
    List<Map<String, Object>> maps = eduTermCourseTextbookArticleDao
        .queryBySql("select * from edu_term_course_textbook_article");
    for (Map<String, Object> map : maps) {
      Long id = Long.parseLong(map.get("id").toString());
      if (map.get("pid") == null) {
        continue;
      }

      String text = String.valueOf(JSON.parse(map.get("text").toString()));
      Map<String, String> stringStringMap = JSON
          .parseObject(text, new TypeReference<Map<String, String>>() {
          });
      String cn = stringStringMap.get("cn");
      if (StringUtils.isBlank(cn)) {
        continue;
      }
      String[] split = cn.split("\r\n");
      LinkedList<Map<String, String>> result = new LinkedList<>();
      for (String s : split) {
        if (!s.contains("]")) {
          Map<String, String> last = result.getLast();
          String text1 = last.get("text") + s;
          last.put("text", text1);
          continue;
        }
        String[] split1 = s.split("]");
        String time = split1[0].replace("[", "");
        String segment = "";
        if (split1.length > 1) {
          segment = split1[1];
        }
//        String time = s.substring(0,9).replace("[","").replace("]","");
//        String segment = s.substring(10,s.length());
        LinkedHashMap<String, String> newObject = new LinkedHashMap<>();
        newObject.put("time", time);
        newObject.put("text", segment);
        result.add(newObject);
      }
      EduTermCourseTextbookArticleEntity eduTermCourseTextbookArticleEntity = new EduTermCourseTextbookArticleEntity();
      eduTermCourseTextbookArticleEntity.setId(id);
      eduTermCourseTextbookArticleEntity.setText(JSON.toJSONString(result));
      eduTermCourseTextbookArticleEntity.setUpdateTime(new Date());
      eduTermCourseTextbookArticleDao.updateById(eduTermCourseTextbookArticleEntity);
      System.out.println();
    }
  }


}
