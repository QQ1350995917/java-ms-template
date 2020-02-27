package pwd.initializr.etl.core.input.processor;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import pwd.initializr.etl.core.input.over.Over;

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
public abstract class DefaultFileProcessor implements FileProcessor {

  protected BlockingQueue blockingQueue;
  protected String delimiter;
  private String completeSuffix;
  private Over over;
  private String suffix;

  public DefaultFileProcessor() {

  }

  public DefaultFileProcessor(JSONObject config) {
    this.setConfig(config);
  }

  @Override
  public void setConfig(JSONObject config) {
    this.delimiter = config.getString("delimiter");
    JSONObject overConfig = config.getJSONObject("over");
    this.suffix = config.getString("suffix");
    this.completeSuffix = config.getString("completeSuffix");
    overConfig.put("suffix", this.suffix);
    overConfig.put("completeSuffix", this.completeSuffix);
    String strategy = overConfig.getString("strategy");
    try {
      this.over = (Over) Class.forName("pwd.initializr.etl.core.input.over." + strategy)
          .newInstance();
      this.over.setConfig(overConfig);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public BlockingQueue<Map> getBlockingQueue() {
    return this.blockingQueue;
  }

  public void setBlockingQueue(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  public void process(Object object) {
    String filePathFaker = object.toString();
    if (filePathFaker != null) {
      String ok = filePathFaker + "." + getCompleteSuffix();
      String data = filePathFaker + "." + getSuffix();
      String oking = ok + ".ing";
      String dataing = data + ".ing";
      new File(ok).renameTo(new File(oking));
      new File(data).renameTo(new File(dataing));
      this.onProcess(data);
      this.onOver(filePathFaker);
    }
  }

  public String getCompleteSuffix() {
    return completeSuffix;
  }

  public String getSuffix() {
    return this.suffix;
  }

  public abstract void onProcess(String filePath);

  public void onOver(String filePathFaker) {
    this.getOver().over(filePathFaker);
  }

  @Override
  public void setOver() {

  }

  public Over getOver() {
    return this.over;
  }


}
