package pwd.initializr.common.email;

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
import javax.mail.internet.MimeUtility;

/**
 * pwd.initializr.common.email@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 12:29
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class EmailHelper {
  public static void main(String[] args) throws Exception{

    Properties props = new Properties();//key value:配置参数。真正发送邮件时再配置

    props.setProperty("mail.transport.protocol", "smtp");//指定邮件发送的协议，参数是规范规定的
//        props.setProperty("mail.debug", "true");//邮件发送的调试模式，参数是规范规定的
    props.setProperty("mail.smtp.auth", "true");//请求服务器进行身份认证。参数与具体的JavaMail实现有关
    props.setProperty("mail.smtp.host", "smtp.163.com");//请求服务器进行身份认证。参数与具体的JavaMail实现有关

    Session session = Session.getInstance(props);//发送邮件时使用的环境配置
    Transport transport = session.getTransport();

        session.setDebug(true);
    MimeMessage message = new MimeMessage(session);
    //设置收件人
    message.setRecipient(Message.RecipientType.TO, new InternetAddress("www.dingpengwei@foxmail.com"));
    //设置抄送人
//    message.setRecipient(Message.RecipientType.CC, new InternetAddress(""));

    //设置邮件的头
    message.setFrom(new InternetAddress("initializr@163.com"));
    message.setSubject("这是一封复杂的邮件");
    //设置正文

    //搞出文本部分
    MimeBodyPart textPart = new MimeBodyPart();
    textPart.setContent("美女<img src='cid:mm'/>aaa", "text/html;charset=UTF-8");

    //搞图片部分
    MimeBodyPart imagePart = new MimeBodyPart();
    imagePart.setContentID("mm");
    //把磁盘上的文件加到part中使用到了JAF框架
    DataHandler dh = new DataHandler(new FileDataSource("tomcat.png"));
    imagePart.setDataHandler(dh);

    MimeMultipart mp = new MimeMultipart();
    mp.addBodyPart(textPart);
    mp.addBodyPart(imagePart);
    mp.setSubType("related");//有关系的

    MimeBodyPart textImagePart = new MimeBodyPart();    //将 MimeMultipart 添加到 MimeBodyPart实现附件的发送
    textImagePart.setContent(mp);

    //创建附件部分
    MimeBodyPart attachmentPart = new MimeBodyPart();
    dh = new DataHandler(new FileDataSource("pom.xml"));
    String filename = dh.getName();
    attachmentPart.setDataHandler(dh);

    //手工设置文件名  防止乱码使用  javaMail里的 MimeUtility进行编码
    attachmentPart.setFileName(MimeUtility.encodeText(filename));

    //最终的 MimeMultipart
    MimeMultipart finalMp = new MimeMultipart();
    finalMp.addBodyPart(attachmentPart);
    finalMp.addBodyPart(textImagePart);

    finalMp.setSubType("mixed");

    message.setContent(finalMp);
    message.saveChanges();

    //发送邮件
    Transport ts = session.getTransport();
    ts.connect("initializr@163.com", ""); //密码为授权码不是邮箱的登录密码
    ts.sendMessage(message, message.getAllRecipients());//对象，用实例方法

  }
}
