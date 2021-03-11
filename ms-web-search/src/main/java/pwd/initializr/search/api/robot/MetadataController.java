package pwd.initializr.search.api.robot;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.utils.StringUtils;
import pwd.initializr.common.web.api.robot.RobotController;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.MetadataService;
import pwd.initializr.search.business.bo.IndexBO;

/**
 * pwd.initializr.search.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-16 18:32
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController(value = "robotMetadataApi")
@RequestMapping(value = "/api/robot/metadata")
public class MetadataController extends RobotController implements MetadataApi {

    @Resource
    private MetadataService metadataService;

    @Override
    public void listIndices(String indexName) {
        PageableQueryResult<IndexBO> indexBOPageableQueryResult = metadataService
            .listIndex(StringUtils.isBlank(indexName) ? "*" : indexName);
        outputData(indexBOPageableQueryResult);
    }

    @Override
    public void createIndex(@Valid @NotNull(message = "参数不能为空") String indexName) {
        boolean index = metadataService.createIndex(indexName);
        if (index) {
            outputData(200);
        } else {
            outputException(500);
        }
    }

    @Override
    public void deleteIndices(@Valid @NotNull(message = "参数不能为空") String indexName) {
        if (metadataService.deleteIndex(indexName)) {
            outputData(200);
        } else {
            outputException(500);
        }
    }
}
