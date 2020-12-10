package pwd.initializr.account.test.business;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Properties;
import pwd.initializr.common.utils.DateTimeUtil;
import pwd.initializr.email.business.EmailClientDefault;
import pwd.initializr.email.business.bo.Email;
import pwd.initializr.email.business.bo.EmailAttachment;

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
public class EmailServiceTestMain2 {

  public static void main(String[] args) throws Exception {
    Properties env = System.getProperties();
    // smtp.exmail.qq.com(使用SSL，端口号465)
    String host = "smtp.exmail.qq.com";
    String port = "465";
    String protocol = "smtp";
    String user = "eversec@eversec.cn";
    String password = "1qaz!QAZ";
    String to = "www.dingpengwei@foxmail.com";

    String current = DateTimeUtil.getCurrent();

    String subject = "hello word:" + current;
    String content = "Hello Word:" + current;
    EmailClientDefault.getInstance(true,host,port,protocol,true,user,password)
      .send(new Email(host,to,null,null,subject,content,null));

  }

}
