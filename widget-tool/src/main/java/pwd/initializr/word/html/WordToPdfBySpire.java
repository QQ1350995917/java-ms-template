package pwd.initializr.word.html;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

/**
 * pwd.initializr.word.html@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-31 10:56
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class WordToPdfBySpire {

  public static void main(String[] args) {
    //加载Word测试文档


    Document doc = new com.spire.doc.Document();

    doc.loadFromFile("C:\\Users\\Administrator\\Desktop\\自动报告\\xxx.docx");

    //保存为PDF格式的文件

    doc.saveToFile("C:\\Users\\Administrator\\Desktop\\自动报告\\Word转PDF-0.pdf", FileFormat.PDF);

    doc.close();
  }
}
