package pwd.initializr.etl.core.input.over;

import com.alibaba.fastjson.JSONObject;
import java.io.File;

/**
 * pwd.initializr.etl.core.input.over@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-27 20:13
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class DefaultOver implements Over {

  protected String completeSuffix;
  protected String suffix;

  public DefaultOver() {
  }

  public DefaultOver(JSONObject config) {
    this.setConfig(config);
  }

  @Override
  public void setConfig(JSONObject config) {
    this.suffix = config.getString("suffix");
    this.completeSuffix = config.getString("completeSuffix");
  }

  @Override
  public String getSuffix() {
    return suffix;
  }

  @Override
  public String getCompleteSuffix() {
    return completeSuffix;
  }

  @Override
  public void over(String filePathFaker) {
    String ok = filePathFaker + "." + getCompleteSuffix();
    String data = filePathFaker + "." + getSuffix();
    String oking = ok + ".ing";
    String dataing = data + ".ing";
    String fileName = new File(ok).getName();
    onOver(dataing, oking, fileName);
  }

  protected abstract void onOver(String dataingFilePath, String okingFilePath, String fileName);
}
