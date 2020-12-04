package pwd.initializr.email.business;

import java.util.LinkedList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pwd.initializr.email.business.bo.Email;
import pwd.initializr.email.business.bo.Email.Attachment;

/**
 * pwd.initializr.email.util@ms-web-initializr
 *
 * <h1>https://www.cnblogs.com/mashuqi/p/10449261.html</h1>
 *
 * date 2020-12-04 15:05
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class EmailClient {

    private static final String EMAIL_SERVER_HOST = "email.server.host";
    private static final String EMAIL_SERVER_USER = "email.server.user";
    private static final String EMAIL_SERVER_PASSWORD = "email.server.password";

    @Value("{email.server.host}")
    private String mailServerHost;
    @Value("{email.server.user}")
    private String mailServerUser;
    @Value("{email.server.password}")
    private String mailServerPassword;


    public void mail(Email email) throws Exception {
        long start = System.currentTimeMillis();
        Properties env = System.getProperties();
        // 1、创建session
        Properties prop = new Properties();
        prop.setProperty("mail.debug", "true");
        prop.setProperty("mail.host", env.getProperty(EMAIL_SERVER_HOST, mailServerHost));
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);
        // 2、通过session得到transport对象
        Transport transport = session.getTransport();
        // 3、连上邮件服务器
        transport.connect(
            env.getProperty(EMAIL_SERVER_HOST, mailServerHost),
            env.getProperty(EMAIL_SERVER_USER, mailServerUser),
            env.getProperty(EMAIL_SERVER_PASSWORD, mailServerPassword)
        );
        // 4、创建邮件
        MimeMessage message = new MimeMessage(session);
        // 邮件消息头
        message.setFrom(new InternetAddress(email.getFrom()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
        if (StringUtils.isNotBlank(email.getCc())) {
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(email.getCc()));
        }
        if (StringUtils.isNotBlank(email.getBcc())) {
            message.setRecipient(Message.RecipientType.BCC, new InternetAddress(email.getBcc()));
        }

        message.setSubject(email.getSubject());

        MimeMultipart mimeMultipart = new MimeMultipart();

        MimeBodyPart text = new MimeBodyPart();
        text.setContent(email.getHtml(), "text/html;charset=UTF-8");
        mimeMultipart.addBodyPart(text);

//        MimeBodyPart image = new MimeBodyPart();
//        DataHandler dh = new DataHandler(
//            new FileDataSource("C:\\Users\\Administrator\\Pictures\\4.jpg"));
//        image.setDataHandler(dh);
//        image.setContentID("abcx.jpg");


        LinkedList<Attachment> attachments = email.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            attachments.forEach(attachment -> {
                try {
                    MimeBodyPart mimeBodyPart = new MimeBodyPart();
                    DataHandler dataHandler = new DataHandler(new FileDataSource(attachment.getUrl()));
//                    DataHandler dataHandler = new DataHandler(new URLDataSource(URI.create(attachment.getUrl()).toURL()));
                    mimeBodyPart.setDataHandler(dataHandler);
                    mimeBodyPart.setContentID(attachment.getCid());
                    mimeBodyPart.attachFile(attachment.getUrl());
                    mimeMultipart.addBodyPart(mimeBodyPart);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
//        String[] files = {
//            "C:\\Users\\Administrator\\Pictures\\1.jpg",
//            "C:\\Users\\Administrator\\Pictures\\2.jpg",
//            "C:\\Users\\Administrator\\Pictures\\3.jpg"
//        };

        // 描述数据关系

//        mimeMultipart.addBodyPart(image);
        // 添加邮件附件
//        for (String filename : files) {
//            MimeBodyPart attachPart = new MimeBodyPart();
//            attachPart.attachFile(filename);
////            attachPart.setContent(filename,"text/html;charset=UTF-8");
//            mm.addBodyPart(attachPart);
//        }

        mimeMultipart.setSubType("related");
        message.setContent(mimeMultipart);
        message.saveChanges();
        // 5、发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

        System.out.println(System.currentTimeMillis() - start);
    }
}
