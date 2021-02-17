package pwd.initializr.edu.api.admin;

import io.swagger.annotations.Api;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
import pwd.initializr.edu.api.admin.vo.WordCollectionInput;
import pwd.initializr.edu.api.admin.vo.WordCollectionOutput;
import pwd.initializr.edu.business.WordCollectionService;
import pwd.initializr.edu.business.bo.WordCollectionBO;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
* pwd-initializr-app-20210216215106880@ms-web-initializr
*
* <h1>WordCollection控制层接口实现</h1>
*
* date 2021-02-16 21:51
*
* @author Automatic[dingpengwei@foxmail.com]
* @version 0.0.1-SNAPSHOT
* @since 0.0.1-SNAPSHOT
*/
@Api(
  tags = "WordCollection信息结构",
  value = "WordCollectionManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "WordCollection")
@RequestMapping(value = "/api/WordCollection")
@Slf4j
public class WordCollectionController extends pwd.initializr.common.web.api.admin.AdminController implements WordCollectionApi {

  @Autowired
  private WordCollectionService service;

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<WordCollectionBO> pageableQueryResult = service
      .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<WordCollectionOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      WordCollectionOutput output = new WordCollectionOutput();
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
    WordCollectionBO bo = service.queryById(id);
    WordCollectionOutput output = new WordCollectionOutput();
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
  public void create(@Valid @NotNull(message = "参数不能为空") WordCollectionInput input) {
    WordCollectionBO bo = new WordCollectionBO();
    BeanUtils.copyProperties(input,bo);
    Long id = service.insert(bo);
    outputData(200,id);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") List<WordCollectionInput> input) {
    List<WordCollectionBO> bos = input.stream().map(this::convertWordCollectionInput2WordCollectionBO)
      .collect(Collectors.toList());
    service.insert(bos);
    outputData(200);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") WordCollectionInput input) {
    WordCollectionBO bo = new WordCollectionBO();
    BeanUtils.copyProperties(input,bo);
    Long id = service.insertOrReplace(bo);
    outputData(200,id);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") List<WordCollectionInput> input) {
    List<WordCollectionBO> bos = input.stream().map(this::convertWordCollectionInput2WordCollectionBO)
      .collect(Collectors.toList());
    service.insertOrReplace(bos);
    outputData(200);
  }

  @Override
  public void update(@Valid @NotNull(message = "参数不能为空") Long id,
  @Valid @NotNull(message = "参数不能为空") WordCollectionInput input) {
    WordCollectionBO bo = new WordCollectionBO();
    BeanUtils.copyProperties(input,bo);
    Integer result = service.updateById(bo);
    outputData(200,result);
  }

  private WordCollectionBO convertWordCollectionInput2WordCollectionBO(WordCollectionInput input){
    WordCollectionBO bo = new WordCollectionBO();
    BeanUtils.copyProperties(input,bo);
    return bo;
  }

}
