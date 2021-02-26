package pwd.initializr.search.test.business;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
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

  private static final String indexName = "book";

  @Autowired
  private DocumentService documentService;

  @Test
  public void create() {
    LinkedList<DocumentBO> documentBOS = new LinkedList<>();
    documentBOS.add(
        new DocumentBO(
            "1",
            "1",
            "xxx",
            indexName,
            Arrays.asList("xxxxxx").stream().collect(Collectors.toSet()),
            Arrays.asList("xxxxxx"),"http://www.xxx.com","20200506")
    );

    documentBOS.add(
        new DocumentBO(
            "2",
            "1",
            "三国演义",
            indexName,
            Arrays.asList("罗贯中").stream().collect(Collectors.toSet()),
            Arrays.asList("xxx三国演义xxx","xxx三国xxx"),"http://www.sanguoyanyi.com","20200501")
    );
    documentBOS.add(
        new DocumentBO(
            "3",
            "1",
            "水浒传",
            indexName,
            Arrays.asList("施耐庵").stream().collect(Collectors.toSet()),
            Arrays.asList("xxx水浒传xxx","xxx水浒xxx"),"http://www.shuihuzhuan.com","20200502")
    );
    documentBOS.add(
        new DocumentBO(
            "4",
            "1",
            "水浒",
            indexName,
            Arrays.asList("施耐庵").stream().collect(Collectors.toSet()),
            Arrays.asList("xxx水浒xxx","xxx水浒xxx"),"http://www.shuihuzhuan.com","20200502")
    );
    documentBOS.add(
        new DocumentBO(
            "5",
            "1",
            "西游记",
            indexName,
            Arrays.asList("吴承恩").stream().collect(Collectors.toSet()),
            Arrays.asList("xxx西游记xxx","xxx西游记xxx"),"http://www.xiyouji.com","20200503")
    );
    documentBOS.add(
        new DocumentBO(
            "6",
            "1",
            "红楼梦",
            indexName,
            Arrays.asList("曹雪芹").stream().collect(Collectors.toSet()),
            Arrays.asList("xxx红楼梦xxx","xxx红楼xxx"),"http://www.hongloumeng.com","20200504")
    );
    documentBOS.add(
        new DocumentBO(
            "7",
            "1",
            "四大名著",
            indexName,
            Arrays.asList("田丁").stream().collect(Collectors.toSet()),
            Arrays.asList("xxx三国演义xxx","xxx水浒传xxx","xxx西游记xxx","xxx红楼梦xxx"),"http://www.sidamingzhu.com","20200505"))
    ;
    documentBOS.add(
        new DocumentBO(
            "8",
            "1",
            "民国传说中的东北颜值最高文武双全拥有佛缘名字慈灯出身社会底层用笔尖抗争疾呼的男神他把目光聚焦于揭露社会深层穷苦弱小百姓和傀儡军队等真实的惨相与阴暗无情腐败贪婪充满尔虞我诈的混沌世界里以暗讽隐晦的手法大量书写揭露日本侵略者在东北建立的伪满洲国以及鼓吹的王道乐土五族协和大东亚共荣圈的虚假中生活在殖民统治下水深火热中被欺压受苦受难却不甘亡国的穷苦百姓发出的呐喊控诉和愤争中的文章看似他远离政治却与时事从未脱离的现实主义勾勒的笔触都带着一种饱含深意的消解和形式上与独裁统治者对抗下的作品虽然短小精悍往往只写一个小人物或一件小事聚焦生活的小片段犹如挂在日本侵华罪恶史墙上的每一帧照片都有一副苦难而扭曲的面孔在诉说现实的苦难里反映出最痛苦最挣扎最屈辱最无尊严最令人心碎最激发人们抗争呐喊和最展示人性强大与弱点彷徨与深思的矛盾心理涵盖了勾心斗角的小职员精打细算的家庭妇女赤膊的脚夫奔跑街头的人力车夫争斗打拼的码头工人受人白眼的茶馆仆役奸诈势利的当铺掌柜低俗可悲的街头妓女贪图小利的食肆小二嘶哑叫卖的报童泼皮无赖的流氓打家劫舍的强盗欺压百姓的军阀勾结胡子的地主等社会下最底层最草根最可怜最屈辱最色厉内荏却无可奈何但又热爱生活的各类人群和最腐败最贪婪最无趣最奸诈最卑躬屈膝和昧着良心背叛生活的那些人的故事令他的作品有血有肉有哭有笑更多是他的草民经历眼见所闻切身感受和观察到的社会众生相使这位东北沦陷期的传奇人物由一个深受大众欢迎的百姓作家转变为受东北左联进步思想的影响毅然投笔从戎参加革命改名夏园在对敌斗争中还坚持不懈进行创作的他出身贫寒一生传奇做过苦工服务过傀儡军队参加过抗联当过地下党站过讲台做过中央领导秘书下放过农村 1930年至1945年15年间作品竟达千万字讲述着伪满洲国那片地域那个时空中不为人知的故事",
            indexName,
            Arrays.asList("夏园","杨慈灯","最长的书名").stream().collect(Collectors.toSet()),
            Arrays.asList(
                "现在的吉尼斯世界纪录记载，世界上书名最长的一本书。",
                "这套书共分六卷，采用硬壳精装，设计相当精美，并且还附赠了一套藏书票(每本书各附一张)，藏书票图案分别为作者不同时代的头像。为了保证收藏价值，这套书只限量印刷了500套。",
                "这套书不光书名长、外观美，内容也不错，它是20世纪三、四十年代东北最出名的作家杨慈灯(笔名夏园)的作品，那时几乎在每天的报刊上都能读到他的作品，可以说在当时他是个家喻户晓的人物。",
                "文学界曾将他与沈从文并列，有“慈灯之在东北，恰如沈从文之在南方”之说。1945年他参加了地下党，投笔从戎，因此他的作品在1945年后就很少再见到了。后经过历次“运动”，他的作品被彻底埋没。",
                "直到近年来，在抢救中国文学史籍的工作中，他的作品又被重新发现了，并且已整理出达千万字之巨。杨慈灯的文学作品，包括小说、散文、传记、童话故事、诗歌等，数量巨大得惊人。现在根据文献史料发现，他的作品将近还有大半都未被发掘出来。",
                "近三年来，多家出版社相继出版了杨慈灯的多部作品，国家出版基金也为他的作品出版给予了资助。他的作品重新出世，在文学界引起了极大的反响，受到了普遍的欢迎。为此，贵州人民出版社以他在被“下放”到贵州期间所用的笔名“夏园”出版了这套书，作为对他的纪念。",
                "作者简介　杨慈灯，年轻时曾是东北知名作家，上世纪四十年代共创作了11部中长篇小说。后来在党的指引下参加了革命。1946年在北平与姜德明、金野、陈思、戈更、吕平、王化兆等一批志同道合的青年共同创办了红色报纸《鲁迅晚报》，连续发表毛主席的《新民主主义论》、《论联合政府》等文章。为躲避国民党特务的追捕，更名为夏园，后被组织安排到解放区工作。文革时期遭受迫害，直到文革结束平反、离休、病逝。"
            ),"http://culture.gog.cn/system/2017/08/23/016027361.shtml","20070731"))
    ;
    documentService.replace(indexName, documentBOS);
  }

  @Test
  public void search(){
    SearchInputBO searchInputBO = new SearchInputBO();
    searchInputBO.setIndex(0);
    searchInputBO.setSize(12);
    searchInputBO.setKeyword("水浒传");
    searchInputBO.setIndices(Arrays.asList("book"));
//    searchInputBO.setIndices(Arrays.asList("*"));
    PageableQueryResult<DocumentBO> searchBodyVOBOPageableQueryResult = documentService.search(searchInputBO);
    log.info(JSON.toJSONString(searchBodyVOBOPageableQueryResult));
  }

}
