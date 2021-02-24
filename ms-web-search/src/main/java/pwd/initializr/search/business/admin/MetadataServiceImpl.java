package pwd.initializr.search.business.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
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
 *
 *                 XContentBuilder builder = XContentFactory.jsonBuilder();
 *                 builder.startObject();
 *                 {
 *                     builder.startObject("properties");
 *                     {
 *                         builder.startObject("name");
 *                         {
 *                             builder.field("type", "text");
 *                             builder.field("analyzer", "ik_smart");
 *                         }
 *                         builder.endObject();
 *                     }
 *                     {
 *                         builder.startObject("age");
 *                         {
 *                             builder.field("type", "keyword");
 *                         }
 *                         builder.endObject();
 *                     }
 *                     {
 *                         builder.startObject("desc");
 *                         {
 *                             builder.field("type", "text");
 *                             builder.field("analyzer", "ik_smart");
 *                         }
 *                         builder.endObject();
 *                     }
 *                     {
 *                         builder.startObject("id");
 *                         {
 *                                 builder.field("type", "integer");
 *                         }
 *                         builder.endObject();
 *                 }
 *                 builder.endObject();
 *                 }
 *                 builder.endObject();
 */
@Service
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    // https://blog.csdn.net/chengyuqiang/article/details/102938266?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-2&spm=1001.2101.3001.4242
    @Override
    public PageableQueryResult<IndexBO> listIndex() {
        PageableQueryResult<IndexBO> indexBOPageableQueryResult = new PageableQueryResult<>();
//        IndicesClient indices = elasticsearchRestTemplate.getClient().indices();
//        GetIndexResponse getIndexResponse = elasticsearchRestTemplate.getClient().indices()
//            .prepareGetIndex().get();

        IndicesClient indices1 = elasticsearchRestTemplate.getClient().indices();

        GetIndexResponse getIndexResponse = null;
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
                        Set<MappingFieldBO> mappingFieldBOS = new HashSet<>();
                        valueMap.forEach((fieldKey,fieldValue) ->{
                            MappingFieldBO mappingFieldBO = new MappingFieldBO();
                            mappingFieldBO.setKey(fieldKey);
                            mappingFieldBO.setValue(fieldValue);
                            mappingFieldBOS.add(mappingFieldBO);
                        });
                        MappingBO mappingBO = new MappingBO();
                        mappingBO.setFieldName(key);
                        mappingBO.setFieldTypes(mappingFieldBOS);
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
    public boolean existIndex(String indexName) {
        return elasticsearchRestTemplate.indexExists(indexName);
    }

    @Override
    public boolean createIndex(String indexName) {
        return createIndex(indexName, getDefaultMapping());
    }

    @Override
    public boolean createIndex(String indexName, List<MappingBO> mappingBOS) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .startObject("properties");
            Optional.ofNullable(mappingBOS).orElseGet(LinkedList::new).forEach(mappingBO -> {
                try {
                    builder.startObject(mappingBO.getFieldName());
                    Optional.ofNullable(mappingBO.getFieldTypes()).orElseGet(HashSet::new)
                        .forEach(field -> {
                                try {
                                    builder.field(field.getKey(), field.getValue());
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
            boolean index = elasticsearchRestTemplate.createIndex(indexName);
            boolean mapping = elasticsearchRestTemplate.putMapping(indexName, indexName, builder);
            if (!mapping) {
                deleteIndex(indexName);
            }
            return index && mapping;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteIndex(String indexName) {
        return elasticsearchRestTemplate.deleteIndex(indexName);
    }

    @Override
    public List<MappingBO> getDefaultMapping() {
        List<MappingBO> mappingBOS = new LinkedList<>();
        mappingBOS.add(new MappingBO("esId", Arrays.asList(new MappingFieldBO("type","keyword")).stream().collect(Collectors.toSet())));
        mappingBOS.add(new MappingBO("esVisibility", Arrays.asList(new MappingFieldBO("type","keyword")).stream().collect(Collectors.toSet())));
        mappingBOS.add(new MappingBO("esTitle", Arrays.asList(new MappingFieldBO("type","text"),new MappingFieldBO("analyzer","ik_max_word")).stream().collect(Collectors.toSet())));
        mappingBOS.add(new MappingBO("esContent", Arrays.asList(new MappingFieldBO("type","text"),new MappingFieldBO("analyzer","ik_max_word")).stream().collect(Collectors.toSet())));
        mappingBOS.add(new MappingBO("esLinkTo", Arrays.asList(new MappingFieldBO("type","keyword")).stream().collect(Collectors.toSet())));
        mappingBOS.add(new MappingBO("esUpdateTime", Arrays.asList(new MappingFieldBO("type","date"),new MappingFieldBO("format","yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")).stream().collect(Collectors.toSet())));
        return mappingBOS;
    }
}
