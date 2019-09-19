package pwd.initializr.logger.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pwd.initializr.common.web.business.bo.OperationLogger;

/**
 * pwd.initializr.common.web.dao.logger@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 10:54
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
@Document(collection = "operation_logger")
public class OperationLoggerEntity extends OperationLogger {

  @Id
  @Field("id")
  private String id;
  @Field("uid")
  private String userId;
  @Field("sid")
  private String sessionId;
  @Field("cv")
  private String clientVersion;
  @Field("sv")
  private String serviceVersion;
  @Field("app_key")
  private String appKey;
  @Field("app_sec")
  private String appSecret;
  @Field("app_nam")
  private String appName;
  @Field("sn")
  private String serviceName;
  @Field("cip")
  private String clientIp;
  @Field("sip")
  private String serverIp;
  @Field("req_url")
  private String requestUrl;
  @Field("req_par")
  private String requestParams;
  @Field("req_sch")
  private String requestScheme;
  @Field("req_pro")
  private String requestProtocol;
  @Field("req_des")
  private String requestDescription;
  @Field("exe_dur")
  private Integer execDuration;
  @Field("res_cod")
  private Integer responseCode;
  @Field("req_dat")
  private String responseData;
  @Field("res_des")
  private String responseDescription;
  @Field("ct")
  private Long createTime;
}
