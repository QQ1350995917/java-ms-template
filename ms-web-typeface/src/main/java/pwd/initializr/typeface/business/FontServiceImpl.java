package pwd.initializr.typeface.business;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
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
  public ObjectList<FontBO> findByCondition(FontBO fontBO, Long pageIndex,
      Long pageSize) {
    FontEntity fontEntity = new FontEntity();
    BeanUtils.copyProperties(fontBO, fontEntity);

    QueryWrapper<FontEntity> title = new QueryWrapper<FontEntity>().like("title", fontBO.getTitle());

    Integer count = fontMapper.selectCount(title);

    IPage<FontEntity> fontEntityIPage = fontMapper
        .selectPage(new Page<>(pageIndex, pageSize), title);

    List<FontBO> collect = fontEntityIPage.getRecords().stream().map(
        obj -> new FontBO(obj.getId(), obj.getTitle(), obj.getName(), obj.getFileUrl(),
            obj.getThumbUrl(), obj.getStatus(), obj.getCreateTime(), obj.getUpdateTime()))
        .collect(Collectors.toList());

    return new ObjectList<>(count.longValue(), pageIndex, pageSize, collect);
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
