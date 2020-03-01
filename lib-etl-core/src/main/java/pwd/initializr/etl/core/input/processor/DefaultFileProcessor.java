package pwd.initializr.etl.core.input.processor;

import com.alibaba.fastjson.JSONObject;
import pwd.initializr.etl.core.util.FileUtil;

/**
 * pwd.initializr.etl.core.input.processor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-27 10:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class DefaultFileProcessor extends DefaultProcessor {

  private String charset;
  private String completeSuffix;
  private String delimiter;
  private JSONObject overConfig;
  private String suffix;

  public String getCharset() {
    return charset;
  }

  public String getDelimiter() {
    return delimiter;
  }

  @Override
  public void process(Object object) {
    String filePathFaker = object.toString();
    if (filePathFaker != null) {
      String dataIng = FileUtil.getIngFilePathByFaker(filePathFaker, this.suffix);
      this.onProcess(dataIng);
      this.onOver(filePathFaker);
    }
  }

  public abstract void onProcess(String filePath);

  private void onOver(String filePathFaker) {
    this.getOver().over(filePathFaker);
  }

  @Override
  public Processor setConfig(JSONObject config) {
    this.charset = config.getString("charset");
    this.delimiter = config.getString("delimiter");
    this.overConfig = config.getJSONObject("over");
    this.suffix = config.getString("suffix");
    this.completeSuffix = config.getString("completeSuffix");
    this.overConfig.put("suffix", this.suffix);
    this.overConfig.put("completeSuffix", this.completeSuffix);
    return super.setConfig(config);
  }
}
