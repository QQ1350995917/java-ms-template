import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * PACKAGE_NAME@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-27 17:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Test {

    protected static ExecutorService executorService = Executors.newCachedThreadPool();

    public static ShellResult execForResult(String[] commands) {
        try {
            List<String> lines = new LinkedList<>();
            List<String> error = new LinkedList<>();
            Process process = Runtime.getRuntime().exec(commands);
            executorService.execute(() -> {
                try (InputStream inputStream = process.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        lines.add(line);
                    }
                } catch (Exception e) {

                }
            });
            executorService.execute(() -> {
                try (InputStream inputStream = process.getErrorStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        error.add(line);
                    }
                } catch (Exception e) {

                }
            });
            Integer exitValue = -1;
            try {
                exitValue = executorService.submit(() -> {
                    process.waitFor();
                    return process.exitValue();
                }).get(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                exitValue = 99;
            } catch (ExecutionException e) {
                exitValue = 89;
            } catch (TimeoutException e) {
                exitValue = 79;
            }
            System.out.println(exitValue);
            if (exitValue != 0) {
                process.destroyForcibly();
            } else {
                process.destroy();
            }
            return new Test().new ShellResult(exitValue, lines, error);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String[] command = {"/bin/sh", "-c", "ping baidu.com"};
        ShellResult shellResult = execForResult(command);
        System.out.println(shellResult);
        executorService.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("wait=" + (end - start));
    }

    class ShellResult {

        private int exitValue;
        private List<String> lines;
        private List<String> error;

        public ShellResult(int exitValue, List<String> lines, List<String> error) {
            this.exitValue = exitValue;
            this.lines = lines;
            this.error = error;
        }

        public int getExitValue() {
            return exitValue;
        }

        public void setExitValue(int exitValue) {
            this.exitValue = exitValue;
        }

        public List<String> getLines() {
            return lines;
        }

        public void setLines(List<String> lines) {
            this.lines = lines;
        }

        public List<String> getError() {
            return error;
        }

        public void setError(List<String> error) {
            this.error = error;
        }

        @Override
        public String toString() {
            System.out.println("ExitValue = " + this.getExitValue());
            System.out.println("=======lines======");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("=======errors======");
            for (String err : error) {
                System.out.println(err);
            }
            return "";
        }
    }


}
