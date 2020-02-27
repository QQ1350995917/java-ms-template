package pwd.initializr.etl.core.input.over;

import com.alibaba.fastjson.JSONObject;

/**
 * pwd.initializr.etl.core.input.over@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-27 10:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface Over {

  String getCompleteSuffix();

  String getSuffix();

  void over(String filePathFaker);

  void setConfig(JSONObject config);

}
