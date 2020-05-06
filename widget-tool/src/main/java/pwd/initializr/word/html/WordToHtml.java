package pwd.initializr.word.html;

import java.io.File;
import java.util.Set;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

/**
 * pwd.initializr.word.html@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 17:18
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class WordToHtml {

  public static void main(String[] args) throws Throwable {
    final String file = "C:\\Users\\Administrator\\Desktop\\20200320.docx";
    DocumentConverter converter = new DocumentConverter();
    Result<String> result = converter.convertToHtml(new File(file));
    String html = result.getValue(); // The generated HTML
    Set<String> warnings = result.getWarnings(); // Any
  }


}
