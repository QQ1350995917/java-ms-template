package pwd.initializr.monitor.api.admin.vo;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
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
 * date 2020-10-19 18:12
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel(value = "CpuVO", description = "CpuVO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CpuVO implements Serializable {
    long user = 0L;
    long sys = 0L;
    long nice = 0L;
    long idle = 0L;
    long wait = 0L;
    long irq = 0L;
    long softIrq = 0L;
    long stolen = 0L;
    long total = 0L;
}
