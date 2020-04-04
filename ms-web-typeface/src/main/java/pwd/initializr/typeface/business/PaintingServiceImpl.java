package pwd.initializr.typeface.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.storage.rpc.UploadOutput;
import pwd.initializr.typeface.business.bo.FontBO;
import pwd.initializr.typeface.business.bo.PaintingBO;
import pwd.initializr.typeface.persistence.dao.PaintingEntity;
import pwd.initializr.typeface.persistence.mapper.PaintingMapper;
import pwd.initializr.typeface.util.Painter;

/**
 * pwd.initializr.typeface.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 15:27
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
@Slf4j
public class PaintingServiceImpl implements PaintingService {

  @Value("${spring.application.name}")
  private String applicationName;
  @Value("${ms.typeface.bucket.name}")
  private String bucketName;

  @Autowired
  private FontService fontService;
  @Autowired
  private PaintingMapper paintingMapper;
  @Value("${ms.typeface.printer}")
  private String printer;
  @Autowired
  private StorageService storageService;

  @Value("${ms.typeface.ttf.dir}")
  private String ttfDir;

  @Override
  public PaintingBO create(PaintingBO paintingBO) {
    String content = paintingBO.getContent();
    String[] contents = content.split("\r\n");

    FontBO fontBO = fontService.findById(paintingBO.getFontId());
    String fontPath = String.join("", ttfDir, fontBO.getName());
    BufferedImage bufferedImage = Painter.createImage(contents, fontPath);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    String objectName = String
        .join("/", printer, String.valueOf(System.currentTimeMillis()), UUID.randomUUID() + ".png");
    try {
      DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
          MediaType.IMAGE_PNG_VALUE, false, objectName);
      OutputStream outputStream = fileItem.getOutputStream();
      IOUtils.copy(inputStream, outputStream);
      MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
      String upload = storageService.upload(applicationName, bucketName, objectName, multipartFile);
      Output<UploadOutput> output = JSON
          .parseObject(upload, new TypeReference<Output<UploadOutput>>() {
          });
      if (200 == output.getMeta().getCode()) {
        UploadOutput uploadOutput = output.getData();
        paintingBO.setImageUrl(uploadOutput.getUrl());
        paintingBO.setBucketName(uploadOutput.getBucketName());
        paintingBO.setObjectName(uploadOutput.getObjectName());
        paintingBO.setCreateTime(System.currentTimeMillis());
        paintingBO.setUpdateTime(System.currentTimeMillis());
        PaintingEntity paintingEntity = new PaintingEntity();
        BeanUtils.copyProperties(paintingBO, paintingEntity);
        paintingEntity.setStatus(0);
        paintingMapper.insert(paintingEntity);
        BeanUtils.copyProperties(paintingEntity, paintingBO);
        return paintingBO;
      } else {
        return null;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Integer deleteByIds(List<Long> ids) {
    Integer result = 0;
    List<PaintingEntity> deletion = paintingMapper.findByIds(ids);
    deletion.stream()
        .collect(Collectors
            .toMap(PaintingEntity::getBucketName, Function.identity(), (key1, key2) -> key2,
                LinkedHashMap::new)).forEach((key, values) -> {
      String delete = storageService
          .delete(applicationName, key, Stream.of(values).map(value -> value.getObjectName())
              .collect(Collectors.toList()));
      Output<UploadOutput> output = JSON
          .parseObject(delete, new TypeReference<Output<UploadOutput>>() {
          });
      if (200 == output.getMeta().getCode()) {
        Integer integer = paintingMapper
            .deleteByIds(deletion.stream().map(obj -> obj.getId()).collect(Collectors.toList()));
      } else {
        log.error("{}", output);
      }
    });
    return result;
  }

  @Override
  public ObjectList<PaintingBO> findByCondition(PaintingBO paintingBO, Long pageIndex,
      Long pageSize) {
    PaintingEntity paintingEntity = new PaintingEntity();
    BeanUtils.copyProperties(paintingBO, paintingEntity);

    Long count = paintingMapper.countByCondition(paintingEntity);

    List<PaintingEntity> findByCondition = paintingMapper
        .findByCondition(paintingEntity, pageIndex * pageSize, pageSize);

    List<PaintingBO> collect = findByCondition.stream().map(
        obj -> new PaintingBO(obj.getId(), obj.getUserId(), obj.getFontId(), obj.getFontSize(),
            obj.getContent(), obj.getBackground(), obj.getForeground(), obj.getWidth(),
            obj.getHeight(), obj.getImageUrl(), obj.getStatus(), obj.getCreateTime(),
            obj.getUpdateTime()))
        .collect(Collectors.toList());

    return new ObjectList<>(count, pageIndex, pageSize, collect);
  }

  @Override
  public PaintingBO findById(Long id) {
    PaintingEntity byId = paintingMapper.findById(id);
    PaintingBO paintingBO = new PaintingBO();
    BeanUtils.copyProperties(byId, paintingBO);
    return paintingBO;
  }


}
