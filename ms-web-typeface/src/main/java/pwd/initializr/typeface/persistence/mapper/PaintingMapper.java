package pwd.initializr.typeface.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.typeface.persistence.dao.PaintingEntity;

/**
 * pwd.initializr.typeface.persistence.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 15:14
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Mapper
public interface PaintingMapper {

  Long countByCondition(@Param("paintingEntity") PaintingEntity paintingEntity);

  Integer deleteByIds(@Param("ids") List<Long> ids);

  List<PaintingEntity> findByCondition(@Param("paintingEntity") PaintingEntity paintingEntity,
      @Param("offset") Long offset, @Param("rows") Long rows);

  PaintingEntity findById(@Param("id") Long id);

  List<PaintingEntity> findByIds(@Param("ids") List<Long> ids);

  void insert(@Param("paintingEntity") PaintingEntity paintingEntity);
}
