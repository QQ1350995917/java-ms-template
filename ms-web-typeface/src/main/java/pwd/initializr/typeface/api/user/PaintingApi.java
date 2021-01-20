package pwd.initializr.typeface.api.user;

import java.util.List;
import pwd.initializr.typeface.api.user.vo.PaintingListInput;
import pwd.initializr.typeface.api.user.vo.PaintingVO;

/**
 * pwd.initializr.typeface.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 20:42
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface PaintingApi {

  void createPainting(PaintingVO input);

  void deleteById(Long id);

  void deleteByIds(List<Long> ids);

  void findByCondition(PaintingListInput input);

  void findById(Long id);

}
