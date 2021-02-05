package pwd.initializr.common.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.UUID;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Hex;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-05 11:32
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CryptographerPbkdf {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    //盐的长度
    public static final int SALT_BYTE_SIZE = 8 / 2;
    //生成密文的长度
    public static final int HASH_BIT_SIZE = 128;
    //迭代次数
    public static final int PBKDF2_ITERATIONS = 1000;

    private static SecureRandom random;

    static {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean authenticate(String attemptedPassword, String encryptedPassword,
        String salt) throws Exception {
        // 用相同的盐值对用户输入的密码进行加密
        String encryptedAttemptedPassword = encrypt(attemptedPassword, salt);
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return encryptedAttemptedPassword.equals(encryptedPassword);
    }

    public static String encrypt(String password, String salt) throws Exception {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), Hex.decodeHex(salt),
            PBKDF2_ITERATIONS,
            HASH_BIT_SIZE);

        SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return Hex.encodeHexString(f.generateSecret(spec).getEncoded());
    }

    public static String generateSalt() {
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return Hex.encodeHexString(salt);
    }

    public static void main(String[] args) throws Exception {
        long start0 = System.currentTimeMillis();
        String text1 = "www.dingpengwei@foxmail.com";
        String text2 = "www.xxx.com";
        for (int i = 0; i < 100000; i++) {
            String salt = generateSalt();
        }
        long end0 = System.currentTimeMillis();
        System.out.println(end0 - start0);

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String salt = generateSalt();
            encrypt(System.currentTimeMillis() + UUID.randomUUID().toString(),salt);
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);


        String salt = generateSalt();
        System.out.println("salt:" + salt);
        String pbkdf1 = encrypt(text1, salt);
        long start2 = System.currentTimeMillis();
        System.out.println("cipherText:" + pbkdf1);
        String pbkdf2 = encrypt(text2, salt);
        System.out.println("cipherText:" + pbkdf2);
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);
    }

}
