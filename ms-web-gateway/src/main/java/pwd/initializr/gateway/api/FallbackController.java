package pwd.initializr.gateway.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * pwd.initializr.gateway.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-12 16:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController
public class FallbackController {
  @GetMapping("/fallback")
  @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
  public Flux<Integer> fallback() {

    return Flux.just(HttpStatus.SERVICE_UNAVAILABLE.value());
  }
}
