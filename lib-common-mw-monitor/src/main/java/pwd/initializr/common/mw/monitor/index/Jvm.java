package pwd.initializr.common.mw.monitor.index;

import java.util.Properties;
import pwd.initializr.monitor.rpc.RPCJst;

/**
 * pwd.initializr.common.mw.monitor.index@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-20 21:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Jvm {

  private static final Properties PROPERTIES = System.getProperties();

  public static RPCJst jst() {
    RPCJst rpcJst = new RPCJst();
    rpcJst.setVersion(PROPERTIES.getProperty("java.version"));
    rpcJst.setVendor(PROPERTIES.getProperty("java.vendor"));
    rpcJst.setVendorUrl(PROPERTIES.getProperty("java.vendor.url"));
    rpcJst.setHome(PROPERTIES.getProperty("java.home"));
    rpcJst.setVmVersion(PROPERTIES.getProperty("java.vm.version"));
    rpcJst.setVmVendor(PROPERTIES.getProperty("java.vm.vendor"));
    rpcJst.setVmName(PROPERTIES.getProperty("java.vm.name"));
    rpcJst.setVmSpecificationVersion(PROPERTIES.getProperty("java.vm.specification.version"));
    rpcJst.setSpecificationVender(PROPERTIES.getProperty("java.specification.vender"));
    rpcJst.setVmSpecificationVendor(PROPERTIES.getProperty("java.vm.specification.vendor"));
    rpcJst.setVmSpecificationName(PROPERTIES.getProperty("java.vm.specification.name"));
    rpcJst.setSpecificationVersion(PROPERTIES.getProperty("java.specification.version"));
    rpcJst.setSpecificationName(PROPERTIES.getProperty("java.specification.name"));
    rpcJst.setClassVersion(PROPERTIES.getProperty("java.class.version"));
    rpcJst.setClassPath(PROPERTIES.getProperty("java.class.path"));
    rpcJst.setLibraryPath(PROPERTIES.getProperty("java.library.path"));
    rpcJst.setIoTmpdir(PROPERTIES.getProperty("java.io.tmpdir"));
    rpcJst.setExtDirs(PROPERTIES.getProperty("java.ext.dirs"));
    return rpcJst;
  }

  public void jrt() {
    Runtime r = Runtime.getRuntime();
    System.out.println("JVM可以使用的总内存:    " + r.totalMemory());
    System.out.println("JVM可以使用的剩余内存:    " + r.freeMemory());
    System.out.println("JVM可以使用的处理器个数:    " + r.availableProcessors());
  }

  public void file() {
    System.out.println("文件分隔符：    " + PROPERTIES.getProperty("file.separator"));
    System.out.println("路径分隔符：    " + PROPERTIES.getProperty("path.separator"));
    System.out.println("行分隔符：    " + PROPERTIES.getProperty("line.separator"));
  }

  public void user() {
    System.out.println("用户的账户名称：    " + PROPERTIES.getProperty("user.name"));
    System.out.println("用户的主目录：    " + PROPERTIES.getProperty("user.home"));
    System.out.println("用户的当前工作目录：    " + PROPERTIES.getProperty("user.dir"));
  }


}
