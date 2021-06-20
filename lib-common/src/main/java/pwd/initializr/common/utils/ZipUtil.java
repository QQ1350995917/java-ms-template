package pwd.initializr.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang.StringUtils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:12
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ZipUtil {

  public static final int BUFFER = 1024;

  public ZipUtil() {
  }

  public static byte[] gZipCompress(byte[] data) throws Exception {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
    byte[] tempData = new byte[1024];
    int count;
    while ((count = byteArrayInputStream.read(tempData, 0, 1024)) != -1) {
      gzipOutputStream.write(tempData, 0, count);
    }
    gzipOutputStream.finish();
    gzipOutputStream.flush();
    gzipOutputStream.close();

    byte[] output = byteArrayOutputStream.toByteArray();
    byteArrayOutputStream.flush();
    byteArrayOutputStream.close();
    byteArrayInputStream.close();
    return output;
  }

  public static void main(String[] args) throws Exception {
    String source = "C:\\Users\\Administrator\\Downloads\\zip.xml";
    File sourceFile = new File(source);
    String target =
        "C:\\Users\\Administrator\\Downloads\\project-generator-test-" + System.currentTimeMillis()
            + ".zip";
    File targetFile = new File(target);
    if (targetFile.exists()) {
      targetFile.delete();
    }
    ZipUtil.zip(sourceFile, targetFile);
  }

  public static void unZipFile(String zipFile, String charset, String outDir) throws Exception {
    File pathFile = new File(outDir);
    if (!pathFile.exists()) {
      pathFile.mkdirs();
    }
    //解决zip文件中有中文目录或者中文文件
    ZipFile zip = new ZipFile(zipFile, Charset.forName(charset));
    for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      String zipEntryName = entry.getName();
      InputStream in = zip.getInputStream(entry);
      String outPath = (outDir + zipEntryName).replaceAll("\\*", "/");
      ;
      //判断路径是否存在,不存在则创建文件路径
      File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
      if (!file.exists()) {
        file.mkdirs();
      }
      //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
      if (new File(outPath).isDirectory()) {
        continue;
      }
      //输出文件路径信息
      System.out.println(outPath);
      OutputStream out = new FileOutputStream(outPath);
      byte[] buf1 = new byte[BUFFER];
      int len;
      while ((len = in.read(buf1)) > 0) {
        out.write(buf1, 0, len);
      }
      in.close();
      out.close();
    }
  }

  public static void zip(File sourceFile, File targetFile) throws IOException {
    if (sourceFile == null || !sourceFile.exists()) {
      throw new RuntimeException(new FileNotFoundException(sourceFile.getAbsolutePath()));
    }

    File parentFile = targetFile.getParentFile();
    if (!parentFile.exists()) {
      parentFile.mkdirs();
    }

    try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(targetFile))) {
      if (sourceFile.isDirectory()) {
        directory(zipOutputStream, sourceFile, "");
      } else {
        zipFile(zipOutputStream, sourceFile, sourceFile.getName());
      }
    }
  }

  /**
   * <h2>以ZIP格式压缩文件</h2>
   * date 2020-09-16 11:23
   *
   * @param source 原始路径，一个目录或者一个文件
   * @param target 目标文件，指定生成的ZIP文件全路径
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  public static void zip(String source, String target) throws IOException {
    zip(new File(source), new File(target));
  }

  private static void directory(ZipOutputStream zipOutputStream, File file, String parentFileName)
      throws IOException {
    File[] files = file.listFiles();
    String parentFileNameTemp;
    for (File fileTemp : files) {
      parentFileNameTemp = StringUtils.isEmpty(parentFileName) ? fileTemp.getName()
          : parentFileName + File.separator + fileTemp.getName();
      if (fileTemp.isDirectory()) {
        directory(zipOutputStream, fileTemp, parentFileNameTemp);
      } else {
        zipFile(zipOutputStream, fileTemp, parentFileNameTemp);
      }
    }
  }

  private static void zipFile(ZipOutputStream zipOutputStream, File file, String parentFileName)
      throws IOException {
    try (FileInputStream in = new FileInputStream(file)) {
      ZipEntry zipEntry = new ZipEntry(parentFileName);
      zipOutputStream.putNextEntry(zipEntry);
      int len;
      byte[] buf = new byte[8 * 1024];
      while ((len = in.read(buf)) != -1) {
        zipOutputStream.write(buf, 0, len);
      }
      zipOutputStream.flush();
      zipOutputStream.closeEntry();
    }
  }


}
