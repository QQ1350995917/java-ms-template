package pwd.initializr.common.web.business.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.web.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 14:57
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OperationLogger {
  private String id;
  private String userId;
  private String sessionId;
  private String clientVersion;
  private String serviceVersion;
  private String appKey;
  private String appSecret;
  private String appName;
  private String serviceName;
  private String clientIp;
  private String serverIp;
  private String requestUrl;
  private String requestParams;
  private String requestScheme;
  private String requestProtocol;
  private String requestDescription;
  private Integer execDuration;
  private Integer responseCode;
  private String responseData;
  private String responseDescription;
  private Long createTime;
}
