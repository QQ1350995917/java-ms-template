package pwd.initializr.edu.test.business.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
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
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.edu.business.ReadingContentService;
import pwd.initializr.edu.business.ReadingQuestionService;
import pwd.initializr.edu.business.bo.ReadingContentBO;
import pwd.initializr.edu.business.bo.ReadingQuestionBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-18 10:51
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadingQuestionServiceTest {

    @Autowired
    private ReadingContentService readingContentService;
    @Autowired
    private ReadingQuestionService readingQuestionService;

    public static void main(String[] args) {

    }

    @org.junit.Test
    public void question() {
        LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
        ScopeBO scopeBO1 = new ScopeBO();
        scopeBO1.setHit("EM");
        scopeBO1.setFieldName("able");
        scopeBO1.setFieldValue("0");
        scopeBOS.add(scopeBO1);

        PageableQueryResult<ReadingContentBO> readingTableBOPageableQueryResult = readingContentService
            .queryAllByCondition(scopeBOS, null, 0L, Long.MAX_VALUE);
        List<ReadingContentBO> elements = readingTableBOPageableQueryResult.getElements();
        // against try again
        elements = null;
        // against try again
        for (ReadingContentBO element : elements) {
            HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
            HashMap<String, String> header = new HashMap<>();
            header.put("Accept", "application/json, text/javascript, */*; q=0.01");
            header.put("Accept-Encoding", "gzip, deflate");
            header.put("Accept-Language", "en-US,en;q=0.5");
            header.put("Connection", "keep-alive");
            header.put("Cookie", SpriderConfig.cookie);
            header.put("Host", "47.92.155.170");
            header.put("Referer",
                "http://47.92.155.170/Web/AiReading/index.html");
            header.put("User-Agent", SpriderConfig.UserAgent);
            header.put("X-Requested-With", "XMLHttpRequest");

            String content = httpXByHttpClient
                .get("http://47.92.155.170/Web/AiReading/readingexam/id/" + element.getId()
                    + ".html", header, null);

            Document document = Jsoup.parse(content);
            Element body = document.body();
            Elements questionElements = body.getElementsByClass("qus-box");

            for (Element questionElement : questionElements) {
                ReadingQuestionBO readingQuestionBO = new ReadingQuestionBO();
                readingQuestionBO.setPid(element.getId());

                String title = questionElement.getElementsByClass("qus-title").get(0).text();
                readingQuestionBO.setTitle(title);

                Elements answerElements = questionElement.getElementsByClass("qus-answer");
                for (Element answerElement : answerElements) {
                    String answer = answerElement.getElementsByClass("my_radio").get(0)
                        .text();
                    if ("answer1".equals(answerElement.attr("data-answer"))) {
                        readingQuestionBO.setA1(answer);
                    } else if ("answer2".equals(answerElement.attr("data-answer"))) {
                        readingQuestionBO.setA2(answer);
                    } else if ("answer3".equals(answerElement.attr("data-answer"))) {
                        readingQuestionBO.setA3(answer);
                    } else if ("answer4".equals(answerElement.attr("data-answer"))) {
                        readingQuestionBO.setA4(answer);
                    } else if ("answer5".equals(answerElement.attr("data-answer"))) {
                        readingQuestionBO.setA5(answer);
                    }
                }

                try {
                    readingQuestionService.insert(readingQuestionBO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            readingContentService.ableById(element.getId(), EntityAble.ENABLE);
        }

    }

    @org.junit.Test
    public void answer() {
        LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
        ScopeBO scopeBO1 = new ScopeBO();
        scopeBO1.setHit("EM");
        scopeBO1.setFieldName("able");
        scopeBO1.setFieldValue("0");
        scopeBOS.add(scopeBO1);



        PageableQueryResult<ReadingContentBO> readingTableBOPageableQueryResult = readingContentService
            .queryAllByCondition(scopeBOS, null, 0L, Long.MAX_VALUE);
        List<ReadingContentBO> readingContents = readingTableBOPageableQueryResult.getElements();
        for (ReadingContentBO readingContent : readingContents) {
            LinkedHashSet<ScopeBO> questionScopeBOS = new LinkedHashSet<>();
            ScopeBO questionScopeBO = new ScopeBO();
            questionScopeBO.setHit("EM");
            questionScopeBO.setFieldName("pid");
            questionScopeBO.setFieldValue(String.valueOf(readingContent.getId()));
            questionScopeBOS.add(questionScopeBO);

            LinkedHashSet<SortBO> sortBOS = new LinkedHashSet<>();
            SortBO sortBO0 = new SortBO();
            sortBO0.setFieldName("id");
            sortBO0.setSort("ASC");
            sortBOS.add(sortBO0);

            PageableQueryResult<ReadingQuestionBO> questionResult = readingQuestionService
                .queryAllByCondition(questionScopeBOS, sortBOS, 0L, Long.MAX_VALUE);
            List<ReadingQuestionBO> questions = questionResult.getElements();
            boolean allQuestionsRight = false;
            int doRequestTimes = 0;
            LinkedHashMap<Long, String> currentAnswers = new LinkedHashMap<>();
            LinkedHashMap<String, Boolean> currentAnswerRights = new LinkedHashMap<>();
            do {
                int questionIndex = 0;
                for (ReadingQuestionBO question : questions) {
                    String currentAnswer = currentAnswers.get(question.getId());
                    if (currentAnswer == null) {
                        currentAnswer = "answer1";
                    }

                    Boolean currentAnswerRight = currentAnswerRights.get(currentAnswer + questionIndex);
                    if (currentAnswerRight == null) {
                        currentAnswerRight = false;
                        currentAnswerRights.put(currentAnswer + questionIndex,currentAnswerRight);
                    }

                    currentAnswer = answerTry(currentAnswer, currentAnswerRight, (doRequestTimes == 0));

                    currentAnswers.put(question.getId(),currentAnswer);

                    questionIndex ++;
                }

                Collection<String> values = currentAnswers.values();
                StringBuilder ansParams = new StringBuilder();
                for (String value : values) {
                    ansParams.append(value).append("-");
                }

                ansParams.deleteCharAt(ansParams.length() - 1);

                HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
                HashMap<String, String> header = new HashMap<>();
                header.put("Accept", "application/json, text/javascript, */*; q=0.01");
                header.put("Accept-Encoding", "gzip, deflate");
                header.put("Accept-Language", "en-US,en;q=0.5");
                header.put("Connection", "keep-alive");
                header.put("Cookie", SpriderConfig.cookie);
                header.put("Host", "47.92.155.170");
                header.put("Origin", "47.92.155.170");
                header.put("Referer",
                    "http://47.92.155.170/Web/AiReading/readingexam/id/" + readingContent.getId() + ".html");
                header.put("User-Agent", SpriderConfig.UserAgent);
                header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                header.put("X-Requested-With", "XMLHttpRequest");

                String params = "aj_answers=" + ansParams.toString() + "&aj_bookId=" + readingContent.getId() + "&validTime=200";

                String content = httpXByHttpClient
                    .postForm("http://47.92.155.170/Web/AiReading/ajaxSubmitExam.html", header, params);

                JSONArray jsonArray = JSON.parseObject(content).getJSONObject("info")
                    .getJSONArray("result");
                boolean allRight = true;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject answer =  (JSONObject)jsonArray.get(i);
                    String choose = answer.getString("choose");
                    boolean isRight = answer.getInteger("isRight") == 1;
                    currentAnswerRights.put(choose + i,isRight);
                    if (!isRight) {
                        allRight = false;
                    }
                }
                doRequestTimes ++;

                if (allRight) {
                    currentAnswers.forEach((id,a) -> {
                        String answer = null;
                        if ("answer1".equals(a)) {
                            answer = "a1";
                        } else if ("answer2".equals(a)) {
                            answer = "a2";
                        } else if ("answer3".equals(a)) {
                            answer = "a3";
                        } else if ("answer4".equals(a)) {
                            answer = "a4";
                        }
                        ReadingQuestionBO readingQuestionBO = new ReadingQuestionBO();
                        readingQuestionBO.setId(id);
                        readingQuestionBO.setA(answer);
                        readingQuestionService.updateById(readingQuestionBO);
                    });
                    allQuestionsRight = true;
                }
            } while (!allQuestionsRight);


            readingContentService.ableById(readingContent.getId(), EntityAble.ENABLE);
        }
//        POST /Web/AiReading/ajaxSubmitExam.html HTTP/1.1
//        Host: 47.92.155.170
//        Connection: keep-alive
//        Content-Length: 78
//        Accept: application/json, text/javascript, */*; q=0.01
//        X-Requested-With: XMLHttpRequest
//        User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36
//        Content-Type: application/x-www-form-urlencoded; charset=UTF-8
//        Origin: http://47.92.155.170
//        Referer: http://47.92.155.170/Web/AiReading/readingexam/id/332.html
//        Accept-Encoding: gzip, deflate
//        Accept-Language: zh-CN,zh;q=0.9
//        Cookie: tianjinandznyy_=0jvqdj2fu9i0pfluq1arj0pki3

//        aj_answers=answer1-answer1-answer1-answer1-answer2&aj_bookId=333&validTime=86
//
//        aj_answers: answer1-undefined-undefined-undefined
//        aj_bookId: 332
//        validTime: 200


//        aj_answers: answer1-answer1-answer1-answer1
//        aj_bookId: 332
//        validTime: 222

//        {"info":{"result":[{"choose":"answer1","right":"1","isRight":1},{"choose":"answer3","right":"1","isRight":0},{"choose":"answer1","right":"1","isRight":1},{"choose":"answer2","right":"1","isRight":0}],"unlock":false},"status":1,"url":""}
//        {"info":{"result":[{"choose":"answer1","right":"1","isRight":1},{"choose":"answer1","right":"1","isRight":1},{"choose":"answer1","right":"1","isRight":1},{"choose":"answer1","right":"1","isRight":1}],"unlock":true},"status":1,"url":""}
//        {"info":{"result":[{"choose":"answer1","right":"1","isRight":1},{"choose":"answer1","right":"1","isRight":1},{"choose":"answer1","right":"1","isRight":1},{"choose":"answer1","right":"1","isRight":1},{"choose":"answer2","right":"2","isRight":1}],"unlock":true},"status":1,"url":""}
    }


    public static String answerTry(String answer0,boolean right,boolean start){
        if (start) {
            return answer0;
        }
        if (right) {
            return answer0;
        }
        if ("answer1".equals(answer0)) {
            return "answer2";
        } else if ("answer2".equals(answer0)) {
            return "answer3";
        } else if ("answer3".equals(answer0)) {
            return "answer4";
        } else {
            throw new RuntimeException("not right answer");
        }
    }
}

