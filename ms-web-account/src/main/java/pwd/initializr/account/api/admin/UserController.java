package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import java.util.LinkedHashSet;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.UserAccountOutput;
import pwd.initializr.account.api.admin.vo.UserCreateInput;
import pwd.initializr.account.api.admin.vo.UserUserOutput;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.UserUserService;
import pwd.initializr.account.business.user.UserUserServiceWrap;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.account.persistence.entity.AdminAccountType;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：用户信息</h1>
 *
 * date 2019-09-14 21:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "系统用户管理",
    value = "userManageApi",
    description = "[用户/账户列表，用户/账户详情，用户/账户禁用/启用，用户/账户删除]"
)
@RestController(value = "userManageApi")
@RequestMapping(value = "/api/admin/user")
public class UserController extends AdminController implements UserApi {

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private UserUserService userUserService;

  @Autowired
  private UserUserServiceWrap userUserServiceWrap;

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") UserCreateInput input) {
    UserUserBO userUserBO = new UserUserBO();
    UserAccountBO userAccountBO = new UserAccountBO();
    BeanUtils.copyProperties(input.getUser(), userUserBO);
    BeanUtils.copyProperties(input.getAccount(), userAccountBO);
    userAccountBO.setType(AdminAccountType.GRANT.getNumber());
    UserAccountBO userAccountBOResult = userUserServiceWrap.insert(userUserBO, userAccountBO);
    super.outputData(userAccountBOResult.getUid());
  }

  @Override
  public void delUser(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    Integer result = userUserServiceWrap.deleteByUserId(ids);
    outputData(200, result);
  }

  @Override
  public void disableUser(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    Integer result = userUserService.ableById(ids, EntityAble.DISABLE);
    outputData(200, result);
  }

  @Override
  public void enableUser(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    Integer result = userUserService.ableById(ids, EntityAble.ENABLE);
    outputData(200, result);
  }

  @Override
  public void getUser(@Valid @NotNull(message = "参数不能为空") Long userId) {
    UserUserBO userUserBO = userUserService.queryById(userId);
    UserUserOutput userUserOutput = new UserUserOutput();
    if (userUserBO == null) {
      outputData(401);
      return;
    }
    BeanUtils.copyProperties(userUserBO, userUserOutput);
    outputData(userUserOutput);
  }

  @Override
  public void listAccount(@Valid @NotNull(message = "参数不能为空") Long userId) {
    PageOutput<UserAccountOutput> responseData = new PageOutput<>();
    PageableQueryResult<UserAccountBO> queryResult = userAccountService
        .queryAllByUserId(userId);
    queryResult.getElements().forEach(element -> {
      UserAccountOutput userAccountOutput = new UserAccountOutput();
      BeanUtils.copyProperties(element, userAccountOutput);
      responseData.getElements().add(userAccountOutput);
    });
    outputData(responseData);
  }

  @Override
  public void listUser(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageOutput<UserUserOutput> responseData = new PageOutput<>();
    PageableQueryResult<UserUserBO> queryResult = userUserService
        .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    queryResult.getElements().forEach(element -> {
      UserUserOutput userUserOutput = new UserUserOutput();
      BeanUtils.copyProperties(element, userUserOutput);
      responseData.getElements().add(userUserOutput);
    });
    responseData.setSize(queryResult.getSize());
    responseData.setIndex(queryResult.getIndex());
    responseData.setTotal(queryResult.getTotal());
    outputData(responseData);
  }

}
