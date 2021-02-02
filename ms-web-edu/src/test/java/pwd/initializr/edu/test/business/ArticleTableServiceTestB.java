package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.edu.business.bo.ArticleTableBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-02 14:55
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTableServiceTestB {

    @Autowired
    private pwd.initializr.edu.business.ArticleTableService articleContentService;

    @Test
    public void articleContent() throws Exception {
        ArticleTableBO articleTableBO = new ArticleTableBO();
        articleTableBO.setPid(0L);
        articleTableBO.setName("初中");
        articleTableBO.setLeaf(0);
        articleTableBO.setData("4028b4816460a6da016460b186140003");

        System.out.println(JSON.toJSONString(articleTableBO));

        articleContentService.insert(articleTableBO);

        StringBuilder lines = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
            new FileReader(new File("e://b.html")))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String html = lines.toString();

        Document document = Jsoup.parse(html);
        Element body = document.body();
        Elements elementsByClass = body.getElementsByClass("tree tree-bbox");
        Element ul = elementsByClass.get(0).child(0);
        Elements liChildren = ul.children();
        for (Element liChild : liChildren) {
            String title = liChild.getElementsByTag("div").get(0).getElementsByTag("a")
                .attr("title");

            ArticleTableBO versionArticleTableBO = new ArticleTableBO();
            versionArticleTableBO.setPid(articleTableBO.getId());
            versionArticleTableBO.setName(title);
            versionArticleTableBO.setLeaf(0);

            System.out.println("\t" + JSON.toJSON(versionArticleTableBO));

            articleContentService.insert(versionArticleTableBO);

            Element yijiUL = liChild.getElementsByClass("yiji-ul").get(0);
            Elements yijiULChildren = yijiUL.children();
            for (Element yijiULChild : yijiULChildren) {
                String grade = yijiULChild.getElementsByClass("close_menu").get(0)
                    .getElementsByTag("a").attr("title");

                ArticleTableBO gradeArticleTableBO = new ArticleTableBO();
                gradeArticleTableBO.setPid(versionArticleTableBO.getId());
                gradeArticleTableBO.setName(grade);
                gradeArticleTableBO.setLeaf(0);

                System.out.println("\t\t" + JSON.toJSON(gradeArticleTableBO));

                articleContentService.insert(gradeArticleTableBO);

                Element erjiUL = yijiULChild.getElementsByClass("erji-ul").get(0);
                Elements erjiULChildren = erjiUL.children();
                for (Element erjiULChild : erjiULChildren) {
                    Element element = erjiULChild.getElementsByTag("a").get(0);
                    String dataId = element.attr("data-id");
                    String dataTitle = element.attr("title");

                    ArticleTableBO unitArticleTableBO = new ArticleTableBO();
                    unitArticleTableBO.setPid(gradeArticleTableBO.getId());
                    unitArticleTableBO.setName(dataTitle);
                    unitArticleTableBO.setData(dataId);
                    unitArticleTableBO.setLeaf(1);

                    System.out.println("\t\t\t" + JSON.toJSON(unitArticleTableBO));

                    articleContentService.insert(unitArticleTableBO);
                }

            }
        }
    }

}
