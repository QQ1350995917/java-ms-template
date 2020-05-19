package pwd.initializr.search.api.robot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.robot.RobotController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.search.api.robot.vo.ArticleIntoSearchInputVO;
import pwd.initializr.search.api.robot.vo.BookIntoSearchInputVO;
import pwd.initializr.search.api.robot.vo.SearchInputVo;
import pwd.initializr.search.api.robot.vo.SearchOutputVO;
import pwd.initializr.search.business.robot.BookService;
import pwd.initializr.search.business.robot.bo.ArticleBO;
import pwd.initializr.search.business.robot.bo.BookBO;
import pwd.initializr.search.rpc.RPCSearchOutput;

/**
 * pwd.initializr.search.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "图书信息入搜索",
    value = "bookApi",
    description = "图书信息入搜索API"
)
@RestController(value = "bookApi")
@RequestMapping(value = "/api/robot/book")
public class BookController extends RobotController implements BookApi {

  @Autowired
  private BookService bookService;

  @ApiOperation(value = "向ES添加/更新文章")
  @PostMapping(value = {"/article"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void postOrPutArticle(@RequestBody ArticleIntoSearchInputVO input) {
    ArticleBO articleBO = new ArticleBO();
    BeanUtils.copyProperties(input, articleBO);
    bookService.postOrPutArticle(articleBO);
    outputData(200);
  }

  @ApiOperation(value = "向ES添加/更新图书")
  @PostMapping(value = {"/book"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void postOrPutBook(@RequestBody BookIntoSearchInputVO input) {
    BookBO bookBO = new BookBO();
    BeanUtils.copyProperties(input, bookBO);
    bookService.postOrPutBook(bookBO);
    outputData(200);
  }

  @ApiOperation(value = "在ES中搜索图书/文章")
  @GetMapping(value = {"/search"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void search(SearchInputVo input) {
    ObjectList<? extends RPCSearchOutput> search = bookService
        .search(input.getKeyword(), input.getIndex(), input.getSize());
    ObjectList<SearchOutputVO> result = new ObjectList<>();
    result.setSize(search.getPages());
    result.setIndex(search.getIndex());
    result.setPages(search.getPages());
    result.setTotal(search.getTotal());
    List<SearchOutputVO> elements = new LinkedList<>();
    search.getElements().forEach(articleBO -> {
      SearchOutputVO searchOutputVO = new SearchOutputVO();
      BeanUtils.copyProperties(articleBO, searchOutputVO);
      elements.add(searchOutputVO);
    });
    result.setElements(elements);
    outputData(search);
  }

}
