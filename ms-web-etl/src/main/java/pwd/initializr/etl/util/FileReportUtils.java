package pwd.initializr.etl.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * pwd.initializr.etl.util@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-17 16:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class FileReportUtils {

  public static Map<String,Integer> countFile(String filePath){
    Map<String,Integer> result = new HashMap<>();
    result.put("transferring",0);
    result.put("transferred",0);
    result.put("processing",0);

    try {
      Integer allData = new File(filePath).listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          if (name.endsWith(".data") || name.endsWith(".dataing")){
            return true;
          }
          return false;
        }
      }).length;

      Integer transferred = new File(filePath).listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          if (name.endsWith(".ok")){
            return true;
          }
          return false;
        }
      }).length;

      Integer processing = new File(filePath).listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          if (name.endsWith(".oking")){
            return true;
          }
          return false;
        }
      }).length;

      Integer transferring = allData - transferred - processing;

      result.put("transferring",transferring);
      result.put("transferred",transferred);
      result.put("processing",processing);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return result;
  }

  private Long getFileCreateTime(String filePath) {
    File file = new File(filePath);
    try {
      Path path = Paths.get(filePath);
      BasicFileAttributeView basicView = Files
          .getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
      BasicFileAttributes attr = basicView.readAttributes();
      return attr.creationTime().toMillis();
    } catch (Exception e) {
      e.printStackTrace();
      return file.lastModified();
    }
  }

}
