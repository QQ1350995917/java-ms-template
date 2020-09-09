package pwd.initializr.automatic.business.mysql.project;

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
public class Springboot {


    public static void main(String[] args) throws Exception {
        ProjectBO projectBO = new ProjectBO();
        List<ProjectFile> projectFiles = new LinkedList<>();
        projectFiles.add(new ProjectPom(projectBO));
        projectFiles.add(new ProjectZip(projectBO));
        projectFiles.add(new ProjectStart(projectBO));
        projectFiles.add(new ProjectStop(projectBO));
        projectFiles.add(new SrcMainJavaPackageApplication(projectBO));
        projectFiles.add(new SrcMainResourcesApplication(projectBO));
        projectFiles.add(new SrcMainResourcesApplicationDev(projectBO));
        projectFiles.add(new SrcMainResourcesBanner(projectBO));
        projectFiles.add(new SrcMainJavaPackageSwagger(projectBO));

        projectFiles.forEach(obj -> {
            try {
                obj.createProjectFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
