package pwd.initializr.logger.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.logger.api.user.vo.RecordInput;

/**
 * pwd.initializr.logger.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-15 10:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "日志提交",
    value = "loggerCommitApi",
    description = "日志提交API"
)
@RestController(value = "loggerCommitApi")
@RequestMapping(value = "/api/logger")
public class RecordController extends UserController implements RecordApi {

  @ApiOperation(value = "日志提交")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  @Override
  public void record(@RequestBody RecordInput input) {
    super.outputData();
  }
}
