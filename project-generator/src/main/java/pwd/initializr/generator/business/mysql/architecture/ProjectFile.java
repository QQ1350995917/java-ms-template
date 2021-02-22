package pwd.initializr.generator.business.mysql.architecture;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * pwd.initializr.automatic.business.mysql.project@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-09 10:35
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class ProjectFile {


  protected static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
  static Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

  static {
    // 设置加载的目录
    configuration.setClassForTemplateLoading(ArchitectureBoot.class, "/templates");
  }

  protected ProjectBO projectBO;
  protected Map<String, Object> data = new LinkedHashMap<>();
  protected String fileDir;

  public ProjectFile(ProjectBO projectBO) {
    this.projectBO = projectBO;
    this.data.put("projectName", projectBO.getProjectName());
    this.data.put("projectVersion", projectBO.getProjectVersion());
    this.data.put("projectPackage", projectBO.getPackageName());
    this.data.put("applicationName", projectBO.getApplicationName());
    this.data.put("projectCreateDate", simpleDateFormat.format(new Date()));

    this.fileDir = projectBO.getExportDir() + File.separator + projectBO.getProjectDir();
  }

  public void createProjectFile() throws IOException, TemplateException {
    // 得到模板对象
    Template template = configuration.getTemplate(getTemplate());
    // 2.创建数据
    Map<String, Object> data = getData();
    // 3.产生输出
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
        new FileOutputStream(getOutputFile()));
    template.process(data, outputStreamWriter);
    outputStreamWriter.flush();
    outputStreamWriter.close();
  }

  protected abstract String getTemplate();

  protected abstract Map<String, Object> getData();

  protected abstract File getOutputFile() throws IOException;
}
