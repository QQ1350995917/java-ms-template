package pwd.initializr.account.test.business;

import java.util.LinkedList;
import pwd.initializr.email.business.EmailClientServiceImpl;
import pwd.initializr.email.business.bo.Email;

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
    new EmailClientServiceImpl().send(new Email() {
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
        return "<h1>Hello Word x x</h1>\" + \"<p>显示图片<img src='cid:a.png'>1.jpg</p>";
      }

      @Override
      public LinkedList<Attachment> getAttachments() {
        LinkedList<Attachment> attachments = new LinkedList<>();
        attachments.add(new Attachment() {
          @Override
          public String getCid() {
            return "a";
          }

          @Override
          public String getContentType() {
            return "image/jpeg";
          }

          @Override
          public String getUrl() {
            return "/Users/pwd/Documents/minio/xresources/thumb/351.png";
          }
        });
        attachments.add(new Attachment() {
          @Override
          public String getCid() {
            return "b";
          }

          @Override
          public String getContentType() {
            return "image/jpeg";
          }

          @Override
          public String getUrl() {
            return "/Users/pwd/Documents/minio/xresources/thumb/345.png";
          }
        });
        attachments.add(new Attachment() {
          @Override
          public String getCid() {
            return "c";
          }

          @Override
          public String getContentType() {
            return "image/jpeg";
          }

          @Override
          public String getUrl() {
            return "/Users/pwd/Documents/minio/xresources/thumb/392.png";
          }
        });

        return attachments;
      }
    });

  }

}
