package pwd.initializr.generator.business.mysql.database;

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
public class DataSourceBO {

    private String name = "initializr_account";
    private String url = "jdbc:mysql://localhost:3306/initializr_account";
    private String driver = "com.mysql.jdbc.Driver";
    private String user = "root";
    private String pwd = "root";

//    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/initializr_account";
//    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
//    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
}
