package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.CreateAdminInput;
import pwd.initializr.account.business.admin.AdminService;
import pwd.initializr.account.business.admin.bo.AdminBO;
import pwd.initializr.common.web.business.bo.ObjectList;

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

  @Autowired
  private AdminService adminService;

  @ApiOperation(value = "管理员列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void list() {
    ObjectList<AdminBO> adminBOObjectList = adminService.queryAllByLimit(0, 12);
    super.outputData(adminBOObjectList);
  }

  @ApiOperation(value = "创建管理员")
  @PostMapping(value = {
      ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void create(CreateAdminInput input) {
    AdminBO adminBO = new AdminBO();
    BeanUtils.copyProperties(input, adminBO);
    AdminBO insert = adminService.insert(adminBO);
    super.outputData(insert.getId());
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
