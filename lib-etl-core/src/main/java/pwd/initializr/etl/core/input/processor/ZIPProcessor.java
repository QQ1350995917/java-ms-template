package pwd.initializr.etl.core.input.processor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * pwd.initializr.etl.core.input.processor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-29 22:45
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ZIPProcessor extends TXTProcessor {

  @Override
  public void onProcess(String filePath) {
    try {
      ZipFile zipFile = new ZipFile(filePath);
      InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
      Charset charset = Charset.forName(getCharset());
      ZipInputStream zipInputStream = new ZipInputStream(inputStream, charset);
      ZipEntry zipEntry;
      while ((zipEntry = zipInputStream.getNextEntry()) != null) {
        if (zipEntry.isDirectory()) {

        } else {
          if (zipEntry.getSize() > 0) {
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(zipFile.getInputStream(zipEntry)));
            String line;
            Integer lineNum = 0;
            while ((line = bufferedReader.readLine()) != null) {
              lineNum++;
              putToInputBlockingQueue(super.buildMap(line, filePath, lineNum));
            }
            bufferedReader.close();
          }
        }
      }
      zipInputStream.closeEntry();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
