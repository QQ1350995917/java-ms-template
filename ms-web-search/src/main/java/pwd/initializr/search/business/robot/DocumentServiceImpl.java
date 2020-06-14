package pwd.initializr.search.business.robot;

import java.io.IOException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.search.business.robot.bo.DocumentBO;

/**
 * pwd.initializr.search.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 21:29
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class DocumentServiceImpl implements DocumentService {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Override
  public int create(String index, DocumentBO documentBO) {
    try {
      XContentBuilder builder = XContentFactory.jsonBuilder()
          .startObject()
          .field("esId", documentBO.getEsId())
          .field("esVisibility", documentBO.getEsVisibility())
          .field("esTitle", documentBO.getEsTitle())
          .field("esContent", documentBO.getEsContent())
          .field("esLinkTo", documentBO.getEsLinkTo())
          .field("esUpdateTime", documentBO.getEsUpdateTime())
          .endObject();

      IndexResponse indexResponse = elasticsearchTemplate.getClient().prepareIndex(index, index)
          .setId(documentBO.getEsId())
          .setSource(builder).get();
//      String jsonString = JSONObject.toJSONString(documentBO);
//      client.prepareIndex(index, index, documentBO.getEsId())
//          .setSource(jsonString, XContentType.JSON).get();

      return indexResponse.getResult().ordinal();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
