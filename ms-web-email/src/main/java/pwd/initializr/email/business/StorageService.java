package pwd.initializr.email.business;

import feign.Response;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pwd.initializr.email.FeignConfig;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-08 19:08
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@FeignClient(value = "storage", configuration = FeignConfig.class, fallback = StorageServiceFallbackImpl.class)
public interface StorageService {

    @GetMapping(value = "/api/robot/file", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    Response download(@Valid @NotNull(message = "参数不能为空") @RequestParam("url") String url);
}

