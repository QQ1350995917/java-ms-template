package pwd.initializr.common.email;

import java.util.LinkedList;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Email {

    private String from;
    private Set<String> tos;
    private Set<String> ccs;
    private Set<String> bccs;
    private String subject;
    private String content;
    LinkedList<EmailAttachment> attachments;

}
