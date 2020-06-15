package pwd.initializr.book.business.user.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.search.rpc.RPCSearchInput;

/**
 * pwd.initializr.book.business.user.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-23 17:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SearchInputBO extends RPCSearchInput {

    @Override
    public Integer getIndex() {
        return super.getIndex() < 0 ? 0 : super.getIndex();
    }

    @Override
    public Integer getSize() {
        return super.getSize() < 1 ? 12 : super.getSize();
    }
}
