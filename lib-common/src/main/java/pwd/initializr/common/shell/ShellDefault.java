package pwd.initializr.common.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * pwd.initializr.common.shell@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-25 22:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class ShellDefault implements Shell {

    static ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void exec() {
        exec(this.getCommandArray());
    }

    @Override
    public void exec(String[] commands) {
        try {
            Runtime.getRuntime().exec(commands);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShellResult execForResult() {
        return execForResult(this.getCommandForResultArray());
    }

    @Override
    public ShellResult execForResult(String[] commands) {
        try {
            List<String> lines = new LinkedList<>();
            List<String> errors = new LinkedList<>();
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
                        errors.add(line);
                    }
                } catch (Exception e) {

                }
            });
            Integer exitValue = -1;
            String exitMessage = null;
            try {
                exitValue = executorService.submit(() -> {
                    process.waitFor();
                    return process.exitValue();
                }).get(getTimeout(), getTimeoutUnit());
            } catch (Exception e) {
                exitMessage = e.getMessage();
            }
            if (exitValue != 0) {
                process.destroyForcibly();
            } else {
                process.destroy();
            }
            return new Shell.ShellResult(exitValue, exitMessage, lines, errors);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String[] getCommandArray();

    protected abstract String[] getCommandForResultArray();

    protected int getTimeout() {
        return 1;
    }

    protected TimeUnit getTimeoutUnit() {
        return TimeUnit.SECONDS;
    }

}
