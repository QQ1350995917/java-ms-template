package pwd.initializr.search.business.robot;

import java.util.List;
import pwd.initializr.search.business.robot.bo.MappingBO;

/**
 * pwd.initializr.search.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 21:24
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface MappingService {

  boolean create(String index,List<MappingBO> mappingBOS);

  List<MappingBO> getDefaultMapping();

}
