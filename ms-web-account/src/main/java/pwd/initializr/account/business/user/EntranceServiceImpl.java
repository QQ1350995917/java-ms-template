package pwd.initializr.account.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.Entrance;
import pwd.initializr.account.persistence.dao.EntranceEntity;
import pwd.initializr.account.persistence.mapper.EntranceMapper;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-24 16:37
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class EntranceServiceImpl implements EntranceService {

  @Autowired
  private EntranceMapper entranceMapper;

  @Override
  public ObjectList<Entrance> findUserEntranceByType(Integer type) {
    List<EntranceEntity> signUpByUser = entranceMapper.findUserEntranceByType(type);
    ObjectList<Entrance> result = new ObjectList<>();
    for (EntranceEntity entranceEntity : signUpByUser) {
      Entrance entrance = new Entrance();
      BeanUtils.copyProperties(entranceEntity, entrance);
      result.getElements().add(entrance);
    }
    return result;
  }
}
