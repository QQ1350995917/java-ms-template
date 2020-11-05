package pwd.initializr.monitor.api.admin.vo;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.monitor.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-11-05 16:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HostCpuStatStatisticsOutput {

    private String cpuCoreName;
    private List<HostCpuStatOutput> cpuStats;
}
