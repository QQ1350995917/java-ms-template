package pwd.initializr.gateway.api;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.gateway.api.vo.SessionWhiteVO;
import pwd.initializr.gateway.api.vo.SessionWhiteOutput;
import pwd.initializr.gateway.business.filter.SessionBO;
import pwd.initializr.gateway.business.filter.SessionWhiteListFilterServiceImpl;
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
@RestController()
@RequestMapping("/filter")
public class FilterController {

  @Resource
  private SessionWhiteListFilterServiceImpl sessionFilterService;

  @PostMapping(value = "/session/white", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<Integer> createFilterSessionWhite(@RequestBody SessionWhiteVO input) {
    SessionBO sessionBO = convertVoToBo(input);
    if (sessionFilterService.contains(sessionBO)) {
      return Mono.just(-1);
    } else {
      return sessionFilterService.create(sessionBO);
    }
  }

  @DeleteMapping(value = "/session/white/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<Integer> deleteFilterSessionWhite(@PathVariable("id") Long id) {
    return sessionFilterService.delete(id);
  }

  @GetMapping(value = "/session/white", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<SessionWhiteOutput> queryFilterSessionWhite() {
    return sessionFilterService.list().map(this::convertBoToVo)
        .collectList().map(list -> new SessionWhiteOutput(sessionFilterService.getRemoteVersion(),list));
  }

  @PutMapping(value = "/session/white/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<Integer> updateFilterSessionWhite(@PathVariable("id") Long id,
      @RequestBody SessionWhiteVO input) {
    input.setId(id);
    return sessionFilterService.update(convertVoToBo(input));
  }

  private SessionWhiteVO convertBoToVo(SessionBO sessionBO){
    SessionWhiteVO sessionWhiteVO = new SessionWhiteVO();
    BeanUtils.copyProperties(sessionBO, sessionWhiteVO);
    return sessionWhiteVO;
  }

  private SessionBO convertVoToBo(SessionWhiteVO sessionWhiteVO){
    SessionBO sessionBO = new SessionBO();
    BeanUtils.copyProperties(sessionWhiteVO,sessionBO);
    return sessionBO;
  }


}
