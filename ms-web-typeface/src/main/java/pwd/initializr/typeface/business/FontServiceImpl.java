package pwd.initializr.typeface.business;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.typeface.business.bo.FontBO;
import pwd.initializr.typeface.persistence.dao.FontMapper;
import pwd.initializr.typeface.persistence.entity.FontEntity;

/**
 * pwd.initializr.typeface.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 15:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class FontServiceImpl implements FontService {

  @Autowired
  private FontMapper fontMapper;

  @Override
  public PageableQueryResult<FontBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<FontBO> result = new PageableQueryResult<>();
    Long total = this.fontMapper.countAllByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<FontEntity> entities = this.fontMapper
        .queryAllByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (entities == null) {
      return null;
    }
    List<FontBO> collect = entities.stream().map(
        obj -> new FontBO(obj.getId(), obj.getTitle(), obj.getName(), obj.getFileUrl(),
            obj.getThumbUrl(), obj.getStatus(), obj.getCreateTime(), obj.getUpdateTime()))
        .collect(Collectors.toList());

    result.getElements().addAll(collect);
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  @Override
  public FontBO findById(Long id) {
    FontEntity findById = fontMapper.selectById(id);
    FontBO fontBO = new FontBO();
    BeanUtils.copyProperties(findById, fontBO);
    return fontBO;
  }

  @Override
  public FontBO save(FontBO fontBO) {
    FontEntity fontEntity = new FontEntity();
    BeanUtils.copyProperties(fontBO, fontEntity);
    fontMapper.insert(fontEntity);
    BeanUtils.copyProperties(fontEntity, fontBO);
    return fontBO;
  }
}
