package pwd.initializr.book.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;

/**
 * pwd.initializr.book.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-03 00:27
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HanLPTest {

  public static void main(String[] args) {
    Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
    Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
    String[] testCase = new String[]{
        "9787229124410",
        "2017-07-01",
        "重庆出版社",
        "2020-05-03 12:45:41",
        "邪乎到家必有鬼",
        "中国科幻基石",
    };
    for (String sentence : testCase)
    {
      System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
    }
  }
}
