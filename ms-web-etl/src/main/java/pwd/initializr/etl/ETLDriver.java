package pwd.initializr.etl;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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

  private static final int MAX_CAPACITY = 10;
  private static final Lock lock = new ReentrantLock();
  private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(MAX_CAPACITY);
  private ExecutorService executorService;
  private String input;
  private String output;


  public ETLDriver(Integer core, Integer max, String input, String output) throws Exception {
    this.input = input;
    this.output = output;

    executorService = new ThreadPoolExecutor(core + 2, max, 60L, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>());
    executorService.execute(new InputScanner());

  }


  private synchronized String poll() {
    String result = null;
    try {
      boolean getLock = lock.tryLock();
      if (getLock) {
        String poll = blockingQueue.poll();
        if (poll != null) {
          result = rename(poll);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return result;
  }

  private String rename(String file) {
    String result = file.concat(".ing");
    new File(file).renameTo(new File(result));
    return result;
  }

  class InputScanner implements Runnable {

    @Override
    public void run() {
      while (true) {
        try {
          boolean getLock = lock.tryLock();
          if (getLock) {

          }
        } catch (Exception e) {

        } finally {
          lock.unlock();
        }
        boolean offer = blockingQueue.offer("a");
        if (offer) {

        }
      }
    }
  }

  class HandleDriver implements Runnable {

    @Override
    public void run() {
      String poll = poll();

    }
  }
}
