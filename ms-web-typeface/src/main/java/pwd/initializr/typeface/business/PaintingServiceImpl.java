package pwd.initializr.typeface.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.storage.rpc.RPCUploadOutput;
import pwd.initializr.typeface.business.bo.FontBO;
import pwd.initializr.typeface.business.bo.PaintingBO;
import pwd.initializr.typeface.persistence.dao.PaintingMapper;
import pwd.initializr.typeface.persistence.entity.PaintingEntity;
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

  static String lineSeparator = System.getProperty("line.separator", "\n");

  @Value("${spring.application.name}")
  private String applicationName;
  @Value("${typeface.bucket.name}")
  private String bucketName;

  @Autowired
  private FontService fontService;
  @Autowired
  private PaintingMapper paintingMapper;
  @Value("${typeface.bucket.object.name.prefix}")
  private String objectNamePrefix;
  @Autowired
  private StorageService storageService;

  @Value("${typeface.ttf.dir}")
  private String ttfDir;

  @Override
  public PaintingBO create(PaintingBO paintingBO) {
    String content = paintingBO.getContent();
    String[] contents = content.split(lineSeparator);

    FontBO fontBO = fontService.findById(paintingBO.getFontId());
    String fontPath = String.join("/", ttfDir, fontBO.getName());
    BufferedImage bufferedImage = Painter.createImage(contents, fontPath);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    String objectName = String
        .join("/", objectNamePrefix, String.valueOf(System.currentTimeMillis()), UUID.randomUUID() + ".png");
    try {
      DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
          MediaType.IMAGE_PNG_VALUE, false, objectName);
      OutputStream outputStream = fileItem.getOutputStream();
      IOUtils.copy(inputStream, outputStream);
      MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
      String upload = storageService.upload(applicationName, bucketName, objectName, multipartFile);
      Output<RPCUploadOutput> output = JSON
          .parseObject(upload, new TypeReference<Output<RPCUploadOutput>>() {
          });
      if (200 == output.getMeta().getCode()) {
        RPCUploadOutput RPCUploadOutput = output.getData();
        paintingBO.setImageUrl(RPCUploadOutput.getUrl());
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
    Integer integer = paintingMapper.deleteByIds(ids);
    return integer;
  }

  @Override
  public PageableQueryResult<PaintingBO> findByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {

    Long count = paintingMapper.countAllByCondition(scopes);

    List<PaintingEntity> findByCondition = paintingMapper
        .queryAllByCondition(scopes, sorts, pageIndex * pageSize, pageSize);

    List<PaintingBO> collect = findByCondition.stream().map(
        obj -> new PaintingBO(obj.getId(), obj.getUserId(), obj.getFontId(), obj.getFontSize(),
            obj.getContent(), obj.getBackground(), obj.getForeground(), obj.getWidth(),
            obj.getHeight(), obj.getImageUrl(), obj.getStatus(), obj.getCreateTime(),
            obj.getUpdateTime()))
        .collect(Collectors.toList());

    return new PageableQueryResult<>(count, pageIndex, pageSize, collect);
  }

  @Override
  public PaintingBO findById(Long id) {
    PaintingEntity byId = paintingMapper.findById(id);
    PaintingBO paintingBO = new PaintingBO();
    BeanUtils.copyProperties(byId, paintingBO);
    return paintingBO;
  }


}
