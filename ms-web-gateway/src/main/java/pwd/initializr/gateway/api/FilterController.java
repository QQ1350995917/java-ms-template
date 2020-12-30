package pwd.initializr.gateway.api;

import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.gateway.api.vo.FilterSessionWhiteVO;
import pwd.initializr.gateway.business.filter.SessionFilterServiceImpl;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-30 23:35
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Controller()
@RequestMapping("/filter")
public class FilterController {

  @Resource
  private SessionFilterServiceImpl sessionFilterService;

  @PostMapping(value = "/session/white", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<FilterSessionWhiteVO> createFilterSessionWhite() {
    return Mono.empty();
  }

  @DeleteMapping(value = "/session/white/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<FilterSessionWhiteVO> deleteFilterSessionWhite(@PathVariable("id") Long id) {
    return Mono.empty();
  }

  @GetMapping(value = "/session/white", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<FilterSessionWhiteVO> queryFilterSessionWhite() {
    return Mono.empty();
  }

  @PutMapping(value = "/session/white/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<FilterSessionWhiteVO> updateFilterSessionWhite(@PathVariable("id") Long id,
      FilterSessionWhiteVO input) {
    return Mono.empty();
  }


}
