package pwd.initializr.common.http;

/**
 * pwd.initializr.common.http@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-23 18:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class HttpX {


    public abstract String postJson(String uri,String body);

    public abstract String getJson(String uri,String body);

}
