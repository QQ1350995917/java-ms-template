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
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;

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
public class ReadingQuestionSpider {

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
        Elements questionElements = body.getElementsByClass("qus-box");
        for (Element questionElement : questionElements) {
            String title = questionElement.getElementsByClass("qus-title").get(0).text();
            Elements answerElements = questionElement.getElementsByClass("qus-answer");
            System.out.println(title);
            for (Element answerElement : answerElements) {
                String answer = answerElement.getElementsByClass("my_radio").get(0)
                    .text();
                if ("answer1".equals(answerElement.attr("data-answer"))) {

                } else if ("answer2".equals(answerElement.attr("data-answer"))) {

                } else if ("answer3".equals(answerElement.attr("data-answer"))) {

                } else if ("answer4".equals(answerElement.attr("data-answer"))) {

                }
                System.out.println("\t" + answer);
            }
        }
    }
}
