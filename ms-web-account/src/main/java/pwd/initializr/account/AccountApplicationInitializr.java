package pwd.initializr.account;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.swing.text.html.parser.Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.admin.AdminConfigService;
import pwd.initializr.account.business.admin.AdminUserService;
import pwd.initializr.account.persistence.entity.AdminConfigEntity;
import pwd.initializr.account.persistence.entity.AdminUserEntity;
import pwd.initializr.common.web.persistence.entity.EntityDel;
import pwd.initializr.common.web.persistence.entity.EntityEnable;

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

    @PostConstruct
    @Transactional
    public void initializr() {
        AdminConfigEntity adminConfigEntity = adminConfigService.queryByKey(INITIALIZED_FLAG);
        if (adminConfigEntity == null || "false".equals(adminConfigEntity.getValue())) {
            // TODO 执行初始化（加强判断机制，防止误操作）
            // TODO 删除配置表 -> 新增配置表

            adminConfigEntity = new AdminConfigEntity();
            adminConfigEntity.setKey(INITIALIZED_FLAG);
            adminConfigEntity.setValue("true");
            adminConfigEntity.setSummary("标识是否对系统进行业务初始化操作，二次启动以此判断是否初始化数据（true：已初始化，false：未初始化）");
            adminConfigEntity.setAble(EntityEnable.ENABLE.getNumber());
            adminConfigEntity.setDel(EntityDel.NO.getNumber());
            adminConfigEntity.setCreateTime(new Date());
            adminConfigEntity.setUpdateTime(new Date());

            adminConfigService.insert(adminConfigEntity);

            // TODO 删除用户表 -> 新增用户表

            AdminUserEntity adminUserEntity = new AdminUserEntity();

        }
    }
}
