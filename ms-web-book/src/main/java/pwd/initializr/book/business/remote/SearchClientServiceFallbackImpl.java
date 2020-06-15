package pwd.initializr.book.business.remote;

import com.alibaba.fastjson.JSON;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import pwd.initializr.book.business.remote.bo.SearchResultBO;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.search.rpc.RPCSearchInitInput;

/**
 * pwd.initializr.book.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:30
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
public class SearchClientServiceFallbackImpl implements SearchClientService {

    @Override
    public String postOrPut(RPCSearchInitInput rpcSearchInitInput) {
        return JSON.toJSONString(new Output(new Meta(504, "error")));
    }


    @Override
    public Output<ObjectList<SearchResultBO>> search(@RequestParam("indices") List<String> indices,
        String keyword, Integer index,
        Integer pageSize) {
        return new Output<>();
    }
}
