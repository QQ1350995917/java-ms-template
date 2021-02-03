package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
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
import pwd.initializr.edu.persistence.dao.ArticleContentDao;
import pwd.initializr.edu.persistence.dao.ArticleTableDao;
import pwd.initializr.edu.persistence.entity.ArticleContentEntity;
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

    Random random = new Random(1);
    @Autowired
    private ArticleTableDao articleTableDao;
    @Autowired
    private ArticleContentDao articleContentDao;
    @Autowired
    private ArticleContentService articleContentService;

    public static void main(String[] args) {
        String online_audio = "{\"cn\":\"[00:00.00]Unit 5 \\u5355\\u51435\\n[00:10.74]Doctor and nurse \\u533b\\u751f\\u548c\\u62a4\\u58eb\\n[00:13.43]Students' Book Page 22 \\u5b66\\u751f\\u7528\\u4e6622\\u9875\\n[00:20.24]Let's act \\u8ba9\\u6211\\u4eec\\u884c\\u52a8\\n[00:22.13]Listen to me. \\u542c\\u6211\\u7684\\u3002\\n[00:24.89]Open your mouth. \\u5f20\\u5f00\\u4f60\\u7684\\u5634\\u3002\\n[00:28.79]Say \\\"Ah\\\" . \\u8bf4\\u201c\\u554a\\u201d\\u3002\\n[00:33.49]Listen to me. \\u542c\\u6211\\u7684\\u3002\\n[00:37.88]Open your mouth. \\u5f20\\u5f00\\u4f60\\u7684\\u5634\\u3002\\n[00:41.17]Say \\\"Ah\\\" . \\u8bf4\\u201c\\u554a\\u201d\\u3002\\n[00:44.85]Students' Book Page 23 \\u5b66\\u751f\\u7528\\u4e6623\\u9875\\n[00:50.96]Let's talk \\u8ba9\\u6211\\u4eec\\u8c08\\u8c08\\n[00:53.66]She is a ... \\u5979\\u662f\\u4e00\\u4e2a\\u2026\\n[00:59.77]Is she a ...?No,she is a... \\u5979\\u662f\\u4e00\\u4e2a\\u2026\\u5417?\\u4e0d,\\u5979\\u662f\\u4e00\\u4e2a\\u2026\\n[01:07.55]She is a ... \\u5979\\u662f\\u4e00\\u4e2a\\u2026\\n[01:15.76]Is she a ...?No,she is a... \\u5979\\u662f\\u4e00\\u4e2a\\u2026\\u5417?\\u4e0d,\\u5979\\u662f\\u4e00\\u4e2a\\u2026\\n[01:22.65]This end of said A. \\u8fd9\\u662fA\\u7684\\u7ed3\\u5c3e\\u3002\\n[01:57.05]Said B. \\u8bf4B\\u3002\\n[02:08.65]Students' Book Page 24 \\u5b66\\u751f\\u7528\\u4e6624\\u9875\\n[02:15.76]Let's learn \\u8ba9\\u6211\\u4eec\\u5b66\\u4e60\\n[02:17.74]Listen \\u542c\\n[02:23.23]doctor   nurse   cook   old   young \\u62a4\\u58eb\\u53a8\\u5e08\\u8001\\u5e7c\\n[02:46.53]Say \\u8bf4\\n[02:50.84]doctor   nurse   cook   old   young \\u62a4\\u58eb\\u53a8\\u5e08\\u8001\\u5e7c\\n[03:23.13]Students' Book Page 25 \\u5b66\\u751f\\u7528\\u4e6625\\u9875\\n[03:30.23]Let's play \\u8ba9\\u6211\\u4eec\\u73a9\\n[03:32.22]Tell a story \\u8bb2\\u8ff0\\u4e00\\u4e2a\\u6545\\u4e8b\\n[03:34.41]1.Is she a doctor? 1 .\\u5de5\\u4f5c\\u5979\\u662f\\u4e00\\u4e2a\\u533b\\u751f\\u5417?\\n[03:39.51]2.She is young. 2.\\u5979\\u662f\\u5e74\\u8f7b\\u7684\\u3002\\n[03:44.60]3.Open your mouth. 3 .\\u9879\\u76ee\\u5f20\\u5f00\\u4f60\\u7684\\u5634\\u3002\\n[03:50.11]4.Say \\\"Ah\\\" . 4.\\u8bf4\\u201c\\u554a\\u201d\\u3002\\n[03:54.40]5.You are fine. 5.\\u4f60\\u662f\\u5f88\\u597d\\u3002\\n[03:59.41]6.Thank you, doctor. 6.\\u8c22\\u8c22\\u4f60,\\u533b\\u751f\\u3002\\n[04:03.59]Students' Book Page 26 \\u5b66\\u751f\\u7528\\u4e6626\\u9875\\n[04:09.68]Let's enjoy \\u8ba9\\u6211\\u4eec\\u4eab\\u53d7\\n[04:12.26]Rhyme \\u62bc\\u97f5\\n[04:13.96]Look at the doctor Reading a book, \\u770b\\u533b\\u751f\\u770b\\u4e00\\u672c\\u4e66\\uff0c\\n[04:18.25]About a nurse And a cook. \\u5173\\u4e8e\\u4e00\\u4e2a\\u62a4\\u58eb\\u548c\\u4e00\\u4e2a\\u53a8\\u5e08\\u3002\\n[04:21.56]The doctor and the book, \\u533b\\u751f\\u548c\\u4e66\\uff0c\\n[04:24.36]The nurse and the cook, \\u62a4\\u58eb\\u548c\\u53a8\\u5e08\\uff0c\\n[04:27.05]Oh,look,All very old! \\u54e6,\\u770b,\\u90fd\\u5f88\\u8001!\\n[04:31.54]Look at the doctor Reading a book, \\u770b\\u533b\\u751f\\u770b\\u4e00\\u672c\\u4e66\\uff0c\\n[04:34.91]About a nurse And a cook. \\u5173\\u4e8e\\u4e00\\u4e2a\\u62a4\\u58eb\\u548c\\u4e00\\u4e2a\\u53a8\\u5e08\\u3002\\n[04:37.89]The doctor and the book, \\u533b\\u751f\\u548c\\u4e66\\uff0c\\n[04:39.98]The nurse and the cook, \\u62a4\\u58eb\\u548c\\u53a8\\u5e08\\uff0c\\n[04:42.17]Oh,look,All very old! \\u54e6,\\u770b,\\u90fd\\u5f88\\u8001!\\n[04:47.58]Work Book Page 16 \\u7ec3\\u4e60\\u518c16\\u9875\\n[04:54.06]Listen and circle the thing that belongs to each person. \\u542c\\u5e76\\u5708\\u51fa\\u5c5e\\u4e8e\\u6bcf\\u4e2a\\u4eba\\u7684\\u4e1c\\u897f\\u3002\\n[05:19.45]Work Book Page 17 \\u7ec3\\u4e60\\u518c\\u7b2c17\\u9875\\n[05:26.64]Listen and tick the correct box. \\u503e\\u542c\\u548c\\u52fe\\u221a\\u6b63\\u786e\\u7684\\u76d2\\u5b50\\u3002\\n\",\"online_audio\":\"\\/tpl\\/Web\\/audio\\/sentenceVolice\\/115.mp3\",\"remark\":null}";

        String s = StringEscapeUtils.unescapeJava(online_audio);
        System.out.println(s);


    }

    private static void downloadFile(String source, String target, String referer) {
        System.out.println("下载地址：" + source);
        HashMap<String, String> header = new HashMap<>();
//        header.put("Accept", "*/*");
        header.put("Accept-Encoding", "identity;q=1, *;q=0");
//        header.put("Accept-Language", "zh-CN,zh;q=0.9");
//        header.put("Connection", "keep-alive");
//        header.put("Host", "houtai.i-wins.com");
        header.put("Referer", referer);
        header.put("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");

        HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());

        httpXByHttpClient.getFile(source, header, new File(target));
    }

    private static void downloadFile(String source, String target) {
        String referer = "http://47.92.155.170/";
        downloadFile(source, target, referer);

    }

    @Test
    public void step2ForArticleMP3() throws Exception {
        List<ArticleContentEntity> articleContentEntities = articleContentDao.queryAllBySpider();
        articleContentEntities.stream().forEach(obj -> {
            String text = obj.getText();
            Map map = JSON.parseObject(text, Map.class);
            if (map.containsKey("online_audio") && map.get("online_audio") != null) {
                String onlineAudio = map.get("online_audio").toString();
                System.out.println("原始url:" + onlineAudio);
                String filename = UUID.randomUUID().toString() + "-" + onlineAudio
                    .substring(onlineAudio.lastIndexOf("/") + 1);
                String path = SpriderConfig.fileStorage + "/" + filename;
                System.out.println("本地文件名:" + filename + ", 本地路径：" + path);

                ArticleContentBO articleContentBO = new ArticleContentBO();
                articleContentBO.setId(obj.getId());
                if (onlineAudio.startsWith("http")) {
                    try {
                        downloadFile(onlineAudio, path);
                        System.out.println("下载成功：" + obj.getId());
                        articleContentBO.setPath(filename);
                        articleContentBO.setAble(EntityAble.ENABLE.getNumber());
                    } catch (Exception e) {
                        onlineAudio = onlineAudio
                            .replace("http://houtai.i-wins.com", "http://47.92.155.170:888");
                        System.out.print("路径错误，重试url:" + onlineAudio);
                        try {
                            downloadFile(onlineAudio, path);
                            System.out.println("下载成功：" + obj.getId());
                            articleContentBO.setPath(path);
                            articleContentBO.setAble(EntityAble.ENABLE.getNumber());
                        } catch (Exception e1) {
                            System.err.println("下载失败：" + obj.getId());
                            articleContentBO.setAble(EntityAble.DISABLE.getNumber());
                        }
                    }
                } else {

                    onlineAudio = "http://47.92.155.170" + onlineAudio;

                    String referer =
                        "http://47.92.155.170/Web/AiClassroom/tbkw_content/kewenId/" + obj
                            .getId() + "/edu/4028b4816460a6da016460b2548e0004.html";
                    try {
                        downloadFile(onlineAudio, path, referer);
                        System.out.println("下载成功：" + obj.getId());
                        articleContentBO.setPath(path);
                        articleContentBO.setAble(EntityAble.ENABLE.getNumber());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        System.err.println("下载失败：" + obj.getId() + ";错误码：" + e1.getMessage());
                        articleContentBO.setAble(EntityAble.DISABLE.getNumber());
                    }

                    if (obj.getId() < 41) {

                    } else if (obj.getId() >= 41) {

                    }
                }

                articleContentService.updateById(articleContentBO);
                try {
                    Thread.sleep(random.nextInt(10) * 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("online_audio not exist " + obj.getId());
            }

        });
    }

    @Test
    public void step1ForArticleContent() throws Exception {

        List<ArticleTableEntity> entities = articleTableDao.queryAllLeafUsedBySpider();
        int size = entities.size();
        int curr = 0;
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
                .get("http://47.92.155.170/Web/AiClassroom/ajaxGetKewen.html?kewenId=" + data,
                    header,
                    null);

            ArticleContentBO articleContentBO = new ArticleContentBO();
            articleContentBO.setId(Long.parseLong(data));
            articleContentBO.setText(content);

            articleContentService.insert(articleContentBO);
            articleTableDao.ableById(entity.getId(), EntityAble.ENABLE.getNumber());
            System.out.println(++curr + "/" + size + ":" + JSON.toJSONString(articleContentBO));
            Thread.sleep(random.nextInt(10) * 50);
        }
    }
}
