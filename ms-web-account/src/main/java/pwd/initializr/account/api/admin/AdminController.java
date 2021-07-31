package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.AdminAccountInput;
import pwd.initializr.account.api.admin.vo.AdminAccountOutput;
import pwd.initializr.account.api.admin.vo.AdminContactCreateInput;
import pwd.initializr.account.api.admin.vo.AdminCreateInput;
import pwd.initializr.account.api.admin.vo.AdminUserInput;
import pwd.initializr.account.api.admin.vo.AdminUserOutput;
import pwd.initializr.account.business.admin.AdminAccountService;
import pwd.initializr.account.business.admin.AdminUserService;
import pwd.initializr.account.business.admin.AdminUserServiceWrap;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.AdminContactBO;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.account.business.session.SessionService;
import pwd.initializr.account.persistence.entity.AccountType;
import pwd.initializr.account.persistence.entity.AdminContactEntity;
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
 * <h1>控制层接口：管理员信息</h1>
 *
 * date 2019-10-26 8:14
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "后台人员管理",
    value = "adminManageApi",
    description = "[创建管理员/账号，人员/账号列表，人员/账号启/禁用，人员/账号删除]"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin")
@Slf4j
public class AdminController extends pwd.initializr.common.web.api.admin.AdminController implements
    AdminApi {

  @Autowired
  private AdminUserServiceWrap adminUserServiceWrap;
  @Autowired
  private AdminUserService adminUserService;
  @Autowired
  private AdminAccountService adminAccountService;
  @Autowired
  private SessionService sessionService;

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") AdminCreateInput input) {
    AdminUserBO adminUserBO = new AdminUserBO();
    AdminAccountBO adminAccountBO = new AdminAccountBO();
    BeanUtils.copyProperties(input.getUser(), adminUserBO);
    BeanUtils.copyProperties(input.getAccount(), adminAccountBO);
    Long userId = adminUserServiceWrap.insert(adminUserBO, adminAccountBO,input.getContacts().stream().map(this::convertAdminContactVOToBO).collect(Collectors.toList()));
    super.outputData(userId);
  }

  @Override
  public void delete(
      @Valid @NotNull(message = "参数不能为空") @Size(message = "参数不能为空") List<Long> ids) {
    // 同时移除 session
    ids.forEach(id -> sessionService.deleteSession(id));
    Integer del = adminUserServiceWrap.deleteByUserId(ids);
    outputData(del);
  }

  @Override
  public void disable(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    // 同时移除 session
    ids.forEach(id -> sessionService.deleteSession(id));
    Integer able = adminUserService.ableById(ids, EntityAble.DISABLE);
    outputData(200, able);
  }

  @Override
  public void enable(@Valid @NotNull(message = "参数不能为空") List<Long> ids) {
    Integer able = adminUserService.ableById(ids, EntityAble.ENABLE);
    outputData(200, able);
  }

  @Override
  public void update(@PathVariable("uid") Long id, @RequestBody AdminUserInput input) {
    AdminUserBO adminUserBO = new AdminUserBO();
    BeanUtils.copyProperties(input, adminUserBO);
    adminUserBO.setId(id);
    Integer update = adminUserService.update(adminUserBO);
    outputData(new Meta(), update);
  }

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<AdminUserBO> adminUserBOPageableQueryResult = adminUserService
        .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<AdminUserOutput> result = new PageOutput<>();
    adminUserBOPageableQueryResult.getElements().forEach(adminUserBO -> {
      AdminUserOutput adminUserOutput = new AdminUserOutput();
      BeanUtils.copyProperties(adminUserBO, adminUserOutput);
      result.getElements().add(adminUserOutput);
    });
    result.setTotal(adminUserBOPageableQueryResult.getTotal());
    result.setIndex(adminUserBOPageableQueryResult.getIndex());
    result.setSize(adminUserBOPageableQueryResult.getSize());
    outputData(result);
  }

  @Override
  public void get(@Valid @NotNull(message = "参数不能为空") Long uid) {
    AdminUserOutput adminUserOutput = new AdminUserOutput();
    AdminUserBO adminUserBO = adminUserService.queryById(uid);
    if (adminUserBO == null) {
      outputException(401);
      return;
    }
    BeanUtils.copyProperties(adminUserBO, adminUserOutput);
    outputData(adminUserOutput);
  }

  @Override
  public void resetPwd(@Valid @NotNull(message = "参数不能为空") Long uid) {
    AdminAccountBO adminAccountBO = adminAccountService
        .queryByTypeAndUserId(uid, AccountType.LoginNameAndLoginPwd);
    if (adminAccountBO == null) {
      outputException(400,"没有静态类型的账号");
    } else {
      this.resetAccountPwd(uid,adminAccountBO.getId());
    }
  }

  @Override
  public void createAccount(
      @Valid @NotNull(message = "参数不能为空") Long uid,
      @Valid @NotNull(message = "参数不能为空") Long type) {

  }

  @Override
  public void deleteAccount(
      @Valid @NotNull(message = "参数不能为空") Long uid,
      @Valid @NotNull(message = "参数不能为空") Long aid) {
    Integer existedAccountNum = adminAccountService.existedAccountNum(uid);
    if (existedAccountNum > 1) {
      Integer result = adminAccountService.deleteById(aid, uid);
      super.outputData(200, result);
    } else {
      super.outputData(new Meta(412, "用户的最后一个账号不可被删除"));
    }
  }

  @Override
  public void disableAccount(
      @Valid @NotNull(message = "参数不能为空") Long uid,
      @Valid @NotNull(message = "参数不能为空") Long aid) {
    Integer enabledAccountNum = adminAccountService.enabledAccountNum(uid);
    if (enabledAccountNum > 1) {
      Integer result = adminAccountService.ableById(aid, uid, EntityAble.DISABLE);
      super.outputData(200, result);
    } else {
      super.outputData(new Meta(412, "用户的最后一个账号不可被禁用"));
    }
  }

  @Override
  public void enableAccount(
      @Valid @NotNull(message = "参数不能为空") Long uid,
      @Valid @NotNull(message = "参数不能为空") Long aid) {
    Integer able = adminAccountService.ableById(aid, uid, EntityAble.ENABLE);
    outputData(200, able);
  }

  @Override
  public void updateAccount(
      @Valid @NotNull(message = "参数不能为空") Long uid,
      @Valid @NotNull(message = "参数不能为空") Long aid,
      @Valid @NotNull(message = "参数不能为空") AdminAccountInput input) {
    AdminAccountBO adminAccountBO = new AdminAccountBO();
    adminAccountBO.setId(aid);
    adminAccountBO.setLoginPwd(input.getLoginPwd());
    // TODO
  }

  @Override
  public void listAccount(@PathVariable("uid") Long uid) {
    List<AdminAccountBO> adminAccountBOS = adminAccountService.queryByUserId(uid);
    PageOutput<AdminAccountOutput> result = new PageOutput<>();
    adminAccountBOS.forEach(adminAccountBO -> {
      AdminAccountOutput adminAccountOutput = new AdminAccountOutput();
      BeanUtils.copyProperties(adminAccountBO, adminAccountOutput);
      result.getElements().add(adminAccountOutput);
    });
    outputData(result);
  }

  @Override
  public void getAccount(@Valid @NotNull(message = "用户ID不能为空") Long uid,
      @Valid @NotNull(message = "账号ID不能为空") Long aid) {
    AdminAccountBO adminAccountBO = adminAccountService.queryById(uid, aid);
    outputData(this.convertAdminAccountBO2VO(adminAccountBO));
  }

  @Override
  public void updateAccountPwd(@Valid @NotNull(message = "参数不能为空") Long uid,
      @Valid @NotNull(message = "参数不能为空") Long aid,
      @Valid @NotNull(message = "参数不能为空") AdminAccountInput input) {

  }

  @Override
  public void resetAccountPwd(@Valid @NotNull(message = "用户ID不能为空") Long uid,
      @Valid @NotNull(message = "账号ID不能为空") Long aid) {
    Integer integer = adminAccountService.resetPwd(uid, aid);
    outputData(integer);
  }



  private AdminAccountOutput convertAdminAccountBO2VO(AdminAccountBO bo) {
    AdminAccountOutput vo = new AdminAccountOutput();
    BeanUtils.copyProperties(bo,vo);
    return vo;
  }


  protected AdminContactBO convertAdminContactVOToBO (AdminContactCreateInput input) {
    AdminContactBO adminContactBO = new AdminContactBO();
    BeanUtils.copyProperties(input, adminContactBO);
    return adminContactBO;
  }
}
