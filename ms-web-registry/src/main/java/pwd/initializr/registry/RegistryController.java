package pwd.initializr.registry;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.registry@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-09 18:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController
public class RegistryController {

    @Value("${server.port}")
    private Integer serverPort;
    @Value("${spring.application.name}")
    private String springApplicationName;

    @GetMapping(value = "")
    public String index0() {
        Map<String, String> result = new HashMap<>();
        result.put("summary", "this is registry 0 index");
        result.put("springApplicationName", springApplicationName);
        result.put("serverPort", serverPort + "");
        return JSON.toJSONString(result);
    }

    @GetMapping(value = "/")
    public String index1() {
        Map<String, String> result = new HashMap<>();
        result.put("summary", "this is registry 1 index");
        result.put("springApplicationName", springApplicationName);
        result.put("serverPort", serverPort + "");
        return JSON.toJSONString(result);
    }
}
