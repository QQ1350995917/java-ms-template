package pwd.initializr.edu.test.business;

import java.util.HashMap;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;

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
public class ArticleContentSpider {

    @Test
    public void articleContent() throws Exception {
        HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");
        header.put("Connection", "keep-alive");
        header.put("Cookie", "tianjinandznyy_=0jvqdj2fu9i0pfluq1arj0pki3");
        header.put("Host", "47.92.155.170");
        header.put("Referer",
            "http://47.92.155.170/Web/AiClassroom/tbkw_content/kewenId/6815/edu/4028b4816460a6da016460b2548e0004.html");
        header.put("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");
        header.put("X-Requested-With", "XMLHttpRequest");

        String content = httpXByHttpClient
            .get("http://47.92.155.170/Web/AiClassroom/ajaxGetKewen.html?kewenId=6815", header,
                null);

        content = StringEscapeUtils.unescapeJava(content);

        System.out.println(content);

    }
}
