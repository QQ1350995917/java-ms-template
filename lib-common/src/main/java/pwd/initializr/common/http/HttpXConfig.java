package pwd.initializr.common.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.http@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 22:59
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HttpXConfig {

    private int connectionRequestTimeout = 1000;
    private int connectTimeout = 3000;
    private int socketTimeout = 2000;
    private boolean retryEnable = false;
    private int retryCount = 0;
    private int maxTotal = 2;
    private int defaultMaxPerRoute = 2;
}
