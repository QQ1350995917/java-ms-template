package pwd.initializr.email.business;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pwd.initializr.email.business.bo.Email;

/**
 * pwd.initializr.account@ms-web-initializr
 *
 * <h1>邮件客户端服务接口</h1>
 *
 * date 2019-09-13 22:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public abstract class EmailClientService {

  private static final String EMAIL_SERVER_HOST = "email.server.host";
  private static final String EMAIL_SERVER_PASSWORD = "email.server.password";
  private static final String EMAIL_SERVER_USER = "email.server.user";

  @Value("{email.server.host}")
  private String mailServerHost;
  @Value("{email.server.user}")
  private String mailServerUser;
  @Value("{email.server.password}")
  private String mailServerPassword;

  private Session session;
  private Transport transport;

  /**
   * <h2>发送邮件</h2>
   * date 2020-07-28 16:09
   *
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public abstract void send(Email email) throws Exception;

  protected void destoryTransport() throws Exception {
    if (transport != null && transport.isConnected()) {
      transport.close();
    }
  }

  protected Transport getTransport() throws Exception {
    if (this.transport == null) {
      synchronized (EmailClientService.class) {
        if (this.transport == null) {
          Properties env = System.getProperties();
          this.transport = getSession().getTransport();
          // 连上邮件服务器
          this.transport.connect(
              env.getProperty(EMAIL_SERVER_HOST, mailServerHost),
              env.getProperty(EMAIL_SERVER_USER, mailServerUser),
              env.getProperty(EMAIL_SERVER_PASSWORD, mailServerPassword)
          );
        }
      }
    }
    return this.transport;
  }

  protected Session getSession() {
    if (this.session == null) {
      synchronized (EmailClientService.class) {
        if (this.session == null) {
          Properties env = System.getProperties();
          // 1、创建session
          Properties prop = new Properties();
          prop.setProperty("mail.debug", "true");
          prop.setProperty("mail.host", env.getProperty(EMAIL_SERVER_HOST, mailServerHost));
          prop.setProperty("mail.transport.protocol", "smtp");
          prop.setProperty("mail.smtp.auth", "true");
          this.session = Session.getInstance(prop);
        }
      }
    }
    return this.session;
  }

}
