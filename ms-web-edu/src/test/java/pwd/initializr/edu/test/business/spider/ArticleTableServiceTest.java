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
import org.springframework.beans.factory.annotation.Autowired;
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
            List<ArticleTableEntity> tops1 = dao.queryBySql("select * from article_table where pid = " + top0.getId());
            for (ArticleTableEntity top1 : tops1) {
                List<ArticleTableEntity> tops2 = dao.queryBySql("select * from article_table where pid = " + top1.getId());
                for (ArticleTableEntity top2 : tops2) {
                    List<ArticleTableEntity> tops3 = dao.queryBySql("select * from article_table where pid = " + top2.getId());
                    for (ArticleTableEntity top3 : tops3) {
                        String name = top1.getName();
                        String originName = name;
                        if ("陕旅版(三年级起点)".equals(name)) {
                            continue;
                        }
                        if ("科普版(三年级起点)".equals(name)) {
                            continue;
                        }
                        if ("2012湘鲁版".equals(name)) {
                            continue;
                        }
                        if ("2012北师大版(同步课文)".equals(name)) {
                            continue;
                        }
                        if ("外研社(一年级起点)".equals(name)) {
                            continue;
                        }
                        if ("沪教版上海牛津(三年级起点)".equals(name)) {
                            continue;
                        }
                        if ("人教精通版小学同步课文".equals(name)) {
                            continue;
                        }
                        if ("牛津英语（上海版）".equals(name)) {
                            continue;
                        }
                        if ("北京出版社一年级起点".equals(name)) {
                            continue;
                        }
                        if ("新概念英语（青少版）".equals(name)) {
                            continue;
                        }
                        if ("闽教版".equals(name)) {
                            continue;
                        }
                        if ("剑桥少儿英语（2010版）".equals(name)) {
                            continue;
                        }
                        if ("人教社新起点".equals(name)) {
                            continue;
                        }
                        if ("广东人民版小学英语(开心学英语)".equals(name)) {
                            continue;
                        }
                        if ("重大版".equals(name)) {
                            continue;
                        }
                        if ("外研社(初中)".equals(name)) {
                            continue;
                        }
                        if ("人教社新课标(初中)".equals(name)) {
                            continue;
                        }
                        if ("鲁教版（初中）".equals(name)) {
                            continue;
                        }
                        if ("牛津译林2012（初中）".equals(name)) {
                            continue;
                        }
                        if ("2012版沪教版".equals(name)) {
                            continue;
                        }
                        if ("2012科普版北京仁爱".equals(name)) {
                            continue;
                        }
                        if ("冀教版".equals(name)) {
                            continue;
                        }
                        if ("牛津上海版(同步课文)".equals(name)) {
                            continue;
                        }
                        if ("人教社课标版(高中)".equals(name)) {
                            continue;
                        }
                        if ("外研社新标准(同步课文)".equals(name)) {
                            continue;
                        }
                        if ("牛津译林上海版".equals(name)) {
                            continue;
                        }
                        if ("EEC小学英语（2013）".equals(name)) {
                            continue;
                        }
                        if ("人教版2013（一年级起点）".equals(name)) {
                            continue;
                        }
                        if ("牛津英语(小学)".equals(name)) {
                            continue;
                        }
                        if ("人教社PEP版(小学)".equals(name)) {
                            continue;
                        }
                        if ("外研社(三年级起点)".equals(name)) {
                            continue;
                        }
                        if ("湖南英语(小学)".equals(name)) {
                            continue;
                        }
                        if ("辽师大（小学）".equals(name)) {
                            continue;
                        }
                        if ("牛津译林（小学）".equals(name)) {
                            continue;
                        }
                        if ("山东科技版(小学)".equals(name)) {
                            continue;
                        }
                        if ("冀教版（小学）".equals(name)) {
                            continue;
                        }
                        if ("剑桥少儿英语（2013版）".equals(name)) {
                            continue;
                        }
                        if ("新概念英语(测试)".equals(name)) {
                            // FIXME:不区分上下学期
                            continue;
                        }
                        if ("2019高中人教版".equals(name)) {
                            // FIXME: 没有年级
                            continue;
                        }




                        String stage = null;
                        String pattern0 = ".*上";
                        boolean isMatch0 = Pattern.matches(pattern0, name);
                        if (isMatch0) {
                            name = name.replace("上","");
                            stage = "上学期";
                        }
                        String pattern1 = ".*下";
                        boolean isMatch1 = Pattern.matches(pattern1, name);
                        if (isMatch1) {
                            name = name.replace("下","");
                            stage = "下学期";
                        }

                        TempEntity tempEntity = new TempEntity(top0.getName(),name,stage,top1.getName(),top3.getName(),top3.getId(),top3.getData());

                        String gradeResult = tempEntity.getGrade();
                        String stageResult = tempEntity.getStage();
                        if (StringUtils.isBlank(gradeResult) || StringUtils.isBlank(stageResult)) {
                            System.out.println(originName + "-" + JSON.toJSONString(tempEntity));
                        } else {
                            String patternGrade = "一年级|二年级|三年级|四年级|五年级|六年级";
                            String patternStage = "上学期|下学期";
                            boolean isMatchGrade = Pattern.matches(patternGrade, gradeResult);
                            boolean isMatchStage = Pattern.matches(patternStage, stageResult);
                            if (!isMatchGrade || !isMatchStage) {
                                System.out.println(originName + ":" + JSON.toJSONString(tempEntity));
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
