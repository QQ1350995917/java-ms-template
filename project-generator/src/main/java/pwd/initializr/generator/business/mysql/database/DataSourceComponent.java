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

  public Map<String, Object> exec() throws ClassNotFoundException, SQLException {
    Map<String, Object> result = new HashMap();
    Connection connection = getConnection();
    Statement statement = null;
    try {
      statement = connection.createStatement();
      Set<String> sqls = getSqls();
      for (String sql : sqls) {
        ResultSet resultSet = statement.executeQuery(sql);
        result.putAll(getResult(resultSet));
        resultSet.close();
      }
      statement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close(connection, statement);
    }
    return result;
  }

  protected Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName(this.config.getDriver());
    return DriverManager
        .getConnection(this.config.getUrl(), this.config.getUser(), this.config.getPwd());
  }

  protected abstract Set<String> getSqls();

  protected abstract Map<String, Object> getResult(ResultSet resultSet) throws Exception;

  protected void close(Connection connection, Statement statement) {
    this.close(connection, statement, null);
  }

  protected void close(Connection connection, Statement statement, ResultSet resultSet) {
    try {
      if (resultSet != null && !resultSet.isClosed()) {
        resultSet.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (statement != null && !statement.isClosed()) {
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void close(Connection connection) {
    this.close(connection, null);
  }

}
