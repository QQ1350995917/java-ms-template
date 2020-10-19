package pwd.initializr.monitor.api.admin;

import io.swagger.annotations.Api;
import java.util.Properties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.monitor.api.admin.vo.JstOutput;

/**
 * pwd.initializr.monitor.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 18:44
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "服务器信息",
    value = "服务器信息",
    description = "[JST，JRT，FILE，USER]"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin/jvm")
public class JvmController extends AdminController implements JvmApi {

    private static final Properties PROPERTIES = System.getProperties();

    @Override
    public void jst() {
        JstOutput jstOutput = new JstOutput();
        jstOutput.setVersion(PROPERTIES.getProperty("java.version"));
        jstOutput.setVendor(PROPERTIES.getProperty("java.vendor"));
        jstOutput.setVendorUrl(PROPERTIES.getProperty("java.vendor.url"));
        jstOutput.setHome(PROPERTIES.getProperty("java.home"));
        jstOutput.setVmVersion(PROPERTIES.getProperty("java.vm.version"));
        jstOutput.setVmVendor(PROPERTIES.getProperty("java.vm.vendor"));
        jstOutput.setVmName(PROPERTIES.getProperty("java.vm.name"));
        jstOutput
            .setVmSpecificationVersion(PROPERTIES.getProperty("java.vm.specification.version"));
        jstOutput.setSpecificationVender(PROPERTIES.getProperty("java.specification.vender"));
        jstOutput.setVmSpecificationVendor(PROPERTIES.getProperty("java.vm.specification.vendor"));
        jstOutput.setVmSpecificationName(PROPERTIES.getProperty("java.vm.specification.name"));
        jstOutput.setSpecificationVersion(PROPERTIES.getProperty("java.specification.version"));
        jstOutput.setSpecificationName(PROPERTIES.getProperty("java.specification.name"));
        jstOutput.setClassVersion(PROPERTIES.getProperty("java.class.version"));
        jstOutput.setClassPath(PROPERTIES.getProperty("java.class.path"));
        jstOutput.setLibraryPath(PROPERTIES.getProperty("java.library.path"));
        jstOutput.setIoTmpdir(PROPERTIES.getProperty("java.io.tmpdir"));
        jstOutput.setExtDirs(PROPERTIES.getProperty("java.ext.dirs"));
        this.outputData(jstOutput);
    }

    @Override
    public void jrt() {
        Runtime r = Runtime.getRuntime();
        System.out.println("JVM可以使用的总内存:    " + r.totalMemory());
        System.out.println("JVM可以使用的剩余内存:    " + r.freeMemory());
        System.out.println("JVM可以使用的处理器个数:    " + r.availableProcessors());
    }

    @Override
    public void file() {
        System.out.println("文件分隔符：    " + PROPERTIES.getProperty("file.separator"));
        System.out.println("路径分隔符：    " + PROPERTIES.getProperty("path.separator"));
        System.out.println("行分隔符：    " + PROPERTIES.getProperty("line.separator"));
    }

    @Override
    public void user() {
        System.out.println("用户的账户名称：    " + PROPERTIES.getProperty("user.name"));
        System.out.println("用户的主目录：    " + PROPERTIES.getProperty("user.home"));
        System.out.println("用户的当前工作目录：    " + PROPERTIES.getProperty("user.dir"));
    }

    public static void main(String[] args) {
        JvmController jvmController = new JvmController();
        jvmController.jrt();
    }
}
