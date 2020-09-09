package pwd.initializr.automatic.business.mysql.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.automatic.business.mysql@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-08 18:08
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
public class DataSourceConfig {

    private String url;
    private String driver;
    private String username;
    private String password;
}
