package pwd.initializr.typeface.business;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
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
public class PaintingServiceImpl implements PaintingService {

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
    String fontPath = String.join("", ttfDir, fontBO.getFileUrl());
    BufferedImage bufferedImage = Painter.createImage(contents, fontPath);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    String objectName = String
        .join("/", printer, String.valueOf(System.currentTimeMillis()), UUID.randomUUID() + ".jpg");
//    storageService.upload();
    paintingBO.setImageUrl(objectName);
    paintingBO.setCreateTime(System.currentTimeMillis());
    paintingBO.setUpdateTime(System.currentTimeMillis());

    PaintingEntity paintingEntity = new PaintingEntity();
    BeanUtils.copyProperties(paintingBO, paintingEntity);
    paintingMapper.insert(paintingEntity);
    BeanUtils.copyProperties(paintingEntity, paintingBO);

    return paintingBO;
  }

  @Override
  public List<Integer> deleteByIds(List<Long> ids) {
    return null;
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
