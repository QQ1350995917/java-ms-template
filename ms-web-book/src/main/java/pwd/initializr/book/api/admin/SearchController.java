package pwd.initializr.book.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pwd.initializr.book.api.admin.vo.SearchInput;
import pwd.initializr.book.api.user.SearchApi;
import pwd.initializr.book.api.user.vo.SearchInputVO;
import pwd.initializr.book.business.remote.SearchClientService;
import pwd.initializr.book.business.remote.bo.SearchResultBO;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.book.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-25 15:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SearchController extends UserController implements SearchApi {

    @Autowired
    private SearchClientService searchClientService;

    @Override
    public void search(SearchInputVO input) {
        SearchInputVO tempSearchInput;
        if (input == null) {
            tempSearchInput = new SearchInputVO();
        } else {
            tempSearchInput = input;
        }

        Output<ObjectList<SearchResultBO>> objectListOutput = searchClientService
            .search(tempSearchInput.getKeyword(), tempSearchInput.getIndex(),
                tempSearchInput.getSize());

        if (objectListOutput.getMeta().getCode() == HttpStatus.OK.value()) {
            super.outputData(objectListOutput.getData());
        } else {
            super.outputData(objectListOutput.getData());
        }
    }

    @Override
    public void searchArticle(SearchInput input) {
        SearchInput tempSearchInput;
        if (input == null) {
            tempSearchInput = new SearchInput();
        } else {
            tempSearchInput = input;
        }

        Output<ObjectList<SearchResultBO>> objectListOutput = searchClientService
            .searchArticle(tempSearchInput.getKeyword(), tempSearchInput.getIndex(),
                tempSearchInput.getSize());

        if (objectListOutput.getMeta().getCode() == HttpStatus.OK.value()) {
            super.outputData(objectListOutput.getData());
        } else {
            super.outputData(objectListOutput.getData());
        }
    }

    @Override
    public void searchBook(SearchInput input) {
        SearchInput tempSearchInput;
        if (input == null) {
            tempSearchInput = new SearchInput();
        } else {
            tempSearchInput = input;
        }

        Output<ObjectList<SearchResultBO>> objectListOutput = searchClientService
            .searchBook(tempSearchInput.getKeyword(), tempSearchInput.getIndex(),
                tempSearchInput.getSize());

        if (objectListOutput.getMeta().getCode() == HttpStatus.OK.value()) {
            super.outputData(objectListOutput.getData());
        } else {
            super.outputData(objectListOutput.getData());
        }
    }
}
