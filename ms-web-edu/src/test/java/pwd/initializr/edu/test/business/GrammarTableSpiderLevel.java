package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
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
import pwd.initializr.edu.business.GrammarBookService;
import pwd.initializr.edu.business.bo.GrammarBookBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-15 09:09
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GrammarTableSpiderLevel {

  @Autowired
  private GrammarBookService grammarBookService;

  @Test
  public void level1Grade() throws Exception {

    GrammarBookBO bookBO0 = new GrammarBookBO();
    bookBO0.setId(Long.MAX_VALUE);
    bookBO0.setPid(0L);
    bookBO0.setLeaf(0);
    bookBO0.setName("小学-小升初通用版");
    bookBO0.setData("4028b4816460a6da016460b2548e0004");

    grammarBookService.insert(bookBO0);

    GrammarBookBO bookBO1 = new GrammarBookBO();
    bookBO1.setId(Long.MAX_VALUE - 1);
    bookBO1.setPid(0L);
    bookBO1.setLeaf(0);
    bookBO1.setName("初中-中考通用版");
    bookBO1.setData("4028b4816460a6da016460b186140003");

    grammarBookService.insert(bookBO1);

    GrammarBookBO bookBO2 = new GrammarBookBO();
    bookBO2.setId(Long.MAX_VALUE - 2);
    bookBO2.setPid(0L);
    bookBO2.setLeaf(0);
    bookBO2.setName("高中-高中通用版");
    bookBO2.setData("402880e66507589b0165076d65780005");

    grammarBookService.insert(bookBO2);

  }

  @Test
  public void level2JuniorMiddleSchool() throws Exception {
    String json = "{\"info\":{\"list\":[{\"id\":\"17\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u4e00\\u7ae0\\u3001\\u540d\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"1\",\"zz_id\":\"80\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"18\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u4e8c\\u7ae0\\u3001\\u4ee3\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"2\",\"zz_id\":\"81\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"19\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u4e09\\u7ae0\\u3001\\u6570\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"3\",\"zz_id\":\"82\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"20\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u56db\\u7ae0\\u3001\\u4ecb\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"4\",\"zz_id\":\"83\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"21\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u4e94\\u7ae0\\u3001\\u8fde\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"5\",\"zz_id\":\"84\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"22\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u516d\\u7ae0\\u3001\\u5f62\\u5bb9\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"6\",\"zz_id\":\"85\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"23\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u4e03\\u7ae0\\u3001\\u526f\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"7\",\"zz_id\":\"86\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"24\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u516b\\u7ae0\\u3001\\u51a0\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"8\",\"zz_id\":\"87\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"25\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u4e5d\\u7ae0\\u3001\\u60c5\\u6001\\u52a8\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"9\",\"zz_id\":\"88\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"26\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u7ae0\\u3001\\u975e\\u8c13\\u8bed\\u52a8\\u8bcd\",\"edition_id\":\"2\",\"paixu\":\"10\",\"zz_id\":\"89\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"27\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e00\\u7ae0\\u3001\\u65f6\\u6001\",\"edition_id\":\"2\",\"paixu\":\"11\",\"zz_id\":\"90\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"28\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e8c\\u7ae0\\u3001\\u88ab\\u52a8\\u8bed\\u6001\",\"edition_id\":\"2\",\"paixu\":\"12\",\"zz_id\":\"91\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"29\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e09\\u7ae0\\u3001\\u53e5\\u5b50\\u79cd\\u7c7b\",\"edition_id\":\"2\",\"paixu\":\"13\",\"zz_id\":\"92\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"30\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u56db\\u7ae0\\u3001\\u4e3b\\u8c13\\u4e00\\u81f4\\u4e0e\\u5012\\u88c5\\u53e5\",\"edition_id\":\"2\",\"paixu\":\"14\",\"zz_id\":\"93\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"31\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e94\\u7ae0\\u3001\\u5bbe\\u8bed\\u4ece\\u53e5\",\"edition_id\":\"2\",\"paixu\":\"15\",\"zz_id\":\"94\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"32\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u516d\\u7ae0\\u3001\\u5b9a\\u8bed\\u4ece\\u53e5\",\"edition_id\":\"2\",\"paixu\":\"16\",\"zz_id\":\"95\",\"edu_level\":\"4028b4816460a6da016460b186140003\"},{\"id\":\"33\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e03\\u7ae0\\u3001\\u72b6\\u8bed\\u4ece\\u53e5\",\"edition_id\":\"2\",\"paixu\":\"17\",\"zz_id\":\"96\",\"edu_level\":\"4028b4816460a6da016460b186140003\"}],\"type\":\"edition\"},\"status\":1,\"url\":\"\"}";

    List<GrammarBookBO> grammarBookBOList = JSON.parseObject(json).getJSONObject("info")
        .getObject("list", new TypeReference<List<GrammarBookBO>>() {
        });

    for (GrammarBookBO bookBO : grammarBookBOList) {
      bookBO.setPid(Long.MAX_VALUE - 1);
      bookBO.setLeaf(0);
      bookBO.setName(bookBO.getBookName());
      bookBO.setData(bookBO.getEditionId());
      bookBO.setOrder(bookBO.getPaixu());
      String unescapeJava = StringEscapeUtils.unescapeJava(bookBO.getName());
      bookBO.setName(unescapeJava);
      grammarBookService.insert(bookBO);
    }

  }

  @Test
  public void level2PrimarySchool() throws Exception {
    String json = "{\"info\":{\"list\":[{\"id\":\"1\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u4e00\\\\u7ae0 \\\\u51a0\\\\u8bcd\",\"edition_id\":\"1\",\"paixu\":\"1\",\"zz_id\":\"64\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"2\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u4e8c\\\\u7ae0 \\\\u540d\\\\u8bcd\",\"edition_id\":\"1\",\"paixu\":\"2\",\"zz_id\":\"65\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"3\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u4e09\\\\u7ae0 \\\\u6570\\\\u8bcd\",\"edition_id\":\"1\",\"paixu\":\"3\",\"zz_id\":\"66\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"4\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u56db\\\\u7ae0 \\\\u8fde\\\\u8bcd\",\"edition_id\":\"1\",\"paixu\":\"4\",\"zz_id\":\"67\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"5\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u4e94\\\\u7ae0 \\\\u4ee3\\\\u8bcd\",\"edition_id\":\"1\",\"paixu\":\"5\",\"zz_id\":\"68\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"6\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u516d\\\\u7ae0 \\\\u4ecb\\\\u8bcd\",\"edition_id\":\"1\",\"paixu\":\"6\",\"zz_id\":\"69\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"7\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u4e03\\\\u7ae0 \\\\u5f62\\\\u5bb9\\\\u8bcd\",\"edition_id\":\"1\",\"paixu\":\"7\",\"zz_id\":\"70\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"8\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u516b\\\\u7ae0 \\\\u526f\\\\u8bcd\",\"edition_id\":\"1\",\"paixu\":\"8\",\"zz_id\":\"71\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"9\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u4e5d\\\\u7ae0 \\\\u4e00\\\\u822c\\\\u73b0\\\\u5728\\\\u65f6\",\"edition_id\":\"1\",\"paixu\":\"9\",\"zz_id\":\"72\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"10\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u5341\\\\u7ae0 \\\\u73b0\\\\u5728\\\\u8fdb\\\\u884c\\\\u65f6\",\"edition_id\":\"1\",\"paixu\":\"10\",\"zz_id\":\"73\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"11\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u5341\\\\u4e00\\\\u7ae0 \\\\u4e00\\\\u822c\\\\u5c06\\\\u6765\\\\u65f6\",\"edition_id\":\"1\",\"paixu\":\"11\",\"zz_id\":\"74\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"12\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u5341\\\\u4e8c\\\\u7ae0 \\\\u4e00\\\\u822c\\\\u8fc7\\\\u53bb\\\\u65f6\",\"edition_id\":\"1\",\"paixu\":\"12\",\"zz_id\":\"75\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"13\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u5341\\\\u4e09\\\\u7ae0 \\\\u7b80\\\\u5355\\\\u53e5\\\\u57fa\\\\u672c\\\\u53e5\\\\u578b\",\"edition_id\":\"1\",\"paixu\":\"13\",\"zz_id\":\"76\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"14\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u5341\\\\u56db\\\\u7ae0 \\\\u7948\\\\u4f7f\\\\u53e5\",\"edition_id\":\"1\",\"paixu\":\"14\",\"zz_id\":\"77\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"15\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u5341\\\\u4e94\\\\u7ae0 \\\\u611f\\\\u53f9\\\\u53e5\",\"edition_id\":\"1\",\"paixu\":\"15\",\"zz_id\":\"78\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"},{\"id\":\"16\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\\\u7b2c\\\\u5341\\\\u516d\\\\u7ae0 There be \\\\u53e5\\\\u578b\",\"edition_id\":\"1\",\"paixu\":\"16\",\"zz_id\":\"79\",\"edu_level\":\"4028b4816460a6da016460b2548e0004\"}],\"type\":\"edition\"},\"status\":1,\"url\":\"\"}";

    List<GrammarBookBO> grammarBookBOList = JSON.parseObject(json).getJSONObject("info")
        .getObject("list", new TypeReference<List<GrammarBookBO>>() {
        });

    for (GrammarBookBO bookBO : grammarBookBOList) {
      bookBO.setPid(Long.MAX_VALUE);
      bookBO.setLeaf(0);
      bookBO.setName(bookBO.getBookName());
      bookBO.setData(bookBO.getEditionId());
      bookBO.setOrder(bookBO.getPaixu());
      String unescapeJava = StringEscapeUtils.unescapeJava(bookBO.getName());
      bookBO.setName(unescapeJava);
      grammarBookService.insert(bookBO);
    }

  }

  @Test
  public void level2SeniorMiddleSchool() throws Exception {
    String json = "{\"info\":{\"list\":[{\"id\":\"34\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u4e5d\\u7ae0 \\u65f6\\u6001\",\"edition_id\":\"3\",\"paixu\":\"1\",\"zz_id\":\"53\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"35\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u7ae0 \\u88ab\\u52a8\\u8bed\\u6001\",\"edition_id\":\"3\",\"paixu\":\"2\",\"zz_id\":\"54\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"36\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e00\\u7ae0 \\u52a8\\u8bcd\\u7684\\u975e\\u8c13\\u8bed\\u5f62\\u5f0f\",\"edition_id\":\"3\",\"paixu\":\"3\",\"zz_id\":\"55\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"37\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e8c\\u7ae0 \\u53e5\\u5b50\\u6210\\u5206\",\"edition_id\":\"3\",\"paixu\":\"4\",\"zz_id\":\"56\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"38\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e09\\u7ae0 \\u53e5\\u5b50\\u79cd\\u7c7b\",\"edition_id\":\"3\",\"paixu\":\"5\",\"zz_id\":\"57\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"39\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u56db\\u7ae0 \\u7b80\\u5355\\u53e5\",\"edition_id\":\"3\",\"paixu\":\"6\",\"zz_id\":\"58\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"40\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e94\\u7ae0 \\u5e76\\u5217\\u53e5\",\"edition_id\":\"3\",\"paixu\":\"7\",\"zz_id\":\"59\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"41\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u516d\\u7ae0 \\u5b9a\\u8bed\\u4ece\\u53e5\",\"edition_id\":\"3\",\"paixu\":\"8\",\"zz_id\":\"60\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"42\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u4e03\\u7ae0 \\u540d\\u8bcd\\u6027\\u4ece\\u53e5\",\"edition_id\":\"3\",\"paixu\":\"9\",\"zz_id\":\"61\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"43\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u5341\\u516b\\u7ae0 \\u72b6\\u8bed\\u4ece\\u53e5\",\"edition_id\":\"3\",\"paixu\":\"10\",\"zz_id\":\"62\",\"edu_level\":\"402880e66507589b0165076d65780005\"},{\"id\":\"44\",\"create_name\":null,\"create_by\":null,\"create_date\":null,\"update_name\":null,\"update_by\":null,\"update_date\":null,\"sys_org_code\":null,\"sys_company_code\":null,\"bpm_status\":\"1\",\"book_name\":\"\\u7b2c\\u4e8c\\u5341\\u7ae0 \\u7279\\u6b8a\\u53e5\\u5f0f\",\"edition_id\":\"3\",\"paixu\":\"11\",\"zz_id\":\"63\",\"edu_level\":\"402880e66507589b0165076d65780005\"}],\"type\":\"edition\"},\"status\":1,\"url\":\"\"}";

    List<GrammarBookBO> grammarBookBOList = JSON.parseObject(json).getJSONObject("info")
        .getObject("list", new TypeReference<List<GrammarBookBO>>() {
        });

    for (GrammarBookBO bookBO : grammarBookBOList) {
      bookBO.setPid(Long.MAX_VALUE - 2);
      bookBO.setLeaf(0);
      bookBO.setName(bookBO.getBookName());
      bookBO.setData(bookBO.getEditionId());
      bookBO.setOrder(bookBO.getPaixu());
      String unescapeJava = StringEscapeUtils.unescapeJava(bookBO.getName());
      bookBO.setName(unescapeJava);
      grammarBookService.insert(bookBO);
    }

  }


  @Test
  public void level3() throws Exception {
    System.setProperty("http.proxyHost", "127.0.0.1");
    System.setProperty("http.proxyPort", "8888");


    LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
    ScopeBO scopeBO1 = new ScopeBO();
    scopeBO1.setHit("EM");
    scopeBO1.setFieldName("able");
    scopeBO1.setFieldValue(String.valueOf(EntityAble.DISABLE.getNumber()));

    ScopeBO scopeBO2 = new ScopeBO();
    scopeBO2.setHit("EM");
    scopeBO2.setFieldName("leaf");
    scopeBO2.setFieldValue("0");

    ScopeBO scopeBO3 = new ScopeBO();
    scopeBO3.setHit("ENM");
    scopeBO3.setFieldName("pid");
    scopeBO3.setFieldValue("0");

    scopeBOS.add(scopeBO1);
    scopeBOS.add(scopeBO2);
    scopeBOS.add(scopeBO3);

    PageableQueryResult<GrammarBookBO> grammarBookBOPageableQueryResult = grammarBookService
        .queryAllByCondition(scopeBOS, null, 0L, Long.MAX_VALUE);

    if (grammarBookBOPageableQueryResult.getTotal() != 44) {
      throw new RuntimeException("not 44 item");
    }
    List<GrammarBookBO> elements = grammarBookBOPageableQueryResult.getElements();
    for (GrammarBookBO element : elements) {
      HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
      HashMap<String, String> header = new HashMap<>();
      header.put("Accept", "application/json, text/javascript, */*; q=0.01");
      header.put("Accept-Encoding", "gzip, deflate");
      header.put("Accept-Language", "en-US,en;q=0.5");
      header.put("Connection", "keep-alive");
      header.put("Cookie", SpriderConfig.cookie);
      header.put("Host", "47.92.155.170");
      header.put("Referer",
          "http://47.92.155.170/Web/AiGrammar/grammar_index.html");
      header.put("User-Agent",
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:85.0) Gecko/20100101 Firefox/85.0");
      header.put("X-Requested-With", "XMLHttpRequest");

      String content = httpXByHttpClient
          .get("http://47.92.155.170/Web/AiGrammar/grammar_index.html?id=" + element.getId() + "&type=book", header,
              null);

      List<GrammarBookBO> grammarBookBOList = JSON.parseObject(content).getJSONObject("info")
          .getObject("list", new TypeReference<List<GrammarBookBO>>() {
          });

      for (GrammarBookBO bookBO : grammarBookBOList) {
        bookBO.setPid(element.getId());
        bookBO.setLeaf(1);
        bookBO.setName(bookBO.getUnitName());
        bookBO.setData(bookBO.getBookId());
        bookBO.setOrder(bookBO.getPaixu());
        String unescapeJava = StringEscapeUtils.unescapeJava(bookBO.getName());
        bookBO.setName(unescapeJava);
        System.out.println(bookBO);
        grammarBookService.insert(bookBO);
      }
    }
  }


}
