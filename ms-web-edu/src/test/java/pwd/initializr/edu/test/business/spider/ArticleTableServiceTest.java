package pwd.initializr.edu.test.business.spider;

import com.alibaba.fastjson.JSON;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.utils.StringUtils;
import pwd.initializr.edu.persistence.dao.ArticleTableDao;
import pwd.initializr.edu.persistence.entity.ArticleTableEntity;

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
public class ArticleTableServiceTest {

  @Resource
  private ArticleTableDao dao;

  @Test
  public void articleTable() throws Exception {
    List<TempEntity> results = new LinkedList<>();
    List<ArticleTableEntity> tops0 = dao.queryBySql("select * from article_table where pid = 0");
    for (ArticleTableEntity top0 : tops0) {
      List<ArticleTableEntity> tops1 = dao
          .queryBySql("select * from article_table where pid = " + top0.getId());
      for (ArticleTableEntity top1 : tops1) {
        List<ArticleTableEntity> tops2 = dao
            .queryBySql("select * from article_table where pid = " + top1.getId());
        for (ArticleTableEntity top2 : tops2) {
          List<ArticleTableEntity> tops3 = dao
              .queryBySql("select * from article_table where pid = " + top2.getId());
          for (ArticleTableEntity top3 : tops3) {
            String name = top1.getName();
            String originName = name;

            if ("剑桥少儿英语（2010版）".equals(name)) {
              // TODO
              //预备级A
              //预备级B
              //一级A
              //一级B
              //二级A
              //二级B
              //三级A
              //三级B
              continue;
            }
            if ("人教社新课标(初中)".equals(name)) {
              // TODO
              //七年级上
              //七年级下
              //八年级上
              //八年级下
              //九年级
              continue;
            }
            if ("鲁教版（初中）".equals(name)) {
              // TODO
              //六上
              //六下
              //七上
              //七下
              //八上
              //八下
              //九年级
              continue;
            }
            if ("冀教版".equals(name)) {
              // TODO
              //七上
              //七下
              //八上
              //八下
              //九年级
              continue;
            }
            if ("牛津上海版(同步课文)".equals(name)) {
              // TODO
              //6A
              //6B
              //7A
              //7B
              //8A
              //8B
              //9A
              //9B
              continue;
            }
            if ("人教社课标版(高中)".equals(name)) {
              // TODO
              //必修一
              //必修二
              //必修三
              //必修四
              //必修五
              //选修六
              //选修七
              //选修八
              continue;
            }
            if ("外研社新标准(同步课文)".equals(name)) {
              // TODO 必修
              //必修1
              //必修2
              //必修3
              //必修4
              //必修5
              //选修6
              //选修7
              //选修8
              continue;
            }
            if ("牛津译林上海版".equals(name)) {
              // TODO
              //牛津高中S1A
              //牛津高中S1B
              //牛津高中S2A
              //牛津高中S2B
              //牛津高中S3A
              //牛津高中S3B
              continue;
            }
            if ("剑桥少儿英语（2013版）".equals(name)) {
              // TODO
              //预备级A
              //预备级B
              //一级A
              //一级B
              //二级A
              //二级B
              //三级A
              //三级B
              continue;
            }
            if ("新概念英语(测试)".equals(name)) {
              // TODO
              //第一册(英音)
              //第一册(美音)
              //第二册(英音)
              //第二册(美音)
              //第三册(英音)
              //第三册(美音)
              //第四册(英音)
              //第四册(美音)
              continue;
            }
            if ("2019高中人教版".equals(name)) {
              // TODO
              // 必修1
              continue;
            }

            String term = top0.getName();
            String grade = null;
            String stage = null;
            String bookName = top1.getName();


            if (Pattern.matches(".*上", name)) {
              grade = name.replace("上", "");
              stage = "上学期";
            } else if (Pattern.matches(".*下", name)) {
              grade = name.replace("下", "");
              stage = "下学期";
            } else if ("陕旅版(三年级起点)".equals(name)
                || "科普版(三年级起点)".equals(name)
                || "沪教版上海牛津(三年级起点)".equals(name)
                || "外研社(三年级起点)".equals(name)
                || "2012湘鲁版".equals(name)
                || "2012北师大版(同步课文)".equals(name)
                || "外研社(一年级起点)".equals(name)
                || "广东人民版小学英语(开心学英语)".equals(name)
                || "外研社(初中)".equals(name)
                || "牛津译林2012（初中）".equals(name)
                || "2012版沪教版".equals(name)
                || "2012科普版北京仁爱".equals(name)
                || "人教社PEP版(小学)".equals(name)
                || "湖南英语(小学)".equals(name)
                || "辽师大（小学）".equals(name)
                || "牛津译林（小学）".equals(name)
                || "山东科技版(小学)".equals(name)
                || "冀教版（小学）".equals(name)
                || "闽教版".equals(name)
                ) {
              if (top2.getName().contains("上")) {
                grade = top2.getName().replace("上","年级");
                stage = "上学期";
              } else if (top2.getName().contains("下")) {
                grade = top2.getName().replace("下","年级");
                stage = "下学期";
              } else {
                System.err.println(top1.getName());
              }
            } else if ("人教版2013（一年级起点）".equals(name)
                || "北京出版社一年级起点".equals(name)
                || "人教社新起点".equals(name)
                || "人教精通版小学同步课文".equals(name)
                || "重大版".equals(name)
                || "EEC小学英语（2013）".equals(name)
                || "牛津英语(小学)".equals(name)
                ) {
              if (top2.getName().contains("上")) {
                grade = top2.getName().replace("上","");
                stage = "上学期";
              } else if (top2.getName().contains("下")) {
                grade = top2.getName().replace("下","");
                stage = "下学期";
              } else {
                System.err.println(top1.getName());
              }
            } else if ("牛津英语（上海版）".equals(name)
                ) {
              if (top2.getName().contains("上册")) {
                grade = top2.getName().replace("上册","");
                stage = "上学期";
              } else if (top2.getName().contains("下册")) {
                grade = top2.getName().replace("下册","");
                stage = "下学期";
              } else {
                System.err.println(top1.getName());
              }
            } else if ("新概念英语（青少版）".equals(name)
                ) {
              if (top2.getName().contains("A")) {
                grade = top2.getName().replace("A","");
                stage = "上学期";
              } else if (top2.getName().contains("B")) {
                grade = top2.getName().replace("B","");
                stage = "下学期";
              } else {
                System.err.println(top1.getName());
              }
              if (grade.equals("1")) {
                grade = "一年级";
              } else if (grade.equals("2")) {
                grade = "二年级";
              } else if (grade.equals("3")) {
                grade = "三年级";
              } else {
                System.err.println(top1.getName());
              }
            }

            TempEntity tempEntity = new TempEntity(term, grade, stage, bookName,
                top3.getName(), top3.getId(), top3.getData());

            String gradeResult = tempEntity.getGrade();
            String stageResult = tempEntity.getStage();
            if (StringUtils.isBlank(gradeResult) || StringUtils.isBlank(stageResult)) {
              System.out.println(originName + "-" + JSON.toJSONString(tempEntity));
            } else {
              String patternGrade = "一年级|二年级|三年级|四年级|五年级|六年级|七年级|八年级|九年级";
              String patternStage = "上学期|下学期";
              boolean isMatchGrade = Pattern.matches(patternGrade, gradeResult);
              boolean isMatchStage = Pattern.matches(patternStage, stageResult);
              if (!isMatchGrade || !isMatchStage) {
                System.out.println(originName + ":" + JSON.toJSONString(tempEntity));
              } else {
                System.out.println(JSON.toJSONString(tempEntity));
              }
            }
          }
        }
      }
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  @Getter
  @Setter
  class TempEntity {

    private String term;
    private String grade;
    private String stage;
    private String bookName;
    private String unitName;
    private Long id;
    private String data;

  }
}
