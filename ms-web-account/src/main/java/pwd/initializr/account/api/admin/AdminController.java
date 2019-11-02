package pwd.initializr.account.api.admin;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-26 8:14
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "管理员管理",
    value = "adminManageApi",
    description = "管理员管理API"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin")
public class AdminController extends pwd.initializr.common.web.api.admin.AdminController implements AdminApi {

  @ApiOperation(value = "查询信息")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void list() {

  }


  @Override
  public void create() {

  }

  @Override
  public void enable() {

  }

  @Override
  public void disable() {

  }
}
