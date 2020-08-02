package pwd.initializr.search.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.robot.RobotController;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.admin.MetadataService;
import pwd.initializr.search.business.admin.bo.IndexBO;

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
@RestController(value = "metadataApi")
@RequestMapping(value = "/api/admin/search/metadata")
public class MetadataController extends RobotController implements MetadataApi {

    @Autowired
    private MetadataService metadataService;

    @Override
    public void listIndices() {
        PageableQueryResult<IndexBO> list = metadataService.list();
        super.outputData(list);
    }

    @Override
    public void postIndex() {

    }

    @Override
    public void putIndex() {

    }

    @Override
    public void deleteIndices() {

    }
}
