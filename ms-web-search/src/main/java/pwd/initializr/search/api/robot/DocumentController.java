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
import pwd.initializr.search.api.robot.vo.DocumentIntoSearchInputVO;
import pwd.initializr.search.api.robot.vo.SearchInputVo;
import pwd.initializr.search.api.robot.vo.SearchOutputVO;
import pwd.initializr.search.business.robot.DocumentService;
import pwd.initializr.search.business.robot.bo.DocumentBO;
import pwd.initializr.search.business.robot.bo.SearchInputBO;
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
    tags = "信息写入与搜索",
    value = "documentApi",
    description = "信息写入与搜索API"
)
@RestController(value = "documentApi")
@RequestMapping(value = "/api/robot/search")
public class DocumentController extends RobotController implements DocumentApi {

    @Autowired
    private DocumentService documentService;

    @ApiOperation(value = "向ES中搜索")
    @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void postOrPut(@RequestBody DocumentIntoSearchInputVO input) {
        DocumentBO documentBO = new DocumentBO();
        BeanUtils.copyProperties(input.getEsBody().get(0), documentBO);
        documentService.create(input.getEsIndex(), documentBO);
        outputData(200);
    }

    @ApiOperation(value = "在ES中搜索")
    @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void search(SearchInputVo input) {
        SearchInputBO searchInputBO = new SearchInputBO();
        BeanUtils.copyProperties(input, searchInputBO);
        outputData(search0(documentService.search(searchInputBO)));
    }


    private ObjectList<SearchOutputVO> search0(ObjectList<? extends RPCSearchOutput> search) {
        ObjectList<SearchOutputVO> result = new ObjectList<>();
        if (search != null) {
            result.setSize(search.getSize());
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
        }
        return result;
    }
}
