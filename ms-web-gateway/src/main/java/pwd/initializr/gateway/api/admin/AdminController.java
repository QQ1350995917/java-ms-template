package pwd.initializr.gateway.api.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@Controller
public class AdminController {
    @GetMapping("/index")
    public Mono<String> hello(final Model model) {
        model.addAttribute("key1", "hello");
        model.addAttribute("key2", "word");

        String path = "index";
        return Mono.create(monoSink -> monoSink.success(path));
    }

    private static final String CITY_LIST_PATH_NAME = "cityList";

//    @GetMapping("/page/list")
//    public String listPage(final Model model) {
//        final Flux<City> cityFluxList = cityHandler.findAllCity();
//        model.addAttribute("cityList", cityFluxList);
//        return CITY_LIST_PATH_NAME;
//    }
}
