package pwd.initializr.book.test;

import java.io.IOException;
import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * pwd.initializr.book.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-05 00:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class IKTest {
  public static void main(String[] args) throws IOException {
    String text="基于java语言开发的轻量级的中文分词工具包";
    //创建分词对象
    Analyzer anal=new IKAnalyzer(true);
    StringReader reader=new StringReader(text);
    //分词
    TokenStream ts=anal.tokenStream("", reader);
    CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
    //遍历分词数据
    ts.reset();
    while(ts.incrementToken()){
      System.out.print(term.toString()+"|");
    }
    reader.close();
    System.out.println();
  }


}
