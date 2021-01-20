package pwd.initializr.typeface.persistence.dao;

import java.util.LinkedHashSet;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;
import pwd.initializr.common.web.persistence.entity.SortEntity;
import pwd.initializr.typeface.persistence.entity.PaintingEntity;

/**
 * pwd.initializr.typeface.persistence.entity@ms-web-initializr
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

  Long countAllByCondition(@Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes);

  Integer deleteByIds(@Param("ids") List<Long> ids);

  PaintingEntity findById(@Param("id") Long id);

  List<PaintingEntity> findByIds(@Param("ids") List<Long> ids);

  void insert(@Param("paintingEntity") PaintingEntity paintingEntity);

  List<PaintingEntity> queryAllByCondition(
      @Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes,
      @Param("sorts") LinkedHashSet<? extends SortEntity> sorts,
      @Param("offset") Long offset, @Param("limit") Long limit);
}
