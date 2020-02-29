package pwd.initializr.etl.core.input.processor;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import pwd.initializr.etl.core.input.over.Over;
import pwd.initializr.etl.core.input.over.OverFactory;
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
public abstract class DefaultFileProcessor implements FileProcessor {

  private BlockingQueue<Map> blockingQueue;
  private String columnDelimiter;
  private String completeSuffix;
  private JSONObject overConfig;
  private String rowDelimiter;
  private String suffix;

  public DefaultFileProcessor() {

  }

  public DefaultFileProcessor(JSONObject config) {
    this.setConfig(config);
  }

  @Override
  public DefaultFileProcessor setConfig(JSONObject config) {
    this.rowDelimiter = config.getString("rowDelimiter");
    this.columnDelimiter = config.getString("columnDelimiter");
    this.overConfig = config.getJSONObject("over");
    this.suffix = config.getString("suffix");
    this.completeSuffix = config.getString("completeSuffix");
    this.overConfig.put("suffix", this.suffix);
    this.overConfig.put("completeSuffix", this.completeSuffix);
    return this;
  }

  @Override
  public void process(Object object) {
    String filePathFaker = object.toString();
    if (filePathFaker != null) {

      String dataIng = FileUtil.getIngFilePathByFaker(filePathFaker, this.suffix);
      this.onProcess(dataIng);
      this.onOver(filePathFaker);
      System.out.println();
    }
  }  public BlockingQueue<Map> getBlockingQueue() {
    return this.blockingQueue;
  }

  public abstract void onProcess(String filePath);  public void setBlockingQueue(BlockingQueue<Map> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  public void onOver(String filePathFaker) {
    this.getOver().over(filePathFaker);
  }

  @Override
  public String getRowDelimiter() {
    return rowDelimiter;
  }  @Override
  public Over getOver() {
    String strategy = overConfig.getString("strategy");
    return OverFactory.getInstance(strategy, overConfig);
  }

  @Override
  public String getColumnDelimiter() {
    return columnDelimiter;
  }








}
