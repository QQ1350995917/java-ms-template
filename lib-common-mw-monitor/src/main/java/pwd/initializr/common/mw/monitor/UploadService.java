package pwd.initializr.common.mw.monitor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * pwd.initializr.common.mw.monitor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-21 10:54
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@FeignClient(value = "MARKER", configuration = UploadConfig.class, fallback = UploadServiceImpl.class)
public interface UploadService {

    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    void upload();
}
