package pwd.initializr.typeface.business;

import com.alibaba.fastjson.JSON;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;

/**
 * pwd.initializr.typeface.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-04 22:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class StorageServiceFallbackImpl implements StorageService {

  @Override
  public String upload(String appName, String bucketName, String fileName, MultipartFile file) {
    return JSON.toJSONString(new Output(new Meta(504)));
  }
}
