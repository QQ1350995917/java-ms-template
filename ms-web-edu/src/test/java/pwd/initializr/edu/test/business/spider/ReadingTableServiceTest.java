package pwd.initializr.edu.test.business.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.edu.business.GrammarTableService;
import pwd.initializr.edu.business.ReadingTableService;
import pwd.initializr.edu.business.bo.ReadingTableBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-18 10:49
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadingTableServiceTest {

    @Autowired
    private ReadingTableService readingTableService;


    @org.junit.Test
    public void table0 () {
        StringBuilder lines = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
            new FileReader(new File("E:\\workspace\\github\\ms-web-initializr\\ms-web-edu\\src\\test\\java\\pwd\\initializr\\edu\\test\\temp.html")))) {
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
        Element rootElement = body.getElementsByClass("tree tree-bbox").get(0);
        Element rootUL = rootElement.getElementsByTag("ul").get(0);
        Elements itemLevel = rootUL.children();
        for (Element item : itemLevel) {
            String level = item.getElementsByTag("div").get(0).getElementsByTag("a").get(0).text();
            Long parentId = 0L;
            if ("小学".equals(level)) {
                parentId = -1L;
            } else if ("初中".equals(level)) {
                parentId = -2L;
            } else if ("高中".equals(level)) {
                parentId = -3L;
            }
            System.out.println(level);
            Element ul = item.getElementsByTag("ul").get(0);
            Elements li = ul.getElementsByTag("li");
            for (Element element : li) {
                String id = element.getElementsByTag("div").get(0).attr("data-id");
                String title = element.getElementsByTag("div").get(0).getElementsByTag("a").get(0)
                    .text();
                System.out.println("\t" + title + ":" + id);

                ReadingTableBO readingTableBO = new ReadingTableBO();
                readingTableBO.setId(Long.parseLong(id));
                readingTableBO.setPid(parentId);
                readingTableBO.setName(title);
                readingTableBO.setLeaf(1);

                readingTableService.insert(readingTableBO);
            }
        }
    }



    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
    }
}
