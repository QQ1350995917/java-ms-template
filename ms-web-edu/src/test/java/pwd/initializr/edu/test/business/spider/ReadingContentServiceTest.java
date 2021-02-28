package pwd.initializr.edu.test.business.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.edu.business.ReadingContentService;
import pwd.initializr.edu.business.ReadingTableService;
import pwd.initializr.edu.business.bo.ReadingContentBO;
import pwd.initializr.edu.business.bo.ReadingTableBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-18 10:52
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadingContentServiceTest {

    @Autowired
    private ReadingTableService readingTableService;
    @Autowired
    private ReadingContentService readingContentService;

    @org.junit.Test
    public void table1 () {

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

        PageableQueryResult<ReadingTableBO> readingTableBOPageableQueryResult = readingTableService
            .queryAllByCondition(scopeBOS, null, 0L, Long.MAX_VALUE);
        List<ReadingTableBO> elements = readingTableBOPageableQueryResult.getElements();
        for (ReadingTableBO element : elements) {
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

            //{"info":[{"id":"332","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:08","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c1\u7bc7","content":"  One day Mr  and Mrs  White go shopping by car\uff0eThey stop their car near a store\uff0eThey buy a lot of things and they want to put the things in the car\uff0eBut Mr  White can't open the door of the car, so they ask a policeman to help them\uff0eThe policeman is very friendly to help them\uff0eJust then a man comes up and shouts(\u5927\u58f0\u8bf4): \"What are you doing with my car\"?\n    Mr and Mrs White take a look at the car's number and they are frozen there\uff0eIt isn't their car\uff0e","edu_level":null,"level_id":"31","sort":"1","zuixiao":null,"zuida":null,"qus_count":"4","score":0,"open":1},{"id":"333","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:08","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c2\u7bc7","content":" My family is not very large\uff0eThere are only three cats\uff0eI'm a white cat\uff0ePeople call me Snow-white\uff0eMy husband is a black cat\uff0e People call him Captain Black Cat(\u9ed1\u732b\u8b66\u957f)\uff0eWe have a beautiful daughter\uff0eHer name is Lily\uff0eShe is black and white\uff0eShe's only two years old\uff0eWe live a happy life\uff0e\n    From now on, we will work harder to catch the mice\uff0eWe must let people live in a happy life in this house\uff0e\n","edu_level":null,"level_id":"31","sort":"2","zuixiao":null,"zuida":null,"qus_count":"5","score":0,"open":0},{"id":"334","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:08","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c3\u7bc7","content":"I have six baskets\uff0eThree are big\uff0eAnd three are small\uff0eI have some apples and oranges\uff0eI put three apples in each (\u6bcf\u4e2a) small basket, and I put three oranges in each big basket and one orange in each small basket\uff0eThe number of the oranges is my age (\u5e74\u9f84)\uff0e","edu_level":null,"level_id":"31","sort":"3","zuixiao":null,"zuida":null,"qus_count":"5","score":0,"open":0},{"id":"335","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:09","update_name":"xtgly1","update_by":"xtgly1","update_date":"2019-09-26 21:33:15","sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c4\u7bc7","content":" An old man died and left his son a lot of money\uff0eBut the son was a foolish(\u611a\u8822\u7684) young man, and he quickly spent all the money, so that soon he had nothing left\uff0eOf course, when that happened, all his friends left him\uff0eWhen he was quite poor and alone, he went to see Nasreddin, who was a kind, clever old man and often helped people when they had troubles(\u9ebb\u70e6)\uff0e\r\n    \"My money has finished and my friends have gone,\" said the young man\uff0e\"What will happen to me now\"?\r\n    \"Don't worry, young man,\" answered Nasreddin\uff0e\"Everything will soon be all right again\uff0eWait, and you will soon feel much happier\uff0e\"\r\nThe young man was very glad\uff0e\"Am I going to get rich again then\"? He asked Nasreddin\uff0e\r\n    \"No, I didn't mean that,\" said the old man\uff0e\"I meant that you would soon get used to being poor and to having no friends\uff0e\"\r\n","edu_level":"","level_id":"31","sort":"4","zuixiao":null,"zuida":null,"qus_count":"5","score":0,"open":0},{"id":"336","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:09","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c5\u7bc7","content":"One day Bob took two of his friends into the mountains\uff0eThey put up their tents (\u5e10\u7bf7) and then rode off to a forest to see how the trees were growing\uff0eIn the afternoon when they were about ten kilometers from their camp(\u8425\u5730), it started to snow\uff0eMore and more snow fell\uff0eSoon Bob could hardly see his hands before his face\uff0eHe could not find the road\uff0eBob knew there were two roads\uff0eOne road went to the camp, and the other went to his house\uff0eBut all was white snow\uff0eEverything was the same\uff0eHow could he take his friends back to the camp? Bob had an idea\uff0eThe horses! Let the horses take them back! But what would happen if the horses took the road to his house? That would be a trip of thirty-five kilometers in such cold weather! It was getting late\uff0eThey rode on(\u4e00\u76f4\u9a91) and on\uff0eAt last the horses stopped\uff0eWhere were they? None of them could tell\uff0eJohn looked around\uff08\u73af\u987e\u56db\u5468\uff09\uff0eWhat was that under the tree? It was one of their tents!","edu_level":null,"level_id":"31","sort":"5","zuixiao":null,"zuida":null,"qus_count":"5","score":0,"open":0},{"id":"337","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:10","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c6\u7bc7","content":"American boys and girls love to watch television\uff0eSome children spend six hours a day in school and four to six hours a day in front of the television set\uff0eSome even watch television for eight hours or more on Saturday\uff0eTelevisions are like books or films\uff0eA child can learn bad things and good things from them\uff0eSome programs(\u8282\u76ee) help children to understand (\u7406\u89e3)the news, others show people and places from other countries or other time in history\uff0eWith television a child does not have to go to the zoo to see animals to the ocean to see a ship\uff0eBoys and girls can see a play, a concert or a game at home\uff0eTelevision brings many places and events into our homes\uff0eSome programs show crime(\u72af\u7f6a) and other things that are bad for children, so parents sometimes help them to find other interesting things to do\uff0e","edu_level":null,"level_id":"31","sort":"6","zuixiao":null,"zuida":null,"qus_count":"5","score":0,"open":0},{"id":"338","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:10","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c7\u7bc7","content":"Last Sunday was a funny day\uff0eLiu Tao came to my home in the morning\uff0eWe studied English together\uff0eWe read some funny English stories\uff0eAt about ten o'clock, we went to Chennan Park on foot\uff0eAnd we fished there\uff0eIt was very interesting\uff0eWe cooked lunch at about 12 o'clock and it was very nice\uff0eThen we cleaned the kitchen\uff0eWe watched TV after lunch\uff0eAt about 4 o'clock in the afternoon, we played pingpong\uff0e","edu_level":null,"level_id":"31","sort":"7","zuixiao":null,"zuida":null,"qus_count":"5","score":0,"open":0},{"id":"339","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:10","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c8\u7bc7","content":" Jane is only four and doesn't go to school\uff0eBut she is very clever and learns a lot from her grandpa\u2014\u2014an old teacher\uff0eOne day, Jane's mother, Mrs&#46; John takes her to a party \uff0eThe guests all praise (\u8868\u626c) a rich woman's boy\uff0eMrs&#46; John asks him a few questions, but all his answers are wrong\uff0eJane begins to laugh(\u5632\u7b11) at him\uff0eThe rich woman is angry\uff0eShe tells her friend\u2014\u2014a teacher to ask Jane some questions, but the girl answers all\uff0eThen she asks, \"There're 3 birds on a tree , how many will be left(\u7559\u4e0b)if kill one\"?\"One\" answers Jane\uff0e\"You're wrong\"!calls out the rich woman\uff0e\"All of them will fly away\"!\"Do you think a dead bird can fly\"? asks the little girl\uff0eThe guests begin to laugh and the woman's face turns red\uff0e","edu_level":null,"level_id":"31","sort":"8","zuixiao":null,"zuida":null,"qus_count":"5","score":0,"open":0},{"id":"340","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:10","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c9\u7bc7","content":"Last Saturday morning, Lucy went to a supermarket\uff0eShe wanted to buy a big cake for her mother's birthday\uff0eThere were many people in the supermarket\uff0eSuddenly, Lucy saw a beautiful wallet on the floor\uff0e\"Whose wallet is it\"? She asked, but nobody answered\uff0e \"Excuse me, is this wallet yours, sir\" She asked the man in front of her\uff0e\"No, it isn't\uff0e\" The man said, \"Perhaps it's hers\uff0e\" He pointed the woman in front of him\uff0e\"Is this wallet yours? \"She asked the woman\uff0e\"Let me have a look\uff0eOh, yes, it is\uff0eThank you very much\uff0e\"","edu_level":null,"level_id":"31","sort":"9","zuixiao":null,"zuida":null,"qus_count":"4","score":0,"open":0},{"id":"341","create_name":null,"create_by":null,"create_date":"2019-01-24 09:46:11","update_name":null,"update_by":null,"update_date":null,"sys_org_code":null,"sys_company_code":null,"bpm_status":"1","reading_name":"\u7b2c10\u7bc7","content":" Tom and Fred are students\uff0eThey are both twelve years old, and they are in the same class in their school\uff0eOne day ,they have a fight(\u6253\u67b6)in class, and their teacher becomes very angry\uff0eShe says to both of the boys, \"Stay here after school this afternoon, and write your names a hundred times\uff0e\" After the last lesson, all the other students go home, but Tom and Fred stay in the classroom\uff0eThey begin to write their names in their exercise books and wait for their teacher\uff0eWhen the teacher comes, Fred is crying\uff0eThe teacher looks at him and says, \"Why are you crying\"?\"Because his name is Tom King, and my name is Frederick Hollingsworth,\" Fred says with tears in his eyes\uff0e\"It is too bad that I have a very long name\"!","edu_level":null,"level_id":"31","sort":"10","zuixiao":null,"zuida":null,"qus_count":"5","score":0,"open":0}],"status":1,"url":""}
            String content = httpXByHttpClient
                .get("http://47.92.155.170/Web/AiReading/ajaxGetBooks.html?aj_levelId=" + element.getId(), header, null);

            List<ReadingContentBO> readingContentBOS = JSON
                .parseObject(content).getObject("info",new TypeReference<List<ReadingContentBO>>(){});
            if (readingContentBOS == null) {
                System.out.println(element.getId());
                continue;
            }
            for (ReadingContentBO readingContentBO : readingContentBOS) {
                readingContentBO.setPid(element.getId());
                readingContentBO.setReadingName(StringEscapeUtils.unescapeJava(readingContentBO.getReadingName()));

                readingContentService.insert(readingContentBO);
            }
            readingTableService.ableById(element.getId(), EntityAble.ENABLE);
        }
    }
}

