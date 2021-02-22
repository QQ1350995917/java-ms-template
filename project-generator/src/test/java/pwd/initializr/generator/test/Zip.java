package pwd.initializr.generator.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 * pwd.initializr.generator.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-16 10:59
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Zip {

  public static void decompressZipfile(String file, String outputDir) throws IOException {
    if (!new File(outputDir).exists()) {
      new File(outputDir).mkdirs();
    }
    ZipFile zipFile = new ZipFile(file);
    Enumeration<? extends ZipEntry> entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = entries.nextElement();
      File entryDestination = new File(outputDir, entry.getName());
      if (entry.isDirectory()) {
        entryDestination.mkdirs();
      } else {
        InputStream in = zipFile.getInputStream(entry);
        OutputStream out = new FileOutputStream(entryDestination);
        IOUtils.copy(in, out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
      }
    }
  }

  public static void main(String[] args) {

  }
}
