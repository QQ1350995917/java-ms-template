package pwd.initializr.edu.test.business.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.edu.business.WordCollectionService;
import pwd.initializr.edu.business.WordTableService;
import pwd.initializr.edu.business.bo.WordCollectionBO;
import pwd.initializr.edu.business.bo.WordTableBO;
import pwd.initializr.edu.persistence.dao.WordCollectionDao;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-16 16:08
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCollectionServiceTest {

  @Autowired
  private WordTableService wordTableService;
  @Autowired
  private WordCollectionService wordCollectionService;
  @Resource
  private WordCollectionDao dao;

  @Test
  public void collect() {
    LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
    ScopeBO scopeBO = new ScopeBO();
    scopeBO.setHit("EM");
    scopeBO.setFieldName("leaf");
    scopeBO.setFieldValue("1");

    ScopeBO scopeBO1 = new ScopeBO();
    scopeBO1.setHit("EM");
    scopeBO1.setFieldName("able");
    scopeBO1.setFieldValue("0");

    scopeBOS.add(scopeBO);
    scopeBOS.add(scopeBO1);

    PageableQueryResult<WordTableBO> wordTableBOPageableQueryResult = wordTableService
        .queryAllByCondition(scopeBOS, null, 0L, Long.MAX_VALUE);
    List<WordTableBO> elements = wordTableBOPageableQueryResult.getElements();

    int counter = 1;
    for (WordTableBO element : elements) {
      int total = 0;
      int currentSize = 0;
      do {
        HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("Connection", "keep-alive");
        header.put("Cookie", SpriderConfig.cookie);
        header.put("Host", "47.92.155.170");
        header.put("Referer", "http://47.92.155.170/Web/AiWord/index.html");
        header.put("User-Agent", SpriderConfig.UserAgent);
        header.put("X-Requested-With", "XMLHttpRequest");

        String content = httpXByHttpClient
            .get("http://47.92.155.170/Web/AiWord/ajaxNewWord/jiaocaiid/" + element.getId() + ".html?limit=50&offset=" + currentSize + "&order=asc", header,
                null);

        List<WordCollectionBO> wordCollectionBOS = JSON
            .parseObject(content).getObject("rows",new TypeReference<List<WordCollectionBO>>(){});
        total = JSON.parseObject(content).getInteger("total");
        currentSize = currentSize + wordCollectionBOS.size();

        for (WordCollectionBO wordCollectionBO : wordCollectionBOS) {
          LinkedHashSet<ScopeBO> subScopeBOS = new LinkedHashSet<>();
          ScopeBO subScopeBO = new ScopeBO();
          subScopeBO.setHit("EM");
          subScopeBO.setFieldName("id");
          subScopeBO.setFieldValue(String.valueOf(wordCollectionBO.getId()));

          ScopeBO subScopeBO1 = new ScopeBO();
          subScopeBO1.setHit("EM");
          subScopeBO1.setFieldName("book_id");
          subScopeBO1.setFieldValue(wordCollectionBO.getBookId());

          subScopeBOS.add(subScopeBO);
          subScopeBOS.add(subScopeBO1);

          PageableQueryResult<WordCollectionBO> wordCollectionBOPageableQueryResult = wordCollectionService
              .queryAllByCondition(subScopeBOS, null, 0L, 1L);
          if (wordCollectionBOPageableQueryResult != null
              && wordCollectionBOPageableQueryResult.getSize() != null
              && wordCollectionBOPageableQueryResult.getSize() > 0) {
            log.error("leaf progress -> " + element.getId() + " -> " + wordCollectionBO.getId() + " repeat");
          } else {
            wordCollectionService.insert(wordCollectionBO);
          }
        }

        log.info("leaf progress -> " + element.getId() + ": " + currentSize + "/" + total);

      } while (currentSize < total);

      wordTableService.ableById(element.getId(),EntityAble.ENABLE);

      log.info("leaf progress: " + counter + "/" + elements.size());
      counter ++;
    }

  }

  @Test
  public void collectMp3() {
    long start = System.currentTimeMillis();
    boolean run = true;
    int readSize = 0;
    int writeSize = 0;
    int errorSize = 0;
    while (run) {
      LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
      ScopeBO scopeBO1 = new ScopeBO();
      scopeBO1.setHit("EM");
      scopeBO1.setFieldName("able");
      scopeBO1.setFieldValue("0");

      scopeBOS.add(scopeBO1);

      PageableQueryResult<WordCollectionBO> wordTableBOPageableQueryResult = wordCollectionService
          .queryAllByCondition(scopeBOS, null, 0L, 50L);
      List<WordCollectionBO> elements = wordTableBOPageableQueryResult.getElements();


      if (elements != null && elements.size() > 0) {
        readSize = readSize + elements.size();
        for (WordCollectionBO element : elements) {
          try {
            HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
            HashMap<String, String> header = new HashMap<>();
            header.put("Host", "tsn.baidu.com:443");
            header.put("User-Agent", SpriderConfig.UserAgent);
            header.put("Proxy-Connection","keep-alive");
            header.put("Connection", "keep-alive");

            String enRadio = element.getEnRadio();
            String localPath = enRadio.replace("http://document.i-wins.com//","/Users/pwd/Documents/edu/word-collection/");
            File file = new File(localPath);
            if (file.exists()) {
              dao.ableByIdAndBookId(element.getId(),element.getBookId(),-2,new Date());
              continue;
            }

            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
              parentFile.mkdirs();
            }
            String url =  "https://tsn.baidu.com/text2audio?tex=" + element.getEnWord() + "&lan=zh&cuid=12332521521661&ctp=1&per=0&spd=5&vol=15&tok=24.e970c9d3adb24a28ae771a8b079e664c.2592000.1614518877.282335-14754235";
            httpXByHttpClient.getFile(url, header, file);
            dao.ableByIdAndBookId(element.getId(),element.getBookId(),1,new Date());
            writeSize ++;
          } catch (Exception e) {
            e.printStackTrace();
            dao.ableByIdAndBookId(element.getId(),element.getBookId(),-1,new Date());
            errorSize ++;
          }
        }
        log.info("readSize = " + readSize);
        log.info("writeSize = " + writeSize);
        log.info("errorSize = " + errorSize);
      } else {
        run = false;
      }
    }
    log.info("耗时" + ((System.currentTimeMillis() - start) / 1000)/60);

  }

  public static void main(String[] args) {
      HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
      HashMap<String, String> header = new HashMap<>();
      header.put("Host", "tsn.baidu.com:443");
      header.put("User-Agent", SpriderConfig.UserAgent);
      header.put("Proxy-Connection","keep-alive");
      header.put("Connection", "keep-alive");


      String url =  "https://tsn.baidu.com/text2audio?tex=hello&lan=zh&cuid=12332521521661&ctp=1&per=0&spd=5&vol=15&tok=24.e970c9d3adb24a28ae771a8b079e664c.2592000.1614518877.282335-14754235";
      httpXByHttpClient.getFile(url, header, new File ("/Users/pwd/Documents/edu/word-collection/hello.mp3"));
  }

}
