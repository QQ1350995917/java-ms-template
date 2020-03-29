package pwd.initializr.typeface.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.typeface.persistence.dao.FontEntity;

/**
 * pwd.initializr.typeface.persistence.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 15:00
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Mapper
public interface FontMapper {

  Long countByCondition(
      @Param("fontEntity") FontEntity fontEntity);

  List<FontEntity> findByCondition(@Param("fontEntity") FontEntity fontEntity,
      @Param("offset") Long offset, @Param("rows") Long rows);

  FontEntity findById(@Param("id") Long id);

  void insert(@Param("fontEntity") FontEntity fontEntity);
}
