package pwd.initializr.email.business;

import feign.Response;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-08 19:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class StorageServiceFallbackImpl implements StorageService {

    @Override
    public Response download(@Valid @NotNull(message = "参数不能为空") String url) {
        return null;
    }
}
