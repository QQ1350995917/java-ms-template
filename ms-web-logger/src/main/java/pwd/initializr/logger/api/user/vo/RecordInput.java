package pwd.initializr.logger.api.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.logger.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 15:01
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
@ApiModel(value = "loggerCommitInput", description = "日志提交输入参数")
public class RecordInput {

  @ApiModelProperty(name = "userId", value = "用户ID", required = true, example = "-1")
  @Null(message = "0")
  private String userId;
  @ApiModelProperty(name = "sessionId", value = "sessionId", required = true, example = "unknown")
  @Null(message = "0")
  private String sessionId;
  @ApiModelProperty(name = "clientVersion", value = "客户端版本", required = true, example = "1.2")
  @NotNull(message = "0")
  private String clientVersion;
  @ApiModelProperty(name = "serviceVersion", value = "服务版本", required = true, example = "1.2")
  @NotNull(message = "0")
  private String serviceVersion;
  @ApiModelProperty(name = "appKey", value = "应用KEY", required = true, example = "appKey_example")
  @NotNull(message = "0")
  private String appKey;
  @ApiModelProperty(name = "appSecret", value = "应用秘钥", required = true, example = "appSecret_example")
  @NotNull(message = "0")
  private String appSecret;
  @ApiModelProperty(name = "appName", value = "应用名称", required = true, example = "appName_example")
  @NotNull(message = "0")
  private String appName;
  @ApiModelProperty(name = "serviceName", value = "服务名称", required = true, example = "serviceName_example")
  @NotNull(message = "0")
  private String serviceName;
  @ApiModelProperty(name = "clientIp", value = "客户端IP", required = true, example = "127.0.0.1")
  @NotNull(message = "0")
  private String clientIp;
  @ApiModelProperty(name = "serverIp", value = "服务器IP", required = true, example = "127.0.0.1")
  @NotNull(message = "0")
  private String serverIp;
  @ApiModelProperty(name = "requestUrl", value = "请求地址", required = true, example = "http://127.0.0.1/account/api/login")
  @NotNull(message = "0")
  private String requestUrl;
  @ApiModelProperty(name = "requestParams", value = "请求参数", required = true, example = "{\"jsonKey\":\"jsonValue\"}")
  @NotNull(message = "0")
  private String requestParams;
  @ApiModelProperty(name = "requestScheme", value = "请求规范", required = true, example = "http")
  @NotNull(message = "0")
  private String requestScheme;
  @ApiModelProperty(name = "requestProtocol", value = "请求协议", required = true, example = "HTTP/1.11")
  @NotNull(message = "0")
  private String requestProtocol;
  @ApiModelProperty(name = "requestDescription", value = "请求描述", required = true, example = "登录")
  @NotNull(message = "0")
  private String requestDescription;
  @ApiModelProperty(name = "execDuration", value = "响应时间", required = true, example = "203")
  @NotNull(message = "0")
  private Integer execDuration;
  @ApiModelProperty(name = "responseCode", value = "响应码", required = true, example = "200")
  @NotNull(message = "0")
  private Integer responseCode;
  @ApiModelProperty(name = "responseData", value = "响应数据", required = true, example = "{\"jsonKey\":\"jsonValue\"}")
  @NotNull(message = "0")
  private String responseData;
  @ApiModelProperty(name = "responseDescription", value = "响应描述", required = true, example = "登录成功")
  @NotNull(message = "0")
  private String responseDescription;
  @ApiModelProperty(name = "createTime", value = "日志生产时间", required = true, example = "1568977337345")
  @NotNull(message = "0")
  private Long createTime;
}
