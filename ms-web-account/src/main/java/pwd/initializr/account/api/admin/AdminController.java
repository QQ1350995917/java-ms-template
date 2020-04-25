package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.CreateAdminInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-26 8:14
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "管理员管理",
    value = "adminManageApi",
    description = "[创建管理员，管理员列表，管理员启用，管理员禁用]"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin")
public class AdminController extends pwd.initializr.common.web.api.admin.AdminController implements
    AdminApi {

  @ApiOperation(value = "管理员列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void list() {

  }

  @ApiOperation(value = "创建管理员")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void create(@RequestBody CreateAdminInput input) {

  }

  @ApiOperation(value = "启用")
  @PatchMapping(value = {"/enable/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void enable(@PathVariable Long id) {

  }

  @ApiOperation(value = "禁用")
  @PatchMapping(value = {"/disable/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void disable(Long id) {

  }
}
