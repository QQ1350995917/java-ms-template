package pwd.initializr.account.test.test;

import java.io.BufferedReader;
import java.io.FileReader;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * pwd.initializr.account.test.persistence@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-02 17:04
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class InitSQLTest {

    static HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();

    static {
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    public static void main(String[] args) {

        String file = "E:\\workspace\\github\\ms-web-initializr\\ms-web-account\\src\\test\\java\\pwd\\initializr\\account\\test\\test\\list.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().equals("魏") || line.trim().equals("蜀") || line.trim().equals("吴")) {
                    continue;
                }
                String[] s = line.split(" ");
                String userName = s[0];
                StringBuilder pinyinBuilder = new StringBuilder();
                for (int i = 0; i < userName.length(); i++) {
                    String[] pinyin = PinyinHelper
                        .toHanyuPinyinStringArray(userName.charAt(i), hanyuPinyinOutputFormat);
                    pinyinBuilder.append(pinyin[0]);
                }
//                System.out.print(userName + " = " + pinyinBuilder.toString());
                StringBuilder summaryBuilder = new StringBuilder();
                for (int i = 4; i < s.length; i++) {
                    summaryBuilder.append(s[i] + " ");
                }
                System.out.println(summaryBuilder.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
