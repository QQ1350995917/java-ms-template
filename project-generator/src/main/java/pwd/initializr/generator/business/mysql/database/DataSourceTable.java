package pwd.initializr.generator.business.mysql.database;


import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
public class DataSourceTable extends DataSourceComponent {

    private String databaseName;

    public DataSourceTable(DataSourceBO config, String databaseName) {
        super(config);
        this.databaseName = databaseName;
    }

    @Override
    protected Map<String, Object> getResult(ResultSet resultSet) throws Exception {
        Map<String, Object> result = new HashMap();
        while (resultSet.next()) {
            // 通过字段检索
            String tableName = resultSet.getString("TABLE_NAME");
            List<String> tables = (List<String>) result.get("tables");
            if (tables == null) {
                tables = new LinkedList<>();
                result.put("tables", tables);
            }
            tables.add(tableName);
        }
        return result;
    }

    @Override
    protected Set<String> getSqls() {
        LinkedHashSet<String> sqls = new LinkedHashSet<>();
        String sql = "select TABLE_NAME from information_schema.columns where table_schema ='"
            + this.databaseName + "'  group by TABLE_NAME;";
        return sqls;
    }
}
