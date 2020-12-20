package pwd.initializr.gateway.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-12 16:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Controller()
@RequestMapping("/router")
public class RouterController {

  @GetMapping("")
  public Mono<String> indexPage(final Model model) {
    model.addAttribute("key1", "hello");
    model.addAttribute("key2", "word");
    return Mono.create(mono -> mono.success("index"));
  }

}
