package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.edu.business.ExamQuestionService;
import pwd.initializr.edu.business.bo.ExamQuestionBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-19 14:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamQuestionServiceTest {

    //          <a href="/Web/AiExam/index/studyId/9/studyTitle/%E5%B0%8F%E5%AD%A6.html">
//
//				<div class="kssz-09">
//					小学
//				</div>
//			</a>
//			<a href="/Web/AiExam/index/studyId/10/studyTitle/%E5%88%9D%E4%B8%AD.html">
//
//				<div class="kssz-11">
//					初中
//				</div>
//			</a>
//			<a href="/Web/AiExam/index/studyId/11/studyTitle/%E9%AB%98%E4%B8%AD.html">
//
//				<div class="kssz-13">
//					高中
//				</div>
//			</a>
    @Autowired
    private ExamQuestionService examQuestionService;

    public static void main(String[] args) {
        String response = mark(null);
        List<ExamQuestionBO> examQuestionBOS = JSON
            .parseObject(response).getJSONObject("info")
            .getObject("qus", new TypeReference<List<ExamQuestionBO>>() {
            });

        int index = 0;
        StringBuilder paramsBuilder = new StringBuilder();
        for (ExamQuestionBO examQuestionBO : examQuestionBOS) {
            paramsBuilder.append("examForm[" + index + "][id]=" + index);
            paramsBuilder.append("&");
            paramsBuilder.append("examForm[" + index + "][item]=" + "item2");
            paramsBuilder.append("&");
            paramsBuilder.append("examForm[" + index + "][answer]=" + examQuestionBO.getAnswer());
            paramsBuilder.append("&");
            paramsBuilder.append("examForm[" + index + "][qus]=" + examQuestionBO.getId());
            paramsBuilder.append("&");

            index++;
        }

        String responseTwice = mark(null);
        System.out.println(responseTwice);

    }

    private static void exam() {
        HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("Connection", "keep-alive");
        header.put("Cookie", SpriderConfig.cookie);
        header.put("Host", "47.92.155.170");
        header.put("User-Agent", SpriderConfig.UserAgent);
        header.put("X-Requested-With", "XMLHttpRequest");
        header.put("Referer",
            "http://47.92.155.170/Web/AiExam/exam/studyId/9/examType/0/studyTitle/%E5%B0%8F%E5%AD%A6.html");

        String content = httpXByHttpClient
            .get("http://47.92.155.170/Web/AiExam/ajaxGetQus.html?examId=", header, null);

        System.out.println(content);
    }

    private static String mark(String param) {
        HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("Connection", "keep-alive");
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        header.put("Cookie", SpriderConfig.cookie);
        header.put("Host", "47.92.155.170");
        header.put("User-Agent", SpriderConfig.UserAgent);
        header.put("Origin", "http://47.92.155.170");
        header.put("X-Requested-With", "XMLHttpRequest");
        header.put("Referer",
            "http://47.92.155.170/Web/AiExam/exam/studyId/9/examType/0/studyTitle/%E5%B0%8F%E5%AD%A6.html");

        String body = param + "examType=0&studyId=9&jishiqi=200";
        String content = httpXByHttpClient
            .postForm("http://47.92.155.170/Web/AiExam/ajaxsubmitExam.html", header, body);

        return content;
    }

    @Test
    public void insert() throws Exception {

    }
}
