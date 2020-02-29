package pwd.initializr.etl.core.input.scanner;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import pwd.initializr.etl.core.input.processor.Processor;
import pwd.initializr.etl.core.input.processor.ProcessorFactory;

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

  private final Lock lock = new ReentrantLock();
  private final Condition inputScannerCondition = lock.newCondition();
  private final Condition handleDriverCondition = lock.newCondition();

  private Integer capacity = 5;
  private int MAX_CAPACITY = capacity;
  private String completeSuffix = "ok";
  private LinkedHashSet<String> inputScannerBlockingQueue;
  private JSONObject processorConfig;
  private String sourceDir;
  private String suffix;
  private Integer threadNum = 1;

  private BlockingQueue<Map> blockingQueue;


  public FileScanner() {

  }

  public FileScanner(JSONObject config) {
    this.setConfig(config);
  }

  public BlockingQueue<Map> getBlockingQueue() {
    return this.blockingQueue;
  }

  public FileScanner setBlockingQueue(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
    return this;
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
    this.threadNum = this.processorConfig.getInteger("threadNum");
    return this;
  }

  public String getCompleteSuffix() {
    return completeSuffix;
  }

  public String getSourceDir() {
    return sourceDir;
  }


  @Override
  public void run() {
    ExecutorService executorService = Executors.newFixedThreadPool(threadNum + 1);
    executorService.execute(new InputScanner());
    for (int i = 0; i < threadNum; i++) {
      executorService.execute(new InputScannerProcessor());
    }
  }

  private Processor getFileProcessor() {
    String aClass = processorConfig.getString("class");
    Processor processor = ProcessorFactory.getInstance(aClass);
    processor.setConfig(processorConfig);
    processor.setBlockingQueue(this.getBlockingQueue());
    return processor;
  }

  class InputScanner implements Runnable {

    @Override
    public void run() {
      while (true) {
        try {
          lock.lock();
          while (inputScannerBlockingQueue.size() >= MAX_CAPACITY) {
            inputScannerCondition.await();
          }
          File[] files = new File(getSourceDir()).listFiles((File dir, String name) -> {
            if (name.endsWith(getCompleteSuffix())) {
              return true;
            }
            return false;
          });

          if (files != null && files.length > 0) {
            for (File file : files) {
              String absolutePath = file.getAbsolutePath();
              String filePathFaker = absolutePath.substring(0,absolutePath.lastIndexOf("."));
              inputScannerBlockingQueue.add(filePathFaker);
              if (inputScannerBlockingQueue.size() >= MAX_CAPACITY) {
                break;
              }
            }
          }

          if (inputScannerBlockingQueue.size() > 0) {
            handleDriverCondition.signalAll();
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
      }
    }
  }

  class InputScannerProcessor implements Runnable {

    @Override
    public void run() {
      while (true) {
        try {
          lock.lock();
          while (inputScannerBlockingQueue.size() <= 0) {
            handleDriverCondition.await();
          }

          String completeFilePath = inputScannerBlockingQueue.iterator().next();
          Processor fileProcessor = getFileProcessor();
          if (fileProcessor != null) {
            fileProcessor.setBlockingQueue(getBlockingQueue());
            fileProcessor.process(completeFilePath);
          }
          inputScannerBlockingQueue.remove(completeFilePath);

          if (inputScannerBlockingQueue.size() < MAX_CAPACITY) {
            inputScannerCondition.signalAll();
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
      }
    }
  }
}
