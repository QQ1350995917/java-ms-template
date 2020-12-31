package pwd.initializr.gateway.api;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.gateway.api.vo.SessionWhiteOutput;
import pwd.initializr.gateway.business.filter.SessionBO;
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
@RestController()
@RequestMapping("/filter")
public class FilterController {

  @Resource
  private SessionFilterServiceImpl sessionFilterService;

  @PostMapping(value = "/session/white", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<Long> createFilterSessionWhite(@RequestBody SessionWhiteOutput input) {
    return sessionFilterService.create(convertVoToBo(input));
  }

  @DeleteMapping(value = "/session/white/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<Integer> deleteFilterSessionWhite(@PathVariable("id") Long id) {
    return sessionFilterService.delete(id);
  }

  @GetMapping(value = "/session/white", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<List<SessionBO>> queryFilterSessionWhite() {
    return sessionFilterService.list().collectList();
  }

  @PutMapping(value = "/session/white/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Mono<Integer> updateFilterSessionWhite(@PathVariable("id") Long id,
      @RequestBody SessionWhiteOutput input) {
    input.setId(id);
    return sessionFilterService.update(convertVoToBo(input));
  }

  private SessionWhiteOutput convertBoToVo(SessionBO sessionBO){
    SessionWhiteOutput sessionWhiteOutput = new SessionWhiteOutput();
    BeanUtils.copyProperties(sessionBO, sessionWhiteOutput);
    return sessionWhiteOutput;
  }

  private SessionBO convertVoToBo(SessionWhiteOutput sessionVO){
    SessionBO sessionBO = new SessionBO();
    BeanUtils.copyProperties(sessionVO,sessionBO);
    return sessionBO;
  }


}
