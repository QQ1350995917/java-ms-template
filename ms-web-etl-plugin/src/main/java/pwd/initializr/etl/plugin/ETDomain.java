package pwd.initializr.etl.plugin;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import pwd.initializr.etl.ETLDefaultHandler;

/**
 * pwd.initializr.etl.plugin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-14 14:37
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETDomain  extends ETLDefaultHandler {

  public static String[] rootDomains = { ".com.cn", ".net.cn", ".org.cn", ".gov.cn", ".com", ".cn", ".io", ".xin", ".net", ".top", ".在线", ".xyz", ".wang",
      ".shop", ".site", ".club", ".cc", ".fun", ".online", ".biz", ".red", ".link", ".ltd", ".mobi", ".info", ".org", ".name", ".vip", ".pro", ".work",
      ".tv", ".co", ".kim", ".group", ".tech", ".store", ".ren", ".ink", ".pub", ".live", ".wiki", ".design", ".中文网", ".我爱你", ".中国", ".网址", ".网店", ".公司",
      ".网络", ".集团", ".beer", ".art", ".餐厅", ".luxe", ".商标", ".arpa" };

  public static List rootDomainList = Arrays.asList(rootDomains);

  @Override
  public void handle(Object object) {
    List<Map<String, String>> keyValues = (List<Map<String, String>>)object;
    for (Map<String, String> keyValue : keyValues) {
      if (keyValue.containsKey("errorFlag")) {
        continue;
      } else {
        String url = keyValue.get("url");
        try {
          String[] domainInUrl = getDomainInUrl(url);
          keyValue.put("topDomain", domainInUrl[0]);
          keyValue.put("domain", domainInUrl[1]);
        } catch (MalformedURLException e) {
          keyValue.put("errorFlag", "true");
        }
      }
    }
    this.getNext().handle(keyValues);
  }


  public static String[] getDomainInUrl(String url) throws MalformedURLException {
    if (url == null) {
      return null;
    }

    if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://")) {
      url = String.join("", "http://", url);
    }

    String[] domains = new String[2];
    java.net.URL urlObject = null;
    try {
      urlObject = new java.net.URL(url);
      String domain = urlObject.getHost();
      if (domain == null) {
        throw new MalformedURLException("domain is null");
      }
      domains[1] = domain;
      if (domain.lastIndexOf(".") > -1){
        String domainName = domain.substring(domain.lastIndexOf("."));
        boolean contains = rootDomainList.contains(domainName);
        if (contains) {
          String[] split = domain.replace(domainName, "").split("\\.");
          String topDomain = split[split.length - 1];
          domains[0] = String.join("", topDomain, domainName);
        } else {
          //throw new MalformedURLException(String.join("", "unknown domain space", domain));
        }
      }
    } catch (MalformedURLException e) {

    }

    return domains;
  }
}
