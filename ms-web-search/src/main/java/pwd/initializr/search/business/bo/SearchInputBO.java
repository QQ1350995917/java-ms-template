package pwd.initializr.search.business.bo;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.common.utils.StringUtils;
import pwd.initializr.search.rpc.RPCSearchFieldExactlyVO;
import pwd.initializr.search.rpc.RPCSearchFieldSortVO;
import pwd.initializr.search.rpc.RPCSearchFieldVO;
import pwd.initializr.search.rpc.RPCSearchInput;

/**
 * pwd.initializr.search.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-15 11:59
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class SearchInputBO extends RPCSearchInput {

    private RPCSearchFieldExactlyVO exactly;
    private List<RPCSearchFieldVO> searchField;
    private List<RPCSearchFieldSortVO> sort;

    public RPCSearchFieldExactlyVO getExactly() {
        if (exactly == null) {
            parserQuery();
        }
        return exactly;
    }

    public List<RPCSearchFieldVO> getSearchField() {
        if (searchField == null) {
            parserQuery();
        }
        return searchField;
    }

    public List<RPCSearchFieldSortVO> getSort() {
        if (sort == null) {
            parserQuery();
        }
        return sort;
    }

    private void parserQuery() {
        String query = getQuery();
        if (StringUtils.isNotBlank(query)) {
            SearchInputBO searchInputBO = JSON.parseObject(query, SearchInputBO.class);
            setExactly(searchInputBO.getExactly());
            setSearchField(searchInputBO.getSearchField());
            setSort(searchInputBO.getSort());
        }
    }
}
