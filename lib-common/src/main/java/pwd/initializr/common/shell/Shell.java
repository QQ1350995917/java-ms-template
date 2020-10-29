package pwd.initializr.common.shell;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.shell@ms-web-initializr
 *
 * <h1>shell客户端</h1>
 * <p>fixme:生命周期管理，线程池管理，优化工程能力</p>
 *
 * date 2020-10-25 22:07
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface Shell {

    void exec();

    void exec(String[] commands);

    ShellResult execForResult();

    ShellResult execForResult(String[] commands);

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    class ShellResult {

        private int exitValue;
        private String exitMessage;
        private List<String> lines;
        private List<String> errors;
    }
}
