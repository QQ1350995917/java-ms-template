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
public abstract class DefaultFileOver implements FileOver {

  private String completeSuffix;
  private String suffix;

  public DefaultFileOver() {
  }

  public DefaultFileOver(JSONObject config) {
    this.setConfig(config);
  }

  @Override
  public DefaultFileOver setConfig(JSONObject config) {
    this.suffix = config.getString("suffix");
    this.completeSuffix = config.getString("completeSuffix");
    return this;
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
    String fileNameFaker = fileName.substring(0,fileName.lastIndexOf("."));
    onOver(dataing, oking, fileNameFaker);
  }

  protected abstract void onOver(String dataingFilePath, String okingFilePath, String fileName);
}
