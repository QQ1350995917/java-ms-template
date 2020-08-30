package pwd.initializr.account.api.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
import pwd.initializr.account.api.admin.vo.UserUserOutput;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.UserUserService;
import pwd.initializr.account.business.user.UserUserServiceWrap;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.api.vo.Meta;
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
  public void delAccount(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    // TODO 检查是否是最后一个账户
    Integer integer = userAccountService.deleteById(ids);
    outputData(new Meta(), integer);
  }

  @Override
  public void delUser(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    Boolean result = userUserServiceWrap.deleteByUserId(ids);
    outputData(result);
  }

  @Override
  public void disableAccount(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    // TODO 检查是否是最后一个账户
    Integer integer = userAccountService.ableById(ids, EntityAble.DISABLE);
    outputData(new Meta(), integer);
  }

  @Override
  public void disableUser(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    Boolean result = userUserServiceWrap.ableByUserId(ids, EntityAble.DISABLE);
    outputData(result);
  }

  @Override
  public void enableAccount(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    Integer integer = userAccountService.ableById(ids, EntityAble.ENABLE);
    outputData(new Meta(), integer);
  }

  @Override
  public void enableUser(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    Boolean result = userUserServiceWrap.ableByUserId(ids, EntityAble.ENABLE);
    outputData(result);
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
    LinkedHashSet<ScopeInput> scopeInputs = null;
    LinkedHashSet<SortInput> sortInputs = null;
    PageInput pageInput = null;
    try {
      scopeInputs = JSON.parseObject(scopes, new TypeReference<LinkedHashSet<ScopeInput>>() {
      });
      sortInputs = JSON.parseObject(sorts, new TypeReference<LinkedHashSet<SortInput>>() {
      });
      pageInput = JSON.parseObject(page, PageInput.class);
    } catch (Exception e) {
      throw new RuntimeException("参数格式错误");
    }
    if (pageInput == null) {
      pageInput = new PageInput();
    }

    // TODO 查询条件参数没有应用
    LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();

    LinkedHashSet<SortBO> sortBOS = new LinkedHashSet<>();

    PageOutput<UserUserOutput> responseData = new PageOutput<>();
    UserUserBO userUserBO = new UserUserBO();
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
