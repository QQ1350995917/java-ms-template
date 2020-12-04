package pwd.initializr.email.api;

import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.email.api.vo.EmailInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：管理员信息</h1>
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
    description = "[创建管理员/账号，人员/账号列表，人员/账号启/禁用，人员/账号删除]"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin/admin")
@Slf4j
public class EmailController extends pwd.initializr.common.web.api.admin.AdminController implements
    EmailApi {


  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") EmailInput input) {
  }

  @Override
  public void delAccount(
      @Valid @NotNull(message = "参数不能为空") Long userId,
      @Valid @NotNull(message = "参数不能为空") Long accountId) {

  }

  @Override
  public void delUser(
      @Valid @NotNull(message = "参数不能为空") @Size(message = "参数不能为空") List<Long> ids) {

  }

  @Override
  public void disableAccount(
      @Valid @NotNull(message = "参数不能为空") Long userId,
      @Valid @NotNull(message = "参数不能为空") Long accountId) {

  }

  @Override
  public void disableUser(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {

  }

  @Override
  public void enableAccount(
      @Valid @NotNull(message = "参数不能为空") Long userId,
      @Valid @NotNull(message = "参数不能为空") Long accountId) {

  }

  @Override
  public void enableUser(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {

  }

  @Override
  public void getUser(@Valid @NotNull(message = "参数不能为空") Long userId) {

  }


  @Override
  public void listAccount(@PathVariable("uid") Long userId) {

  }

  @Override
  public void listUser(String scopes, String sorts, String page) {

  }

  @Override
  public void updateAccount(@PathVariable("id") Long id, @RequestBody EmailInput input) {

  }

  @Override
  public void updateUser(@PathVariable("uid") Long id, @RequestBody EmailInput input) {

  }
}
