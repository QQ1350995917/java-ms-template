package pwd.initializr.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-05 11:37
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CryptographerMd5 {

    public static String md5Value(String plainText) {
        try {
            byte[] secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
            String md5code = new BigInteger(1, secretBytes).toString(16);
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
            return md5code;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String text1 = "www.dingpengwei@foxmail.com";
        String text2 = "www.xxx.com";
        System.out.println(md5Value(text1));
        System.out.println(md5Value(text2));
    }


}
