package pwd.initializr.typeface.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pwd.initializr.typeface.business.FontService;
import pwd.initializr.typeface.business.StorageService;
import pwd.initializr.typeface.business.bo.FontBO;

/**
 * pwd.initializr.typeface.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 23:00
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {TypefaceApplication.class})
public class TypefaceApplicationInitTest {

  @Autowired
  private FontService fontService;

  @Autowired
  private StorageService storageService;

  public static void main(String[] args) throws Exception {

  }

  @Test
  public void init() throws Exception {
    String baseDir = "/Users/pwd/Documents/minio/xresources";
    String thumbDir = baseDir + "/thumb";
    String ttfDir = baseDir + "/fonts";
    String bucketName = "initializr-typeface";

    JSONArray jsonArray = JSON
        .parseObject(new FileInputStream(new File(baseDir + "/jsons/All.json")), JSONArray.class,
            null);

    initStorageThumb(thumbDir, jsonArray, bucketName);
    initStorageTTF(ttfDir, jsonArray, bucketName);
    initFontSQL(jsonArray);

  }

  private void initStorageThumb(String thumbDir, JSONArray sourceJSON, String bucketName) {
    sourceJSON.stream().filter(
        obj -> new File(thumbDir + "/" + ((JSONObject) obj).getString("id") + ".png").exists())
        .forEach(jsonObject -> {
          String title = ((JSONObject) jsonObject).getString("title");
          String id = ((JSONObject) jsonObject).getString("id");
          try {
            InputStream thumbInputStream = new FileInputStream(
                new File(thumbDir + "/" + id + ".png"));
            String objectName = "font/" + title + ".png";
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.IMAGE_PNG_VALUE, false, objectName);
            OutputStream outputStream = fileItem.getOutputStream();
            IOUtils.copy(thumbInputStream, outputStream);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            String upload = storageService
                .upload(bucketName, bucketName, objectName, multipartFile);
            System.out.println(upload);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
  }

  private void initStorageTTF(String ttfDir, JSONArray sourceJSON, String bucketName) {
    sourceJSON.stream().filter(
        obj -> new File(ttfDir + "/" + ((JSONObject) obj).getString("title") + ".ttf").exists())
        .forEach(jsonObject -> {
          String title = ((JSONObject) jsonObject).getString("title");
          try {
            InputStream ttfInputStream = new FileInputStream(
                new File(ttfDir + "/" + title + ".ttf"));
            String objectName = "font/" + title + ".ttf";
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.IMAGE_PNG_VALUE, false, objectName);
            OutputStream outputStream = fileItem.getOutputStream();
            IOUtils.copy(ttfInputStream, outputStream);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            String upload = storageService
                .upload(bucketName, bucketName, objectName, multipartFile);
            System.out.println(upload);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
  }

  private void initFontSQL(JSONArray jsonArray) {
    jsonArray.stream().forEach(jsonObject -> {
      String title = ((JSONObject) jsonObject).getString("title");
      FontBO fontBO = new FontBO();
      fontBO.setName(title);
      fontBO.setFileUrl("font/" + title + ".ttf");
      fontBO.setThumbUrl("font/" + title + ".png");
      fontBO.setCreateTime(System.currentTimeMillis());
      fontBO.setUpdateTime(System.currentTimeMillis());
      fontService.save(fontBO);
    });
  }

}
