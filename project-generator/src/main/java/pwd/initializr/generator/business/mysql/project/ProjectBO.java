package pwd.initializr.generator.business.mysql.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class ProjectBO {
    private String exportDir = "E:\\workspace\\github\\ms-web-initializr";
    private String packageName = "me.taoqigui.automatic";
    private String projectName = "project-generator-test";
    private String projectVersion = "0.0.1-SNAPSHOT";
    private String applicationName = "AutomaticTestApplication";
    private int projectPort = 80;
    private String databaseName = "initializr_account";
    private String databaseUrl = "jdbc:mysql://localhost:3306/initializr_account?characterEncoding=utf-8&autoReconnect=true&serverTimezone=GMT";
    private String databaseUser = "root";
    private String databasePwd = "root";
    private String databaseDriver = "com.mysql.jdbc.Driver";
}
