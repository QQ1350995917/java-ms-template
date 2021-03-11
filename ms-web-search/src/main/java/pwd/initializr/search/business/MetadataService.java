package pwd.initializr.search.business;

import java.util.List;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.bo.IndexBO;
import pwd.initializr.search.business.bo.MappingBO;

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

    PageableQueryResult<IndexBO> listIndex(String indexName);

    boolean existIndex(String indexName);

    boolean createIndex(String indexName);

    boolean createIndex(String indexName, List<MappingBO> mappingBOS);

    boolean deleteIndex(String indexName);

    List<MappingBO> getDefaultMapping();
}
