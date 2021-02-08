package pwd.initializr.edu.api.admin;

import io.swagger.annotations.Api;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.edu.api.admin.vo.ArticleContentInput;
import pwd.initializr.edu.api.admin.vo.ArticleContentOutput;
import pwd.initializr.edu.business.ArticleContentService;
import pwd.initializr.edu.business.bo.ArticleContentBO;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
* project-generator-test-20210204160711060@ms-web-initializr
*
* <h1>ArticleContent控制层接口实现</h1>
*
* date 2021-02-04 16:07
*
* @author Automatic[dingpengwei@foxmail.com]
* @version 0.0.1-SNAPSHOT
* @since 0.0.1-SNAPSHOT
*/
@Api(
  tags = "ArticleContent信息结构",
  value = "ArticleContentManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "ArticleContent")
@RequestMapping(value = "/api/ArticleContent")
@Slf4j
public class ArticleContentController extends pwd.initializr.common.web.api.admin.AdminController implements ArticleContentApi {

  @Autowired
  private ArticleContentService service;

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<ArticleContentBO> pageableQueryResult = service
      .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<ArticleContentOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      ArticleContentOutput output = new ArticleContentOutput();
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
    ArticleContentBO bo = service.queryById(id);
    ArticleContentOutput output = new ArticleContentOutput();
    BeanUtils.copyProperties(bo,output);
    outputData(output);
  }

  @Override
  public void enable(@Valid @NotNull(message = "参数不能为空") Long id) {
    Integer result = service.ableById(id, EntityAble.ENABLE);
    outputData(200,result);
  }

  @Override
  public void enable(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
    Integer result = service.ableById(ids, EntityAble.ENABLE);
    outputData(200,result);
  }

  @Override
  public void disable(@Valid @NotNull(message = "参数不能为空") Long id) {
    Integer result = service.ableById(id, EntityAble.DISABLE);
    outputData(200,result);
  }

  @Override
  public void disable(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
    Integer result = service.ableById(ids, EntityAble.DISABLE);
    outputData(200,result);
  }

  @Override
  public void delete(@Valid @NotNull(message = "参数不能为空") Long id) {
    Integer result = service.deleteById(id);
    outputData(200,result);
  }

  @Override
  public void delete(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
    Integer result = service.deleteById(ids);
    outputData(200,result);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") ArticleContentInput input) {
    ArticleContentBO bo = new ArticleContentBO();
    BeanUtils.copyProperties(input,bo);
    Long id = service.insert(bo);
    outputData(200,id);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") List<ArticleContentInput> input) {
    List<ArticleContentBO> bos = input.stream().map(this::convertArticleContentInput2ArticleContentBO)
      .collect(Collectors.toList());
    service.insert(bos);
    outputData(200);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") ArticleContentInput input) {
    ArticleContentBO bo = new ArticleContentBO();
    BeanUtils.copyProperties(input,bo);
    Long id = service.insertOrReplace(bo);
    outputData(200,id);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") List<ArticleContentInput> input) {
    List<ArticleContentBO> bos = input.stream().map(this::convertArticleContentInput2ArticleContentBO)
      .collect(Collectors.toList());
    service.insertOrReplace(bos);
    outputData(200);
  }

  @Override
  public void update(@Valid @NotNull(message = "参数不能为空") Long id,
  @Valid @NotNull(message = "参数不能为空") ArticleContentInput input) {
    ArticleContentBO bo = new ArticleContentBO();
    BeanUtils.copyProperties(input,bo);
    Integer result = service.updateById(bo);
    outputData(200,result);
  }

  private ArticleContentBO convertArticleContentInput2ArticleContentBO(ArticleContentInput input){
    ArticleContentBO bo = new ArticleContentBO();
    BeanUtils.copyProperties(input,bo);
    return bo;
  }

}
