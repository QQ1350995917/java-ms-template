package pwd.initializr.account.test;

import com.alibaba.fastjson.JSON;
import pwd.initializr.account.business.session.bo.SessionBO;

/**
 * pwd.initializr.account.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-25 15:30
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Test {

  public static void main(String[] args) {
    String josn = "{\"aid\":-1,\"times\":0,\"timestamp\":1612692048438,\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NTU4NTI1ODIwNzAwMDU3NjBALTFAMTYxMjY5MjA0ODQzOCJ9.tOStxAIeDaM_YiY1-YwwrS6fHmSAkm16rLErlOqWcUs\",\"type\":0,\"uid\":555852582070005760}";
    SessionBO sessionBO = JSON.parseObject(josn, SessionBO.class);
    System.out.println();
  }
}
