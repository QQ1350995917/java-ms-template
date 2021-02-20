package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.List;
import java.util.Map;
import pwd.initializr.common.utils.StringUtils;
import pwd.initializr.generator.business.mysql.database.TableColumnBO;

/**
 * pwd.initializr.generator.business.mysql.architecture@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-20 16:18
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainResourcesTemplatesHtml extends SrcMainResourcesTemplate {

    private String tableName;
    private String className;
    private List<TableColumnBO> tableColumnBOList;
    public SrcMainResourcesTemplatesHtml(ProjectBO projectBO, String tableName,
        String className,
        List<TableColumnBO> tableColumnBOList) {
        super(projectBO);
        this.filePath += File.separator + "static";
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
        return "mysql/src/main/resources/templates/static/template.html.ftl";
    }

    @Override
    protected String getResourceName(){
        return StringUtils.toLowerCaseFirstLetter(this.className) + ".html";
    }


}

