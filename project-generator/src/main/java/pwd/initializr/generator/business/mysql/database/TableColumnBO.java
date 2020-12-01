package pwd.initializr.generator.business.mysql.database;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import pwd.initializr.generator.util.VariableName;

/**
 * pwd.initializr.generator.business.mysql.database@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-10 10:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TableColumnBO {

    private String jdbcName;
    private String javaName;
    private String jdbcType;
    private String javaType;
    private String mybatisType;
    private String comment;
    private Boolean key;
    private String nullable;
    private String defaultValue;

    public TableColumnBO(String jdbcName, String jdbcType, String comment, Boolean key,String nullable,String defaultValue) {
        this.jdbcName = jdbcName;
        this.jdbcType = jdbcType;
        this.comment = comment;
        this.key = key;
        this.nullable = nullable;
        this.defaultValue = defaultValue;
    }

    public String getJavaName() {
        return VariableName.underlineToHump(this.getJdbcName());
    }


    public String getMybatisType() {
        return dabaseTypeToMybatisType(this.getJdbcType());
    }

    public String getJavaType() {
        return StringUtils.isBlank(dabaseTypeToJavaType(this.getJdbcType()))? "String":dabaseTypeToJavaType(this.getJdbcType());
    }

    protected String dabaseTypeToJavaType(String jdbcType) {
        jdbcType = jdbcType.toUpperCase();
        Map<String, String> typeMapping = new HashMap<>();
        typeMapping.put("CHAR", "String");
        typeMapping.put("VARCHAR", "String");
        typeMapping.put("TEXT", "String");
        typeMapping.put("TINYTEXT", "String");
        typeMapping.put("LONGTEXT", "String");
        typeMapping.put("MEDIUMTEXT", "String");
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
        return typeMapping.get(jdbcType);
    }

    protected String dabaseTypeToMybatisType(String type) {
        type = type.toUpperCase();
        Map<String, String> typeMapping = new HashMap<>();
        typeMapping.put("INT", "INTEGER");
        typeMapping.put("DATETIME", "TIMESTAMP");
        return typeMapping.get(type) == null ? type : typeMapping.get(type);
    }
}
