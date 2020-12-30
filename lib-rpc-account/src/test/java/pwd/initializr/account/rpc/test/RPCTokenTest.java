package pwd.initializr.account.rpc.test;

import java.util.UUID;
import pwd.initializr.account.rpc.RPCSession;
import pwd.initializr.account.rpc.RPCToken;

/**
 * pwd.initializr.account.rpc.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-30 18:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class RPCTokenTest {

    public static void main(String[] args) {
        Long times = 1000 * 1000L;
//        Long times = 100L;
        String secret = "secret";
//        generateTokenTest(times,secret);
        String token= "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OTkifQ.SqhDivO2EOg24nqVJrY6pcl-anYQ53x_pGiMljCTbtM";
        verifyToken(times,token,secret);


    }

    private static void generateTokenTest(Long times,String secret) {
        long start = System.currentTimeMillis();
        while (times > 0) {
            RPCSession rpcSession = new RPCSession();
            rpcSession.setUid(System.currentTimeMillis());
            rpcSession.setUName("UName");
            rpcSession.setAccountId(System.currentTimeMillis());
            rpcSession.setAccountName("AName");
            rpcSession.setTimestamp(System.currentTimeMillis());
            String s = RPCToken.generateToken(rpcSession, secret);
            System.out.println(s);
            times --;
        }
        long end = System.currentTimeMillis();

        System.out.println((end - start) / 1000);
    }

    private static void verifyToken(Long times,String token,String secret) {
        long start = System.currentTimeMillis();
        while (times > 0) {
            RPCSession rpcSession = new RPCSession();
            rpcSession.setUid(999L);
            rpcSession.setUName("UName");
            rpcSession.setAccountId(System.currentTimeMillis());
            rpcSession.setAccountName("AName");
            rpcSession.setTimestamp(System.currentTimeMillis());
            RPCToken.verifyToken(rpcSession,token, secret);
            times --;
        }
        long end = System.currentTimeMillis();

        System.out.println((end - start) / 1000);
    }

}
