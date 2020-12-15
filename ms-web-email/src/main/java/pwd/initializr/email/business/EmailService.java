package pwd.initializr.email.business;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.persistence.entity.EmailEntity;
import pwd.initializr.email.persistence.entity.EmailSendType;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-05 16:30
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface EmailService {

    /**
     * <h2>通过主键删除数据</h2>
     * date 2020-12-14 15:55
     *
     * @param id 主键
     * @return java.lang.Integer 影响行数
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    Integer deleteById(Long id);

    /**
     * <h2>通过主键批量删除数据</h2>
     * date 2020-12-14 15:55
     *
     * @param ids 主键
     * @return java.lang.Integer 影响行数
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    Integer deleteById(Set<Long> ids);

    /**
     * <h2>新增数据</h2>
     * date 2020-12-14 15:55
     *
     * @param bo 业务对象
     * @return java.lang.Integer 影响行数
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    EmailBO insert(EmailBO bo);

    /**
     * <h2>新增数据（批量操作）</h2>
     * date 2020-12-14 15:55
     *
     * @param bos 业务对象
     * @return java.lang.Integer 影响行数
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    void insert(List<EmailBO> bos);

    /**
     * <h2>新增或者替换数据</h2>
     * date 2020-12-14 15:55
     *
     * @param bo 业务对象
     * @return java.lang.Integer 影响行数
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    void insertOrReplace(EmailBO bo);

    /**
     * <h2>新增或者替换数据（批量操作）</h2>
     * date 2020-12-14 15:55
     *
     * @param bos 业务对象
     * @return java.lang.Integer 影响行数
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    void insertOrReplace(List<EmailBO> bos);

    /**
     * <h2>根据条件查询多条数据</h2>
     * date 2020-12-14 15:55
     *
     * @param scopes 查询条件
     * @param sorts 排序条件
     * @param pageIndex 页码
     * @param pageSize 容量
     * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.email.business.bo.EmailBO>
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    PageableQueryResult<EmailBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
        LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize);

    /**
     * <h2>异步发送邮件查询接口，阻塞式查询，使用该接口前最好清楚业务正在做什么</h2>
     * date 2020-12-14 19:15
     *
     * @param pageSize 查询条数
     * @return java.util.List<pwd.initializr.email.persistence.entity.EmailEntity>
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    List<EmailBO> queryBySynchronizationForSender(Long pageSize);

    /**
     * <h2>根据ID批量更新发送状态</h2>
     * date 2020-12-14 19:16
     *
     * @param ids 邮件ID
     * @param sendType 发送状态
     * @return java.lang.Integer
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    Integer updateSentStatusByBatch(Set<Long> ids, EmailSendType sendType);

    /**
     * <h2>通过ID查询单条数据</h2>
     * date 2020-12-14 15:55
     *
     * @param id 主键
     * @return pwd.initializr.email.business.bo.EmailBO 业务对象
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    EmailBO queryById(Long id);

    /**
     * <h2>根据ID更新数据</h2>
     * date 2020-12-14 15:55
     *
     * @param bo 业务对象
     * @return java.lang.Integer 影响行数
     * @author Automatic[www.dingpengwei@foxmail.com]
     * @since 2020-12-14 15:55
     */
    Integer updateById(EmailBO bo);

}
