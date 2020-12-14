package pwd.initializr.email.api.robot;

import io.swagger.annotations.Api;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.email.api.robot.vo.EmailContentInput;
import pwd.initializr.email.api.robot.vo.EmailContentOutput;
import pwd.initializr.email.business.EmailContentService;
import pwd.initializr.email.business.bo.EmailContentBO;

/**
 * project-generator-test@ms-web-initializr
 *
 * <h1>EmailContent控制层接口实现</h1>
 *
 * date 2020-12-14 16:13
 *
 * @author Automatic[dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Api(
    tags = "EmailContent信息结构",
    value = "EmailContentManageApi",
    description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "EmailContent")
@RequestMapping(value = "/api/robot/email/content")
@Slf4j
public class EmailContentController extends
    pwd.initializr.common.web.api.admin.AdminController implements EmailContentApi {

    @Autowired
    private EmailContentService service;

    @Override
    public void list(String scopes, String sorts, String page) {
        PageInput pageInput = PageInput.parse(page);
        LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
        LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
        PageableQueryResult<EmailContentBO> pageableQueryResult = service
            .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
        PageOutput<EmailContentOutput> result = new PageOutput<>();
        pageableQueryResult.getElements().forEach(bo -> {
            EmailContentOutput output = new EmailContentOutput();
            BeanUtils.copyProperties(bo, output);
            result.getElements().add(output);
        });
        result.setTotal(pageableQueryResult.getTotal());
        result.setIndex(pageableQueryResult.getIndex());
        result.setSize(pageableQueryResult.getSize());
        outputData(result);
    }

    @Override
    public void detail(@Valid @NotNull(message = "参数不能为空") Long id) {
        EmailContentBO bo = service.queryById(id);
        EmailContentOutput output = new EmailContentOutput();
        BeanUtils.copyProperties(bo, output);
        outputData(output);
    }

    @Override
    public void enable(@Valid @NotNull(message = "参数不能为空") Long id) {
        Integer result = service.ableById(id, EntityAble.ENABLE);
        outputData(200, result);
    }

    @Override
    public void enable(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
        Integer result = service.ableById(ids, EntityAble.ENABLE);
        outputData(200, result);
    }

    @Override
    public void disable(@Valid @NotNull(message = "参数不能为空") Long id) {
        Integer result = service.ableById(id, EntityAble.DISABLE);
        outputData(200, result);
    }

    @Override
    public void disable(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
        Integer result = service.ableById(ids, EntityAble.DISABLE);
        outputData(200, result);
    }

    @Override
    public void delete(@Valid @NotNull(message = "参数不能为空") Long id) {
        Integer result = service.deleteById(id);
        outputData(200, result);
    }

    @Override
    public void delete(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
        Integer result = service.deleteById(ids);
        outputData(200, result);
    }

    @Override
    public void create(@Valid @NotNull(message = "参数不能为空") EmailContentInput input) {
        EmailContentBO bo = new EmailContentBO();
        BeanUtils.copyProperties(input, bo);
        service.insert(bo);
        outputData(200, bo.getEmailId());
    }

    @Override
    public void create(@Valid @NotNull(message = "参数不能为空") List<EmailContentInput> input) {
        LinkedList<EmailContentBO> bos = new LinkedList<>();
        for (EmailContentInput item : input) {
            EmailContentBO bo = new EmailContentBO();
            BeanUtils.copyProperties(item, bo);
            bos.add(bo);
        }
        service.insert(bos);
        outputData(200);
    }

    @Override
    public void createOrReplace(@Valid @NotNull(message = "参数不能为空") EmailContentInput input) {
        EmailContentBO bo = new EmailContentBO();
        BeanUtils.copyProperties(input, bo);
        service.insertOrReplace(bo);
        outputData(200, bo.getEmailId());
    }

    @Override
    public void createOrReplace(@Valid @NotNull(message = "参数不能为空") List<EmailContentInput> input) {
        LinkedList<EmailContentBO> bos = new LinkedList<>();
        for (EmailContentInput item : input) {
            EmailContentBO bo = new EmailContentBO();
            BeanUtils.copyProperties(item, bo);
            bos.add(bo);
        }
        service.insertOrReplace(bos);
        outputData(200);
    }

    @Override
    public void update(@Valid @NotNull(message = "参数不能为空") Long id,
        @Valid @NotNull(message = "参数不能为空") EmailContentInput input) {
        EmailContentBO bo = new EmailContentBO();
        BeanUtils.copyProperties(input, bo);
        Integer result = service.updateById(bo);
        outputData(200, result);
    }

}
