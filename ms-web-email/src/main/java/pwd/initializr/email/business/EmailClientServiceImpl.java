package pwd.initializr.email.business;

import java.util.LinkedList;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.email.business.bo.Email;
import pwd.initializr.email.business.bo.Email.Attachment;

/**
 * (EmailEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
@Service("emailClientService")
public class EmailClientServiceImpl extends EmailClientService {

  @Override
  public void send(Email email) throws Exception {
    // 创建邮件
    MimeMessage message = new MimeMessage(getSession());
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

    LinkedList<Attachment> attachments = email.getAttachments();
    if (attachments != null && !attachments.isEmpty()) {
      attachments.forEach(attachment -> {
        try {
          MimeBodyPart mimeBodyPart = new MimeBodyPart();
          DataHandler dataHandler = new DataHandler(new FileDataSource(attachment.getUrl()));
//        DataHandler dataHandler = new DataHandler(new URLDataSource(URI.create(attachment.getUrl()).toURL()));
          mimeBodyPart.setDataHandler(dataHandler);
          mimeBodyPart.setContentID(attachment.getCid());
          mimeBodyPart.attachFile(attachment.getUrl());
          mimeMultipart.addBodyPart(mimeBodyPart);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }

    mimeMultipart.setSubType("related");
    message.setContent(mimeMultipart);
    message.saveChanges();
    // 发送邮件
    this.getTransport().sendMessage(message, message.getAllRecipients());
  }
}
