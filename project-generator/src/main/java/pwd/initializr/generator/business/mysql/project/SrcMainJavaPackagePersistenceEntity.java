package pwd.initializr.generator.business.mysql.project;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pwd.initializr.generator.business.mysql.database.TableColumnBO;
import pwd.initializr.generator.util.VariableName;

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
public class SrcMainJavaPackagePersistenceEntity extends SrcMainJavaPackage {

    private String tableName;
    private String className;
    private List<TableColumnBO> tableColumnBOList;

    public SrcMainJavaPackagePersistenceEntity(ProjectBO projectBO, String tableName,
        String className,
        List<TableColumnBO> tableColumnBOList) {
        super(projectBO);
        this.packagePath += File.separator + "persistence" + File.separator + "entity";
        this.tableName = tableName;
        this.className = className;
        this.tableColumnBOList = tableColumnBOList;
    }

    @Override
    protected Map<String, Object> getData() {
        this.data.put("className", this.className);
        this.data.put("tableName", this.tableName);
        for (TableColumnBO tableColumnBO : tableColumnBOList) {
            tableColumnBO.setName(VariableName.underlineToHump(tableColumnBO.getName()));
            tableColumnBO.setType(changeDabaseTypeToJavaType(tableColumnBO.getType()));
        }
        this.data.put("columns", tableColumnBOList);
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/java/Entity.java.ftl";
    }

    @Override
    protected String getClassName() {
        return className + "Entity";
    }

    protected String changeDabaseTypeToJavaType(String type) {
        type = type.toUpperCase();
        Map<String, String> typeMapping = new HashMap<>();
        typeMapping.put("CHAR", "String");
        typeMapping.put("VARCHAR", "String");
        typeMapping.put("LONGVARCHAR", "String");
        typeMapping.put("NUMERIC", "BigDecimal");
        typeMapping.put("DECIMAL", "BigDecimal");
        typeMapping.put("BIT", "Boolean");
        typeMapping.put("BOOLEAN", "Boolean");
        typeMapping.put("TINYINT", "Byte");
        typeMapping.put("SMALLINT", "Short");
        typeMapping.put("INT", "Integer");
        typeMapping.put("INTEGER", "Integer");
        typeMapping.put("BIGINT", "Long");
        typeMapping.put("REAL", "Float");
        typeMapping.put("FLOAT", "Double");
        typeMapping.put("DOUBLE", "Double");
        typeMapping.put("BINARY", "Byte[]");
        typeMapping.put("VARBINARY", "Byte[]");
        typeMapping.put("LONGVARBINARY", "Byte[]");
        typeMapping.put("DATE", "Date");
        typeMapping.put("TIME", "Date");
        typeMapping.put("DATETIME", "Date");
        typeMapping.put("TIMESTAMP", "Long");
        typeMapping.put("CLOB", "Clob");
        typeMapping.put("BLOB", "Blob");
        typeMapping.put("ARRAY", "Array");
        typeMapping.put("DISTINCT", "mapping of underlying type");
        typeMapping.put("STRUCT", "Struct");
        typeMapping.put("REF", "Ref");
        return typeMapping.get(type);
    }
}
