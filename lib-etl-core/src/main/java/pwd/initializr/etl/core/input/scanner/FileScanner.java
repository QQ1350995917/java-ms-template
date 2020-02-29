package pwd.initializr.etl.core.input.scanner;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import pwd.initializr.etl.core.input.processor.Processor;
import pwd.initializr.etl.core.input.processor.ProcessorFactory;
import pwd.initializr.etl.core.util.FileUtil;

/**
 * pwd.initializr.etl.core.input.scanner@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-27 09:32
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class FileScanner implements Runnable {

  private BlockingQueue<Map> blockingQueue;
  private Integer capacity = 5;
  private int MAX_CAPACITY = capacity;
  private String completeSuffix = "ok";
  private ThreadPoolExecutor executorService;
  private LinkedHashSet<String> inputScannerBlockingQueue;
  private JSONObject processorConfig;
  private String sourceDir;
  private String suffix;


  public FileScanner() {

  }

  public FileScanner(JSONObject config) {
    this.setConfig(config);
  }

  public FileScanner setConfig(JSONObject config) {
    this.sourceDir = config.getString("sourceDir");
    this.suffix = config.getString("suffix");
    this.completeSuffix = config.getString("completeSuffix");
    this.capacity = config.getInteger("capacity");
    this.MAX_CAPACITY = capacity;
    this.inputScannerBlockingQueue = new LinkedHashSet<>(MAX_CAPACITY);

    this.processorConfig = config.getJSONObject("processor");
    this.processorConfig.put("suffix", this.suffix);
    this.processorConfig.put("completeSuffix", this.completeSuffix);
    Integer threadNum = this.processorConfig.getInteger("threadNum");
    executorService = new ThreadPoolExecutor(threadNum, threadNum, 60L, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(1));
    return this;
  }

  @Override
  public void run() {
    while (true) {
      File[] files = new File(getSourceDir()).listFiles((File dir, String name) -> {
        if (name.endsWith(getCompleteSuffix())) {
          return true;
        }
        return false;
      });

      if (files != null && files.length > 0) {
        for (File file : files) {
          String absolutePath = file.getAbsolutePath();
          String filePathFaker = absolutePath.substring(0, absolutePath.lastIndexOf("."));
          if (inputScannerBlockingQueue.size() >= MAX_CAPACITY) {
            break;
          } else {
            inputScannerBlockingQueue.add(filePathFaker);
          }
        }
      }

      if (executorService.getActiveCount() < executorService.getMaximumPoolSize()) {
        if (inputScannerBlockingQueue.iterator().hasNext()) {
          String fakeFilePath = inputScannerBlockingQueue.iterator().next();
          String data = FileUtil.getFilePathByFaker(fakeFilePath, suffix);
          String dataIng = FileUtil.getIngFilePathByFaker(fakeFilePath, suffix);
          String ok = FileUtil.getFilePathByFaker(fakeFilePath, completeSuffix);
          String okIng = FileUtil.getIngFilePathByFaker(fakeFilePath, completeSuffix);
          new File(ok).renameTo(new File(okIng));
          new File(data).renameTo(new File(dataIng));
          executorService.execute(() -> {
            Processor fileProcessor = getFileProcessor();
            if (fileProcessor != null) {
              fileProcessor.setBlockingQueue(getBlockingQueue());
              fileProcessor.process(fakeFilePath);
            }
          });
          inputScannerBlockingQueue.remove(fakeFilePath);
        }
      } else {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public String getSourceDir() {
    return sourceDir;
  }

  public String getCompleteSuffix() {
    return completeSuffix;
  }

  private Processor getFileProcessor() {
    String aClass = processorConfig.getString("class");
    Processor processor = ProcessorFactory.getInstance(aClass);
    processor.setConfig(processorConfig);
    processor.setBlockingQueue(this.getBlockingQueue());
    return processor;
  }

  public BlockingQueue<Map> getBlockingQueue() {
    return this.blockingQueue;
  }

  public FileScanner setBlockingQueue(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
    return this;
  }
}
