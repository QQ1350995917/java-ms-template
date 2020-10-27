package pwd.initializr.common.mw.monitor.index;

import pwd.initializr.common.shell.ShellOnLinux;

/**
 * pwd.initializr.common.mw.monitor.index@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-27 20:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Centos {

    class Cpu extends ShellOnLinux {

        ShellResult shellResult;

        public Cpu() {
            this.shellResult = execForResult();
        }

        @Override
        protected String[] getCommandForResultArray() {
            return new String[]{"cat /proc/cpuinfo"};
        }

        void getCpu() {

        }
    }

    class CpuStat extends ShellOnLinux {

        ShellResult shellResult;

        public CpuStat() {
            this.shellResult = execForResult();
        }

        @Override
        protected String[] getCommandForResultArray() {
            return new String[]{"cat /proc/stat"};
        }

        void getCpu() {

        }
    }
}
