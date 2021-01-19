package pwd.initializr.typeface.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;
import pwd.initializr.common.web.persistence.entity.SortEntity;
import pwd.initializr.typeface.persistence.entity.FontEntity;

/**
 * pwd.initializr.typeface.persistence.entity@ms-web-initializr
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
public interface FontMapper extends BaseMapper<FontEntity> {

  Long countAllByCondition(@Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes);

  List<FontEntity> queryAllByCondition(@Param("scopes") LinkedHashSet<? extends ScopeEntity> scopes,
      @Param("sorts") LinkedHashSet<? extends SortEntity> sorts,
      @Param("offset") Long offset, @Param("limit") Long limit);

//  FontEntity findById(@Param("id") Long id);
//
//  void insert(@Param("fontEntity") FontEntity fontEntity);
}
