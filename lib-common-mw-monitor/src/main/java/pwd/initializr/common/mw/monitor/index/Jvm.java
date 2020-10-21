package pwd.initializr.common.mw.monitor.index;

import java.util.Properties;
import pwd.initializr.monitor.rpc.RPCJvmFile;
import pwd.initializr.monitor.rpc.RPCJvmJrt;
import pwd.initializr.monitor.rpc.RPCJvmJst;
import pwd.initializr.monitor.rpc.RPCJvmUser;

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

  public static RPCJvmJst jst() {
    RPCJvmJst rpcJvmJst = new RPCJvmJst();
    rpcJvmJst.setVersion(PROPERTIES.getProperty("java.version"));
    rpcJvmJst.setVendor(PROPERTIES.getProperty("java.vendor"));
    rpcJvmJst.setVendorUrl(PROPERTIES.getProperty("java.vendor.url"));
    rpcJvmJst.setHome(PROPERTIES.getProperty("java.home"));
    rpcJvmJst.setVmVersion(PROPERTIES.getProperty("java.vm.version"));
    rpcJvmJst.setVmVendor(PROPERTIES.getProperty("java.vm.vendor"));
    rpcJvmJst.setVmName(PROPERTIES.getProperty("java.vm.name"));
    rpcJvmJst.setVmSpecificationVersion(PROPERTIES.getProperty("java.vm.specification.version"));
    rpcJvmJst.setSpecificationVender(PROPERTIES.getProperty("java.specification.vender"));
    rpcJvmJst.setVmSpecificationVendor(PROPERTIES.getProperty("java.vm.specification.vendor"));
    rpcJvmJst.setVmSpecificationName(PROPERTIES.getProperty("java.vm.specification.name"));
    rpcJvmJst.setSpecificationVersion(PROPERTIES.getProperty("java.specification.version"));
    rpcJvmJst.setSpecificationName(PROPERTIES.getProperty("java.specification.name"));
    rpcJvmJst.setClassVersion(PROPERTIES.getProperty("java.class.version"));
    rpcJvmJst.setClassPath(PROPERTIES.getProperty("java.class.path"));
    rpcJvmJst.setLibraryPath(PROPERTIES.getProperty("java.library.path"));
    rpcJvmJst.setIoTmpdir(PROPERTIES.getProperty("java.io.tmpdir"));
    rpcJvmJst.setExtDirs(PROPERTIES.getProperty("java.ext.dirs"));
    return rpcJvmJst;
  }

  public static RPCJvmJrt jrt() {
    Runtime runtime = Runtime.getRuntime();
    RPCJvmJrt rpcJvmJrt = new RPCJvmJrt();
    rpcJvmJrt.setTotalMemory(runtime.totalMemory());
    rpcJvmJrt.setFreeMemory(runtime.freeMemory());
    rpcJvmJrt.setAvailableProcessors(runtime.availableProcessors());
    return rpcJvmJrt;
  }

  public static RPCJvmFile file() {
    RPCJvmFile rpcJvmFile = new RPCJvmFile();
    rpcJvmFile.setFileSeparator(PROPERTIES.getProperty("file.separator"));
    rpcJvmFile.setPathSeparator(PROPERTIES.getProperty("path.separator"));
    rpcJvmFile.setPathSeparator(PROPERTIES.getProperty("line.separator"));
    return rpcJvmFile;
  }

  public static RPCJvmUser user() {
    RPCJvmUser rpcJvmUser = new RPCJvmUser();
    rpcJvmUser.setName(PROPERTIES.getProperty("user.name"));
    rpcJvmUser.setHome(PROPERTIES.getProperty("user.home"));
    rpcJvmUser.setDir(PROPERTIES.getProperty("user.dir"));
    return rpcJvmUser;
  }

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    RPCJvmJst jst = Jvm.jst();
    RPCJvmJrt jrt = Jvm.jrt();
    RPCJvmFile file = Jvm.file();
    RPCJvmUser user = Jvm.user();
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }


}
