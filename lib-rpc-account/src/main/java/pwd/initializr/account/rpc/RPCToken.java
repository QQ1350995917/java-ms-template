package pwd.initializr.account.rpc;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.apache.commons.lang.StringUtils;

/**
 * pwd.initializr.account.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-12 20:25
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class RPCToken {

  public static void main(String[] args) {
    String secret = "xxx";

    RPCSession rpcSession0 = new RPCSession();
    rpcSession0.setAid(1L);
    rpcSession0.setUid(10L);
    rpcSession0.setTimestamp(System.currentTimeMillis());
    String token0 = generateToken(rpcSession0, secret);
    System.out.println(token0);

    RPCSession rpcSession1 = new RPCSession();
    rpcSession1.setAid(2L);
    rpcSession1.setUid(20L);
    rpcSession1.setTimestamp(System.currentTimeMillis());
    String token1 = generateToken(rpcSession1, secret);
    System.out.println(token1);

    String token2 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMEAxQDE2MTI2MTkzNjgzOTAifQ.nyorFwcT7aLv5OrWYersvos3YHHaPnZv0Ka1BrG4b4M";
    String token3 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMEAyQDE2MTI2MTkzNjg5NzIifQ.Du-PJvRf5DLgFpVudcEHVODZxGs1GcuouKprMqGFNOs";

    System.out.println(verifyToken(rpcSession1, token2, secret));
    System.out.println(verifyToken(rpcSession0, token3, secret));


  }

  public static String generateToken(RPCSession user, String secret) {
    String realContent = getRealContent(user);
    String realSecret = getRealSecret(user, secret);
    String token = JWT.create().withAudience(realContent)
        .sign(Algorithm.HMAC256(realSecret));
    return token;
  }

  public static Boolean verifyToken(RPCSession user, String token, String secret) {
    try {
      String realSecret = getRealSecret(user, secret);
      JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(realSecret)).build();
      jwtVerifier.verify(token);
      return true;
    } catch (SignatureVerificationException e) {
      return false;
    }
  }

  private static String getRealContent(RPCSession user) {
    return StringUtils.join(new String[]{
            user.getUid().toString(),
            user.getAid().toString(),
            user.getTimestamp().toString()}
        , "@");
  }

  private static String getRealSecret(RPCSession user, String secret) {
    return StringUtils.join(new String[]{
            user.getUid().toString(),
            user.getAid().toString(),
            secret
        }
        , "-");
  }
}
