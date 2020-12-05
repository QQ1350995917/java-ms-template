package pwd.initializr.email.business;

import java.security.Security;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import org.apache.commons.lang.StringUtils;
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
public abstract class EmailClientAbs {

  private Boolean debug;
  private String mailServerHost;
  private String mailServerPort;
  private String mailServerProtocol;
  private Boolean mailServerAuth;
  private String mailServerUser;
  private String mailServerPassword;

  protected EmailClientAbs() {
    super();
  }

  protected EmailClientAbs(Boolean debug, String mailServerHost, String mailServerPort,
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

  protected void destroyTransport() throws Exception {
    if (transport != null && transport.isConnected()) {
      transport.close();
    }
  }

  protected Transport getTransport() throws Exception {
    if (this.transport == null) {
      synchronized (EmailClientAbs.class) {
        if (this.transport == null) {

          this.transport = getSession().getTransport();
          // 连上邮件服务器
          this.transport.connect(this.mailServerHost,this.mailServerUser,this.mailServerPassword);
        }
      }
    }
    return this.transport;
  }

  protected Session getSession() {
    if (this.session == null) {
      synchronized (EmailClientAbs.class) {
        if (this.session == null) {
          Properties env = System.getProperties();
          // 1、创建session
          Properties prop = new Properties();
          prop.setProperty("mail.debug", this.debug == null ? "false":this.debug.toString());
          prop.setProperty("mail.host", this.mailServerHost);
          if (StringUtils.isNotBlank(this.mailServerPort)){
            prop.setProperty("mail.port", this.mailServerPort);
          }
          prop.setProperty("mail.transport.protocol", this.mailServerProtocol);
          prop.setProperty("mail.smtp.auth", this.mailServerAuth == null ? "false":this.mailServerAuth.toString());
          this.session = Session.getInstance(prop);
        }
      }
    }
    return this.session;
  }

}
