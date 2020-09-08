package pwd.initializr.automatic.business.project;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * pwd.initializr.automatic.business.project@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-08 18:39
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Springboot {


  public static void main(String[] args) throws Exception {
    Configuration config = new Configuration(Configuration.VERSION_2_3_28);
    // 设置加载的目录
    config.setClassForTemplateLoading(Springboot.class, "/template");
    // 得到模板对象
    Template t = config.getTemplate("pom.xml.ftl");
    // 2.创建数据
    Map<String, Object> data = new HashMap<>();
    data.put("projectName", "automatic-test");
    data.put("projectVersion", "0.0.1-SNAPSHOT");
    data.put("projectPackage", "automatic.test");
    data.put("applicationName", "AutomaticTest");
    // 3.产生输出
    t.process(data, new OutputStreamWriter(System.out));
  }

}
