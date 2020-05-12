package pwd.initializr.search.persistence.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pwd.initializr.search.persistence.entity.BookDocument;

/**
 * pwd.initializr.search.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-12 16:20
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<BookDocument, Long> {

}
