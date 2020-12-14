package pwd.initializr.email.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

/**
 * <h2>email_box数据表实体类</h2>
 * date 2020-12-14 16:13
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EmailBoxEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键 主键
     */
    private Long id;
    /**
     * email表外键
     */
    private Long emailId;
    /**
     * 邮箱类型，0：发件箱，1：收件箱，2：抄送邮箱，3：密送邮箱
     */
    private Integer type;
    /**
     * 邮箱地址
     */
    private String mailbox;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EmailBoxEntity)) {
            return false;
        }
        EmailBoxEntity entity = (EmailBoxEntity) obj;
        if (StringUtils.equalsIgnoreCase(this.getMailbox(), entity.getMailbox())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (StringUtils.isBlank(this.getMailbox())) {
            return super.hashCode();
        }
        return this.getMailbox().hashCode();
    }
}
