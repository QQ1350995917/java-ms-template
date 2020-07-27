package pwd.initializr.account;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.admin.AdminAccountService;
import pwd.initializr.account.business.admin.AdminConfigService;
import pwd.initializr.account.business.admin.AdminUserService;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.AdminConfigBO;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.common.web.persistence.entity.EntityDel;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account@ms-web-initializr
 *
 * <h1>系统业务自检</h1>
 *
 * date 2020-07-25 10:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Slf4j
public class AccountApplicationInitializr {

    private static final String INITIALIZED_FLAG = "System-Initialized";

    @Autowired
    private AdminConfigService adminConfigService;
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminAccountService adminAccountService;

    @PostConstruct
    @Transactional(rollbackFor = RuntimeException.class)
    public void initializr() {
        AdminConfigBO adminConfigBO = adminConfigService.queryByKey(INITIALIZED_FLAG);
        if (adminConfigBO == null || "false".equals(adminConfigBO.getValue())) {
            // TODO 执行初始化（加强判断机制，防止误操作）

            // TODO 删除配置表 -> 新增配置表
            adminConfigBO = new AdminConfigBO();
            adminConfigBO.setKey(INITIALIZED_FLAG);
            adminConfigBO.setValue("true");
            adminConfigBO.setSummary("标识是否对系统进行业务初始化操作，二次启动以此判断是否初始化数据（true：已初始化，false：未初始化）");
            adminConfigBO.setAble(EntityAble.ENABLE.getNumber());
            adminConfigBO.setDel(EntityDel.NO.getNumber());

            adminConfigService.insert(adminConfigBO);

            // TODO 删除用户表 -> 新增用户表
            AdminUserBO adminUserBO = new AdminUserBO();
            adminUserBO.setPin("0");
            adminUserBO.setName("LuoGuanZhong");
            adminUserBO.setGender("1");
            adminUserBO.setAble(EntityAble.ENABLE.getNumber());
            adminUserBO.setDel(EntityDel.NO.getNumber());
            AdminUserBO adminUserBOResult = adminUserService.insert(adminUserBO);

            AdminAccountBO adminAccountBO = new AdminAccountBO();
            adminAccountBO.setUid(adminUserBOResult.getId());
            adminAccountBO.setLoginName("luoguanzhong");
            adminAccountBO.setLoginPwd("luoguanzhong");
            adminAccountBO.setEnable(EntityAble.ENABLE.getNumber());
            adminAccountBO.setDel(EntityDel.NO.getNumber());
            adminAccountService.insert(adminAccountBO);

        }
    }
}
