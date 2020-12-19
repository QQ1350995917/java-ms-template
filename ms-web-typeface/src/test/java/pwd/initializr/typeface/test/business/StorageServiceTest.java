package pwd.initializr.typeface.test.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pwd.initializr.typeface.TypefaceApplication;
import pwd.initializr.typeface.business.StorageService;

/**
 * pwd.initializr.typeface.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-19 22:42
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TypefaceApplication.class})
public class StorageServiceTest {

  @Autowired
  private StorageService storageService;

  @Test
  public void testUpload() throws Exception {
    String appName = "test";
    String bucketName = "test";
    String fileName = "test";
    String filePath = "/Users/pwd/Documents/WX20200227-095721@2x.png";
    InputStream thumbInputStream = new FileInputStream(new File(filePath));
    DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
        MediaType.IMAGE_PNG_VALUE, false, fileName);
    OutputStream outputStream = fileItem.getOutputStream();
    IOUtils.copy(thumbInputStream, outputStream);
    MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

    String upload = storageService.upload(appName, bucketName, fileName, multipartFile);

  }
}
