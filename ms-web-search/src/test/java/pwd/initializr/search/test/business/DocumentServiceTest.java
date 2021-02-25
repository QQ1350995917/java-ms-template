package pwd.initializr.search.test.business;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.DocumentService;
import pwd.initializr.search.business.bo.DocumentBO;
import pwd.initializr.search.business.bo.SearchInputBO;

/**
 * pwd.initializr.search.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 23:28
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DocumentServiceTest {

  @Autowired
  private DocumentService documentService;

  @Test
  public void create() {
    LinkedList<DocumentBO> documentBOS = new LinkedList<>();
    documentBOS.add(new DocumentBO("2","1","aaa",Arrays.asList("xxxxxx"),"http://www.xxx.com","20200506",0L));
    documentBOS.add(new DocumentBO("1","1","三国演义",Arrays.asList("xxx三国演义xxx"),"http://www.sanguoyanyi.com","20200501",0L));
    documentBOS.add(new DocumentBO("1","1","水浒传",Arrays.asList("xxx水浒传xxx"),"http://www.shuihuzhuan.com","20200502",0L));
    documentBOS.add(new DocumentBO("1","1","水浒",Arrays.asList("xxx水浒xxx"),"http://www.shuihuzhuan.com","20200502",0L));
    documentBOS.add(new DocumentBO("1","1","西游记",Arrays.asList("xxx西游记xxx"),"http://www.xiyouji.com","20200503",0L));
    documentBOS.add(new DocumentBO("1","1","红楼梦",Arrays.asList("xxx红楼梦xxx"),"http://www.hongloumeng.com","20200504",0L));
    documentBOS.add(new DocumentBO("1","1","四大名著",Arrays.asList("xxx三国演义xxx，xxx水浒传xxx，xxx西游记xxx，xxx红楼梦xxx"),"http://www.sidamingzhu.com","20200505",0L));
    documentService.replace("book", documentBOS);
  }

  @Test
  public void search(){
    SearchInputBO searchInputBO = new SearchInputBO();
    searchInputBO.setIndex(0);
    searchInputBO.setSize(12);
    searchInputBO.setKeyword("水浒传");
    searchInputBO.setIndices(Arrays.asList("book"));
    PageableQueryResult<DocumentBO> searchBodyVOBOPageableQueryResult = documentService.search(searchInputBO);
    log.info(JSON.toJSONString(searchBodyVOBOPageableQueryResult));
  }

}
