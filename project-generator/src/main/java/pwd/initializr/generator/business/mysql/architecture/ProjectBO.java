package pwd.initializr.generator.business.mysql.architecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.generator.business.mysql.database.DataSourceBO;
import pwd.initializr.generator.business.mysql.database.DatabaseBoot;

/**
 * pwd.initializr.automatic.business.mysql.project@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-09 10:26
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectBO extends DataSourceBO {

    private String exportDir;
    private String packageName = "me.taoqigui.automatic";
    private String projectName = "project-generator-test";
    private String projectVersion = "0.0.1-SNAPSHOT";
    private String applicationName = "AutomaticTestApplication";
    private int projectPort = 80;
}