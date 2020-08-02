package pwd.initializr.search.business.admin;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.admin.bo.IndexBO;
import pwd.initializr.search.business.admin.bo.MappingBO;
import pwd.initializr.search.business.admin.bo.MappingFieldBO;

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
@Service
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public PageableQueryResult<IndexBO> list() {
        PageableQueryResult<IndexBO> indexBOPageableQueryResult = new PageableQueryResult<>();
        GetIndexResponse getIndexResponse = elasticsearchTemplate.getClient().admin().indices()
            .prepareGetIndex().get();
        String[] indices = getIndexResponse.getIndices();
        ImmutableOpenMap<String, Settings> settings = getIndexResponse.getSettings();
        ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetaData>> mappings = getIndexResponse
            .getMappings();
        Stream.of(Optional.ofNullable(indices).orElseGet(new Supplier<String[]>() {
            @Override
            public String[] get() {
                return new String[0];
            }
        })).forEach(index -> {
            IndexBO indexBO = new IndexBO();
            indexBO.setUuid(settings.get(index).get("index.uuid"));
            indexBO.setName(settings.get(index).get("index.provided_name"));
            indexBO.setReplicas(settings.get(index).get("index.number_of_replicas"));
            indexBO.setShards(settings.get(index).get("index.number_of_shards"));
            indexBO.setDate(settings.get(index).get("index.creation_date"));
            indexBO.setVersion(settings.get(index).get("index.version.created"));
            MappingMetaData mappingMetaData = mappings.get(index).get(index);
            if (mappingMetaData != null) {
                Object properties = mappingMetaData.getSourceAsMap().get("properties");
                if (properties != null && properties instanceof Map) {
                    Map<String,Object> propertiesMap = (Map<String,Object>) properties;
                    List<MappingBO> mappingBOS = new LinkedList<>();
                    propertiesMap.forEach((key,value) -> {
                        Map<String, String> valueMap = (Map<String, String>) value;
                        List<MappingFieldBO> mappingFieldBOS = new LinkedList<>();
                        valueMap.forEach((fieldKey,fieldValue) ->{
                            MappingFieldBO mappingFieldBO = new MappingFieldBO();
                            mappingFieldBO.setName(fieldKey);
                            mappingFieldBO.setValue(fieldValue);
                            mappingFieldBOS.add(mappingFieldBO);
                        });
                        MappingBO mappingBO = new MappingBO();
                        mappingBO.setName(key);
                        mappingBO.setFields(mappingFieldBOS);
                        mappingBOS.add(mappingBO);
                    });
                    indexBO.setProperties(mappingBOS);
                }
                indexBOPageableQueryResult.getElements().add(indexBO);
            }
        });
        return indexBOPageableQueryResult;
    }

    @Override
    public boolean create(String index) {
        return create(index, getDefaultMapping());
    }

    @Override
    public boolean create(String index, List<MappingBO> mappingBOS) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .startObject("properties");
            Optional.ofNullable(mappingBOS).orElseGet(LinkedList::new).forEach(mappingBO -> {
                try {
                    builder.startObject(mappingBO.getName());
                    Optional.ofNullable(mappingBO.getFields()).orElseGet(LinkedList::new)
                        .forEach(field -> {
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

            PutMappingResponse putMappingResponse = elasticsearchTemplate.getClient().admin()
                .indices()
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
