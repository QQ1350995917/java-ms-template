package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.edu.business.StorageService;
import pwd.initializr.edu.persistence.dao.EduTermCourseTextbookArticleDao;
import pwd.initializr.edu.persistence.entity.EduTermCourseTextbookArticleEntity;
import pwd.initializr.storage.rpc.RPCUploadOutput;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-06-20 15:18
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMp3StorageTest {

  @Value("${storage.bucket.name}")
  private String storageBucketName;
  @Autowired
  EduTermCourseTextbookArticleDao dao;
  @Autowired
  StorageService storageService;

  @Test
  public void initEduTermTable() throws Exception {

    List<Map<String, Object>> maps = dao
        .queryBySql("select id,media_path from edu_term_course_textbook_article");
    for (Map<String, Object> map : maps) {
      EduTermCourseTextbookArticleEntity eduTermCourseTextbookArticleEntity = new EduTermCourseTextbookArticleEntity();
      eduTermCourseTextbookArticleEntity.setId(Long.parseLong(map.get("id").toString()));
      if (map.get("media_path") != null) {
        String mediaPath = map.get("media_path").toString();
        String name = new File(mediaPath).getName();
        try {
          FileInputStream fileInputStream = new FileInputStream(
              "/Users/pwd/Documents/minio/edu/" + name);
          DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
              MediaType.parseMediaType("audio/*").getType(), false, name);
          OutputStream outputStream = fileItem.getOutputStream();
          IOUtils.copy(fileInputStream, outputStream);
          MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
          String upload = storageService
              .upload(storageBucketName, storageBucketName, multipartFile);
          Output<RPCUploadOutput> output = JSON
              .parseObject(upload, new TypeReference<Output<RPCUploadOutput>>() {
              });
          if (200 == output.getMeta().getCode()) {
            RPCUploadOutput RPCUploadOutput = output.getData();
            String id = RPCUploadOutput.getId();
            eduTermCourseTextbookArticleEntity.setMediaPath(id);
            dao.updateById(eduTermCourseTextbookArticleEntity);
          } else {
            System.out.println(JSON.toJSON(output));
          }
          outputStream.close();
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }


      }


    }

  }
}
