package pwd.initializr.search.business.admin;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.search.business.admin.IndexService;

/**
 * pwd.initializr.search.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 21:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class IndexServiceImpl implements IndexService {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Override
  public boolean create(String index) {
    CreateIndexResponse createIndexResponse = elasticsearchTemplate.getClient().admin().indices()
        .prepareCreate(index).get();
    return createIndexResponse.isAcknowledged();
  }
}
