package pwd.initializr.monitor.rpc;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-21 14:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RPCServiceInstance {

    @ApiModelProperty(value = "服务名称")
    private String app;
    @ApiModelProperty(value = "服务分组名称")
    private String appGroup;
    @ApiModelProperty(value = "实例ID")
    private String instanceId;
}

// {
//     "instanceId":"localhost:caiot-manage-hn:7093",
//     "app":"CAIOT-MANAGE-HN",
//     "appGroupName":null,
//     "ipAddr":"192.168.200.60",
//     "sid":"na",
//     "homePageUrl":"http://192.168.200.60:7093/",
//     "statusPageUrl":"http://192.168.200.60:7093/info",
//     "healthCheckUrl":"http://192.168.200.60:7093/health",
//     "secureHealthCheckUrl":null,
//     "vipAddress":"caiot-manage-hn",
//     "secureVipAddress":"caiot-manage-hn",
//     "countryId":1,
//     "dataCenterInfo":{
//     "@class":"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
//     "name":"MyOwn"
//     },
//     "hostName":"192.168.200.60",
//     "status":"UP",
//     "leaseInfo":{
//     "renewalIntervalInSecs":5,
//     "durationInSecs":15,
//     "registrationTimestamp":1600937030926,
//     "lastRenewalTimestamp":1603261262785,
//     "evictionTimestamp":0,
//     "serviceUpTimestamp":1600937030926
//     },
//     "isCoordinatingDiscoveryServer":false,
//     "metadata":{
//
//     },
//     "lastUpdatedTimestamp":1600937030926,
//     "lastDirtyTimestamp":1600937030868,
//     "actionType":"ADDED",
//     "asgName":null,
//     "overriddenStatus":"UNKNOWN"
//     }
