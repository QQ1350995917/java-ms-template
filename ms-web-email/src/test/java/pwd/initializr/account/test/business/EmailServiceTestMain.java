package pwd.initializr.account.test.business;

import java.util.LinkedList;
import pwd.initializr.email.business.EmailClient;
import pwd.initializr.email.business.bo.Email;
import pwd.initializr.email.business.bo.EmailBO;

/**
 * pwd.initializr.account.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-04 16:03
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class EmailServiceTestMain {

    public static void main(String[] args) throws Exception {
        new EmailClient().mail(new Email() {
            @Override
            public String getFrom() {
                return "dingpengwei@eversec.cn";
            }

            @Override
            public String getTo() {
                return "www.dingpengwei@foxmail.com";
            }

            @Override
            public String getCc() {
                return null;
            }

            @Override
            public String getBcc() {
                return null;
            }

            @Override
            public String getSubject() {
                return "hello word";
            }

            @Override
            public String getHtml() {
                return "<h1>Hello Word x x</h1>\" + \"<p>显示图片<img src='cid:abcx.jpg'>1.jpg</p>";
            }

            @Override
            public LinkedList<Attachment> getAttachments() {
                return null;
            }
        });

    }

}
