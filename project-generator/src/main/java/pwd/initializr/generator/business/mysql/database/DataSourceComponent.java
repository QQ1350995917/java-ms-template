package pwd.initializr.generator.business.mysql.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * pwd.initializr.automatic.business.mysql@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-08 18:09
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class DataSourceComponent {

    private DataSourceBO config;

    public DataSourceComponent(DataSourceBO config) {
        this.config = config;
    }

    public Map<String, Object> exec() {
        Map<String, Object> result = new HashMap();
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(config.getDriver());
            connection = DriverManager
                .getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            statement = connection.createStatement();
            Set<String> sqls = getSqls();
            for (String sql : sqls) {
                ResultSet resultSet = statement.executeQuery(sql);
                result = getResult(resultSet);
                resultSet.close();
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return result;
    }

    protected abstract Map<String, Object> getResult(ResultSet resultSet) throws Exception;

    protected abstract Set<String> getSqls();


}
