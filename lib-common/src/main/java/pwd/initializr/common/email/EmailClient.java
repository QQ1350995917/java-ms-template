package pwd.initializr.common.email;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.lang.StringUtils;

/**
 * pwd.initializr.common.email@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 12:29
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class EmailClient {

    private Boolean debug;
    private String mailServerHost;
    private String mailServerPort;
    private String mailServerProtocol;
    private Boolean mailServerAuth;
    private String mailServerUser;
    private String mailServerPassword;
    private Session session;
    private Transport transport;

    public EmailClient(Boolean debug, String mailServerHost, String mailServerPort,
        String mailServerProtocol, Boolean mailServerAuth, String mailServerUser,
        String mailServerPassword) {
        this.debug = debug;
        this.mailServerHost = mailServerHost;
        this.mailServerPort = mailServerPort;
        this.mailServerProtocol = mailServerProtocol;
        this.mailServerAuth = mailServerAuth;
        this.mailServerUser = mailServerUser;
        this.mailServerPassword = mailServerPassword;
    }

    private Address[] buildAddress(Set<String> mailbox) throws Exception {
        if (mailbox != null && !mailbox.isEmpty()) {
            Address[] addresses = new InternetAddress[mailbox.size()];
            Iterator<String> iterator = mailbox.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                String next = iterator.next();
                addresses[i] = new InternetAddress(next);
                i++;
            }
            return addresses;
        } else {
            return new InternetAddress[0];
        }
    }

    /**
     * <h2>发送邮件</h2>
     * date 2020-07-28 16:09
     *
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    public void send(Email email) throws Exception {
        // 创建邮件
        MimeMessage message = new MimeMessage(getSession());
        // 设置发件人
        message.setFrom(new InternetAddress(email.getFrom()));
        // 设置收件人
        Set<String> tos = email.getTos();
        message.setRecipients(Message.RecipientType.TO, buildAddress(tos));
        // 设置抄送人
        Set<String> ccs = email.getCcs();
        if (ccs != null && !ccs.isEmpty()) {
            message.setRecipients(Message.RecipientType.CC, buildAddress(ccs));
        }
        // 设置密送人
        Set<String> bccs = email.getBccs();
        if (bccs != null && !bccs.isEmpty()) {
            message.setRecipients(Message.RecipientType.BCC, buildAddress(bccs));
        }
        // 设置标题
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
                    DataHandler dataHandler = new DataHandler(
                        new ByteArrayDataSource(attachment.getBytes(),
                            attachment.getContentType()));
                    attachmentMimeBodyPart.setDataHandler(dataHandler);
                    attachmentMimeBodyPart.setContentID(attachment.getCid());
                    attachmentMimeBodyPart
                        .setFileName(MimeUtility.encodeText(attachment.getFileName()));
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

    public void destroyTransport() {
        try {
            if (transport != null && transport.isConnected()) {
                transport.close();
                transport = null;
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    protected Transport getTransport() throws Exception {
        if (this.transport == null) {
            synchronized (EmailClient.class) {
                if (this.transport == null) {

                    this.transport = getSession().getTransport();
                    // 连上邮件服务器
                    this.transport
                        .connect(this.mailServerHost, this.mailServerUser, this.mailServerPassword);
                }
            }
        }
        return this.transport;
    }

    protected Session getSession() {
        if (this.session == null) {
            synchronized (EmailClient.class) {
                if (this.session == null) {
                    Properties env = System.getProperties();
                    // 1、创建session
                    Properties prop = new Properties();
                    prop.setProperty("mail.debug",
                        this.debug == null ? "false" : this.debug.toString());
                    prop.setProperty("mail.smtp.host", this.mailServerHost);
                    if (StringUtils.isNotBlank(this.mailServerPort)) {
                        prop.setProperty("mail.port", this.mailServerPort);
                    }
//          prop.put("mail.smtp.ssl.enable", "true");
                    prop.put("mail.smtp.timeout", "3000");
                    prop.setProperty("mail.transport.protocol", this.mailServerProtocol);
                    prop.setProperty("mail.smtp.auth",
                        this.mailServerAuth == null ? "false" : this.mailServerAuth.toString());
                    this.session = Session.getInstance(prop);
                }
            }
        }
        return this.session;
    }

}
