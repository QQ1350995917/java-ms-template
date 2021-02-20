package pwd.initializr.generator.business.mysql.architecture;

import java.util.LinkedList;
import java.util.List;

/**
 * pwd.initializr.automatic.business.mysql.project@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-08 18:39
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ArchitectureBoot {

    public static void main(String[] args) throws Exception {
        ProjectBO projectBO = new ProjectBO();
        ArchitectureBoot architectureBoot = new ArchitectureBoot();
        architectureBoot.generateProjectArchitecture(projectBO);
    }

    public void generateProjectArchitecture(ProjectBO projectBO) {
        List<ProjectFile> projectFiles = new LinkedList<>();
        projectFiles.add(new ProjectPom(projectBO));
        projectFiles.add(new ProjectZip(projectBO));
        projectFiles.add(new ProjectStart(projectBO));
        projectFiles.add(new ProjectStop(projectBO));

        // 处理跟目录-静态接口
        projectFiles.add(new SrcMainJavaPackageApplication(projectBO));

        // 处理资源层目录-静态接口
        projectFiles.add(new SrcMainResourcesApplication(projectBO));
        projectFiles.add(new SrcMainResourcesApplicationDev(projectBO));
        projectFiles.add(new SrcMainResourcesTemplatesCss(projectBO));
        projectFiles.add(new SrcMainResourcesTemplatesJs(projectBO));
        projectFiles.add(new SrcMainResourcesTemplatesMedia(projectBO));
        projectFiles.add(new SrcMainResourcesBanner(projectBO));

        // 处理API层目录-静态接口
        projectFiles.add(new SrcMainJavaPackageIndexApi(projectBO));
        projectFiles.add(new SrcMainJavaPackageIndexController(projectBO));
        projectFiles.add(new SrcMainJavaPackageApiSwagger2(projectBO));

        projectFiles.forEach(obj -> {
            try {
                obj.createProjectFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
