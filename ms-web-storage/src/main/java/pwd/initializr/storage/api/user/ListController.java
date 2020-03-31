package pwd.initializr.storage.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.ApiController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.storage.api.user.vo.ListInput;
import pwd.initializr.storage.business.QueryService;
import pwd.initializr.storage.business.bo.StorageBO;

/**
 * pwd.initializr.storage.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-26 18:17
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文件列表",
    value = "文件列表Api",
    description = "文件列表API"
)
@RestController(value = "listApi")
@RequestMapping(value = "/api/user/list")
public class ListController extends ApiController implements ListApi {

  @Autowired
  private QueryService queryService;

  @ApiOperation(value = "文件列表")
  @GetMapping(value = {""})
  @Override
  public void list(ListInput input) {
    ObjectList<StorageBO> storageObjectList = queryService.listFile();
    super.outputData(storageObjectList);
  }

}
