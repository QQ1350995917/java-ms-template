package pwd.initializr.search.business.admin;

import java.util.List;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.search.business.admin.bo.IndexBO;
import pwd.initializr.search.business.admin.bo.MappingBO;

/**
 * pwd.initializr.search.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-16 15:20
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface MetadataService {

    ObjectList<IndexBO> list();

    boolean create(String index);

    boolean create(String index, List<MappingBO> mappingBOS);

    List<MappingBO> getDefaultMapping();
}
