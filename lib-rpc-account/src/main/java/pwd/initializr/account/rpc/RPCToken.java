package pwd.initializr.account.rpc;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
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

  public static String generateToken(RPCSession user, String secret) {
    String realSecret = getRealSecret(user, secret);
    String token = JWT.create().withAudience(String.valueOf(user.getUid()))
        .sign(Algorithm.HMAC256(realSecret));
    return token;
  }

  private static String getRealSecret(RPCSession user, String secret) {
    return StringUtils.join(new String[]{secret, "-", user.getUid().toString()});
  }

  public static void verifyToken(RPCSession user, String token, String secret) {
    String realSecret = getRealSecret(user, secret);
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(realSecret)).build();
    jwtVerifier.verify(token);
  }
}
