package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.List;
import java.util.Map;
import pwd.initializr.generator.business.mysql.database.TableColumnBO;

/**
 * pwd.initializr.automatic.business.mysql.project@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-09 10:37
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainResourcesMapper extends SrcMainResources {

    private String tableName;
    private String className;
    private List<TableColumnBO> tableColumnBOList;
    public SrcMainResourcesMapper(ProjectBO projectBO, String tableName,
        String className,
        List<TableColumnBO> tableColumnBOList) {
        super(projectBO);
        this.filePath += File.separator + "mapper";
        this.tableName = tableName;
        this.className = className;
        this.tableColumnBOList = tableColumnBOList;
    }
    @Override
    protected Map<String, Object> getData() {
        this.data.put("className", this.className);
        this.data.put("tableName", this.tableName);
        this.data.put("columns", tableColumnBOList);
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/resources/mapper/mapper.xml.ftl";
    }

    @Override
    protected String getResourceName(){
        return className + "Dao.xml";
    }

}
