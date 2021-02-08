package pwd.initializr.gateway.api.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-23 22:54
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Controller()
@RequestMapping("")
public class ViewController {

  @GetMapping("")
  public Mono<String> indexPage(final Model model) {
    model.addAttribute("key1", "hello");
    model.addAttribute("key2", "word");
    return Mono.create(mono -> mono.success("index"));
  }

  @GetMapping("/router")
  public Mono<String> routerPage(final Model model) {
    return Mono.create(mono -> mono.success("router"));
  }

  @GetMapping("/session")
  public Mono<String> sessionPage(final Model model) {
    return Mono.create(mono -> mono.success("session"));
  }
}
