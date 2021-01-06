package pwd.initializr.gateway.api.vo;

import java.io.Serializable;
import java.util.List;
import javax.sql.rowset.serial.SerialArray;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.gateway.business.filter.SessionBO;

/**
 * pwd.initializr.gateway.api.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-30 23:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SessionWhiteOutput implements Serializable {

    private Integer version;
    private List<SessionWhiteVO> sessionWhiteList;

}
