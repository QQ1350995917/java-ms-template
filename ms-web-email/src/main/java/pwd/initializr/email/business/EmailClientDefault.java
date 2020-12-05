package pwd.initializr.email.business;

import java.util.LinkedList;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.lang.StringUtils;
import pwd.initializr.email.business.bo.Email;
import pwd.initializr.email.business.bo.EmailAttachment;

/**
 * (EmailEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
public class EmailClientDefault extends EmailClientAbs {


  private EmailClientDefault() {
  }

  private EmailClientDefault(Boolean debug, String mailServerHost, String mailServerPort,
    String mailServerProtocol, Boolean mailServerAuth, String mailServerUser, String mailServerPassword) {
    super(debug, mailServerHost, mailServerPort, mailServerProtocol, mailServerAuth,
        mailServerUser, mailServerPassword);
  }

  private static EmailClientDefault emailClientDefault;
  public static EmailClientDefault getInstance(Boolean debug, String mailServerHost, String mailServerPort,
    String mailServerProtocol, Boolean mailServerAuth, String mailServerUser, String mailServerPassword){
    if (emailClientDefault == null) {
        synchronized (EmailClientDefault.class) {
            if (emailClientDefault == null) {
                emailClientDefault = new EmailClientDefault(debug,mailServerHost,mailServerPort,mailServerProtocol,mailServerAuth,mailServerUser,mailServerPassword);
            }
        }
    }
    return emailClientDefault;
  }

  public static EmailClientDefault getInstance(String mailServerHost, String mailServerPort,
      String mailServerProtocol, Boolean mailServerAuth, String mailServerUser, String mailServerPassword){
      if (emailClientDefault == null) {
          synchronized (EmailClientDefault.class) {
              if (emailClientDefault == null) {
                  emailClientDefault = new EmailClientDefault(false,mailServerHost,mailServerPort,mailServerProtocol,mailServerAuth,mailServerUser,mailServerPassword);
              }
          }
      }
      return emailClientDefault;
  }

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

    MimeBodyPart mainMimeBodyPart = new MimeBodyPart();
    mainMimeBodyPart.addHeader("Content-Type", "text/html; charset=UTF-8");
    mainMimeBodyPart.setContent(email.getContent(), "text/html;charset=UTF-8");
    mimeMultipart.addBodyPart(mainMimeBodyPart);

    LinkedList<EmailAttachment> attachments = email.getAttachments();
    if (attachments != null && !attachments.isEmpty()) {
      attachments.forEach(attachment -> {
        try {
          MimeBodyPart attachmentMimeBodyPart = new MimeBodyPart();
          DataHandler dataHandler = new DataHandler(new ByteArrayDataSource(attachment.getBytes(),attachment.getContentType()));
          attachmentMimeBodyPart.setDataHandler(dataHandler);
          attachmentMimeBodyPart.setContentID(attachment.getCid());
          attachmentMimeBodyPart.setFileName(MimeUtility.encodeText(attachment.getFileName()));
          mimeMultipart.addBodyPart(attachmentMimeBodyPart);
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
