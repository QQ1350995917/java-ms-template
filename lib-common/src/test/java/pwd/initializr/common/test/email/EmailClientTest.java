package pwd.initializr.common.test.email;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Properties;
import pwd.initializr.common.email.Email;
import pwd.initializr.common.email.EmailAttachment;
import pwd.initializr.common.email.EmailClient;
import pwd.initializr.common.utils.DateTimeUtil;

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
public class EmailClientTest {

    // -Demail.server.user=xxx -Demail.server.password=xxx
    private static final String EMAIL_SERVER_PASSWORD = "email.server.password";
    private static final String EMAIL_SERVER_USER = "email.server.user";

    public static void main(String[] args) throws Exception {
        Properties env = System.getProperties();
        // smtp.exmail.qq.com(使用SSL，端口号465)
        String host = "smtp.exmail.qq.com";
        String port = "465";
        String protocol = "smtp";
        String user = env.getProperty(EMAIL_SERVER_USER, null);
        String password = env.getProperty(EMAIL_SERVER_PASSWORD, null);

        String current = DateTimeUtil.getCurrent();

        String subject = "hello word:" + current;
        String content =
            "<h1>Hello Word: " + current + " </h1><p>显示图片<img src='cid:a.png'>1.jpg</p>";

        LinkedList<EmailAttachment> attachments = new LinkedList<>();
        //String file1 = "/Users/pwd/Documents/minio/xresources/thumb/351.png";
        String file1 = "C:\\Users\\Administrator\\Pictures\\1.jpg";
        attachments.add(
            new EmailAttachment("快.jpg", "a", "image/jpeg", Files.readAllBytes(Paths.get(file1))));
        //String file2 = "/Users/pwd/Documents/minio/xresources/thumb/345.png";
        String file2 = "C:\\Users\\Administrator\\Pictures\\2.jpg";
        attachments.add(
            new EmailAttachment("必.jpg", "b", "image/jpeg", Files.readAllBytes(Paths.get(file2))));
        //String file3 = "/Users/pwd/Documents/minio/xresources/thumb/392.png";
        String file3 = "C:\\Users\\Administrator\\Pictures\\3.jpg";
        attachments.add(
            new EmailAttachment("达.jpg", "c", "image/jpeg", Files.readAllBytes(Paths.get(file3))));

        HashSet<String> tos = new HashSet<>();
        tos.add("www.dingpengwei@foxmail.com");
        EmailClient emailClient = new EmailClient(true, host, port, protocol, true, user, password);
        emailClient.send(new Email(user, tos, null, null, subject, content, attachments));
        emailClient.destroyTransport();
    }
}
