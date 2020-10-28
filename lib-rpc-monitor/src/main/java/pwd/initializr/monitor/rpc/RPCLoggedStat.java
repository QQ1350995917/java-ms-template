package pwd.initializr.monitor.rpc;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-28 17:35
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RPCLoggedStat implements ILoggedStat {

    private String user;
    private String tty;
    private String from;
    private String login;
    private String idle;
    private String jcpu;
    private String pcpu;
    private String what;

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getTty() {
        return tty;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getIdle() {
        return idle;
    }

    @Override
    public String getJcpu() {
        return jcpu;
    }

    @Override
    public String getPcpu() {
        return pcpu;
    }

    @Override
    public String getWhat() {
        return what;
    }
}
