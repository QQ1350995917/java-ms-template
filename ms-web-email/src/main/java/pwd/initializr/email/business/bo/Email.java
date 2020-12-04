package pwd.initializr.email.business.bo;

import java.util.LinkedList;

/**
 * pwd.initializr.email.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-04 16:04
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface Email {

    String getFrom();

    String getTo();

    String getCc();

    String getBcc();

    String getSubject();

    String getHtml();

    LinkedList<Attachment> getAttachments();

    interface Attachment {

        String getCid();

        String getContentType();

        String getUrl();
    }
}
