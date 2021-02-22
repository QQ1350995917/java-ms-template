package pwd.initializr.generator.business.mysql.database;


import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
public class DataSourceTableColumn extends DataSourceComponent {

  private String databaseName;
  private Set<String> tables;

  public DataSourceTableColumn(DataSourceBO config, String databaseName, Set<String> tables) {
    super(config);
    this.databaseName = databaseName;
    this.tables = tables;
  }

  @Override
  protected Set<String> getSqls() {
    Set<String> sqls = new HashSet<>();
    for (String table : tables) {
      String sql =
          "SELECT TABLE_NAME, COLUMN_NAME,DATA_TYPE,COLUMN_KEY,COLUMN_COMMENT,IS_NULLABLE,COLUMN_DEFAULT FROM information_schema.columns WHERE table_schema = '"
              + this.databaseName + "' and table_name = '" + table + "';";
      sqls.add(sql);
    }
    return sqls;
  }

  @Override
  protected Map<String, Object> getResult(ResultSet resultSet) throws Exception {
    Map<String, Object> result = new HashMap();
    while (resultSet.next()) {
      // 通过字段检索
      String tableName = resultSet.getString("TABLE_NAME");
      String columnName = resultSet.getString("COLUMN_NAME");
      String dataType = resultSet.getString("DATA_TYPE");
      String columnKey = resultSet.getString("COLUMN_KEY");
      String columnComment = resultSet.getString("COLUMN_COMMENT");
      String columnNullable = resultSet.getString("IS_NULLABLE");
      String columnDefault = resultSet.getString("COLUMN_DEFAULT");
      List<TableColumnBO> tableColumnBOS = (List<TableColumnBO>) result.get(tableName);
      if (tableColumnBOS == null) {
        tableColumnBOS = new LinkedList<>();
        result.put(tableName, tableColumnBOS);
      }
      tableColumnBOS.add(new TableColumnBO(columnName, dataType, columnComment,
          "PRI".equalsIgnoreCase(columnKey), columnNullable, columnDefault));
    }
    return result;
  }


}
