package pwd.initializr.search.business.admin;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.search.business.admin.bo.MappingBO;
import pwd.initializr.search.business.admin.bo.MappingFieldBO;

/**
 * pwd.initializr.search.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 21:27
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class MappingServiceImpl implements MappingService {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Override
  public boolean create(String index, List<MappingBO> mappingBOS) {
    try {
      XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
          .startObject("properties");
      Optional.ofNullable(mappingBOS).orElseGet(LinkedList::new).forEach(mappingBO -> {
        try {
          builder.startObject(mappingBO.getName());
          Optional.ofNullable(mappingBO.getFields()).orElseGet(LinkedList::new).forEach(field -> {
                try {
                  builder.field(field.getName(), field.getValue());
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
              }
          );
          builder.endObject();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });

      builder.endObject().endObject();

      PutMappingResponse putMappingResponse = elasticsearchTemplate.getClient().admin().indices()
          .preparePutMapping(index)
          .setType(index)
          .setSource(builder)
          .get();

      return putMappingResponse.isAcknowledged();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<MappingBO> getDefaultMapping() {
    List<MappingBO> mappingBOS = new LinkedList<>();

    MappingBO esIdMappingBO = new MappingBO();
    esIdMappingBO.setName("esId");
    List<MappingFieldBO> esIdFields = new LinkedList<>();
    esIdFields.add(new MappingFieldBO("type", "keyword"));
    esIdMappingBO.setFields(esIdFields);
    mappingBOS.add(esIdMappingBO);

    MappingBO esVisibilityMappingBO = new MappingBO();
    esVisibilityMappingBO.setName("esVisibility");
    List<MappingFieldBO> esVisibilityFields = new LinkedList<>();
    esVisibilityFields.add(new MappingFieldBO("type", "keyword"));
    esVisibilityMappingBO.setFields(esVisibilityFields);
    mappingBOS.add(esVisibilityMappingBO);

    MappingBO esTitleMappingBO = new MappingBO();
    esTitleMappingBO.setName("esTitle");
    List<MappingFieldBO> esTitleFields = new LinkedList<>();
    esTitleFields.add(new MappingFieldBO("type", "text"));
    esTitleFields.add(new MappingFieldBO("analyzer", "ik_max_word"));
    esTitleMappingBO.setFields(esTitleFields);
    mappingBOS.add(esTitleMappingBO);

    MappingBO esContentMappingBO = new MappingBO();
    esContentMappingBO.setName("esContent");
    List<MappingFieldBO> esContentFields = new LinkedList<>();
    esContentFields.add(new MappingFieldBO("type", "text"));
    esContentFields.add(new MappingFieldBO("analyzer", "ik_max_word"));
    esContentMappingBO.setFields(esContentFields);
    mappingBOS.add(esContentMappingBO);

    MappingBO esLinkToMappingBO = new MappingBO();
    esLinkToMappingBO.setName("esLinkTo");
    List<MappingFieldBO> esLinkToFields = new LinkedList<>();
    esLinkToFields.add(new MappingFieldBO("type", "keyword"));
    esLinkToMappingBO.setFields(esLinkToFields);
    mappingBOS.add(esLinkToMappingBO);

    MappingBO esUpdateTimeMappingBO = new MappingBO();
    esUpdateTimeMappingBO.setName("esUpdateTime");
    List<MappingFieldBO> esUpdateTimeFields = new LinkedList<>();
//    esUpdateTimeFields.add(new MappingFieldBO("type", "date"));
    esUpdateTimeFields.add(new MappingFieldBO("type", "keyword"));
//    esUpdateTimeFields.add(new MappingFieldBO("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"));
    esUpdateTimeMappingBO.setFields(esUpdateTimeFields);
    mappingBOS.add(esUpdateTimeMappingBO);

    return mappingBOS;
  }
}
