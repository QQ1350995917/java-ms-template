package pwd.initializr.common.web.api.test;


import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.ApiController;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;

/**
 * pwd.initializr.common.web.api.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:28
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(value = "检查模板操作", description = "检查模板操作")
@RestController
@RequestMapping("/api")
public class TestController extends ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public Output<String> api(@RequestParam(value = "params", required = false) String input) {
        super.outputExceptionToLog(this.getClass(), new Exception("test log"), input);
        Meta meta = new Meta();
        if (input == null) {
            meta.setMessage("you can request with \"params=hello-word\"");
        }
        return new Output<>(meta, input);
    }

    @RequestMapping(value = "/ping/get", method = RequestMethod.GET)
    public Output<TestInput> get(TestInput input) {
        super.outputExceptionToLog(this.getClass(), new Exception("test log"), input);
        Meta meta = new Meta();
        if (input == null) {
            meta.setMessage(
                "you can request with \"strings=1&strings=2&anInt=4&aLong=1&string=g_222\"");
        }
        return new Output<>(meta, input);
    }

    @RequestMapping(value = "/ping/post", method = RequestMethod.POST)
    public Output<TestInput> post(@RequestParam TestInput input) {
        super.outputExceptionToLog(this.getClass(), new Exception("test log"), input);
        Meta meta = new Meta();
        if (input == null) {
            meta.setMessage(
                "you can request with \"strings=1&strings=2&anInt=4&aLong=1&string=g_222\"");
        }
        return new Output<>(meta, input);
    }
}
