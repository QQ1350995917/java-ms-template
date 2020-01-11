package pwd.initializr.etl;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * pwd.initializr.etl@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-10 16:56
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETLDriver {

  private static final int MAX_CAPACITY = 5;
  private static final Lock lock = new ReentrantLock();
  private static final Condition inputScannerCondition = lock.newCondition();
  private static final Condition handleDriverCondition = lock.newCondition();
  private static LinkedHashSet<String> inputScannerBlockingQueue = new LinkedHashSet<>(MAX_CAPACITY);
  private static ThreadPoolExecutor executorService;
  private static String input;
  private static String output;
  private static ETLHandler etlHandler;


  public static void main(String[] args) throws Exception {
    new ETLDriver(1,3,"/Users/pwd/Documents/etl/input","/Users/pwd/Documents/etl/output",null);
  }


  public ETLDriver(Integer core, Integer max, String input, String output,ETLHandler etlHandler) throws Exception {
    this.input = input;
    this.output = output;
    this.etlHandler = etlHandler;

    executorService = new ThreadPoolExecutor(core, max, 60L, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(MAX_CAPACITY));
    new Thread(new InputScanner()).start();
    new Thread(new HandleDriver()).start();
  }

  static class HandleWorker implements Runnable {
    private String oking;
    private String dataing;
    private String fileName;

    public HandleWorker(String oking ,String dataing,String fileName){
      this.oking = oking;
      this.dataing = dataing;
      this.fileName = fileName;
    }

    @Override
    public void run() {
      try {
        if (etlHandler != null) {
          etlHandler.handle(this.dataing);
        }
        Files.deleteIfExists(Paths.get(output + "/" + fileName));
        Files.move(Paths.get(this.dataing),Paths.get(output + "/" + fileName));
        Files.deleteIfExists(Paths.get(output + "/" + fileName + ".ok"));
        Files.move(Paths.get(this.oking),Paths.get(output + "/" + fileName + ".ok"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  static class HandleDriver implements Runnable {

    @Override
    public void run() {
      while (true){
        try {
          lock.lock();
          while (inputScannerBlockingQueue.size() == 0){
            handleDriverCondition.await();
          }

          if (executorService.getQueue().size() <= MAX_CAPACITY) {
            String ok = inputScannerBlockingQueue.iterator().next();
            if (ok != null) {
              String oking = ok.replace(".ok", ".oking");
              String data = ok.replace(".ok", "");
              String dataing = data.concat(".dataing");
              String fileName = new File(data).getName();
              new File(ok).renameTo(new File(oking));
              new File(data).renameTo(new File(dataing));
              inputScannerBlockingQueue.remove(ok);
              executorService.execute(new HandleWorker(oking,dataing,fileName));
            }
            if (inputScannerBlockingQueue.size() < MAX_CAPACITY) {
              inputScannerCondition.signalAll();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
      }
    }
  }

  static class InputScanner implements Runnable {

    @Override
    public void run() {
      while (true) {
        try {
          lock.lock();
          while (inputScannerBlockingQueue.size() == MAX_CAPACITY){
            inputScannerCondition.await();
          }

          File[] files = new File(input).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
              if (name.endsWith(".ok")) {
                  return true;
              }
              return false;
            }
          });

          if (files != null && files.length > 0) {
            for (File file : files) {
              boolean offer = inputScannerBlockingQueue.add(file.getAbsolutePath());
              if (offer) {
                continue;
              } else {
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
}
