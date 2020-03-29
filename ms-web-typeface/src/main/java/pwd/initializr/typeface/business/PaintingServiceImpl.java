package pwd.initializr.typeface.business;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.typeface.business.bo.PaintingBO;
import pwd.initializr.typeface.persistence.dao.PaintingEntity;
import pwd.initializr.typeface.persistence.mapper.PaintingMapper;

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

  @Autowired
  private PaintingMapper paintingMapper;

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
    BeanUtils.copyProperties(byId,paintingBO);
    return paintingBO;
  }

  @Override
  public PaintingBO save(PaintingBO paintingBO) {
    PaintingEntity paintingEntity = new PaintingEntity();
    BeanUtils.copyProperties(paintingBO,paintingEntity);
    paintingMapper.insert(paintingEntity);
    BeanUtils.copyProperties(paintingEntity,paintingBO);
    return paintingBO;
  }


}
