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

  //生成密文的长度
  private static final int HASH_BIT_SIZE = 128 * 4;
  private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
  //迭代次数
  private static final int PBKDF2_ITERATIONS = 1024;
  //盐的长度
  private static final int SALT_BYTE_SIZE = 32 / 2;
  private static SecureRandom random;

  static {
    try {
      random = SecureRandom.getInstance("SHA1PRNG");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean authenticate(String clearText, String cipherText, String salt)
      throws Exception {
    String encryptedAttemptedPassword = encrypt(clearText, salt);
    return encryptedAttemptedPassword.equals(cipherText);
  }

  public static String encrypt(String password, String salt) {
    try {
      KeySpec keySpec = new PBEKeySpec(password.toCharArray(), Hex.decodeHex(salt),
          PBKDF2_ITERATIONS, HASH_BIT_SIZE);

      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
      return Hex.encodeHexString(secretKeyFactory.generateSecret(keySpec).getEncoded());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String randomSalt() {
    byte[] salt = new byte[SALT_BYTE_SIZE];
    random.nextBytes(salt);
    return Hex.encodeHexString(salt);
  }

  public static void main(String[] args) throws Exception {
    long start0 = System.currentTimeMillis();
    String text1 = "www.dingpengwei@foxmail.com";
    String text2 = "www.xxx.com";
    for (int i = 0; i < 1; i++) {
      String salt = randomSalt();
    }
    long end0 = System.currentTimeMillis();
    System.out.println(end0 - start0);

    long start1 = System.currentTimeMillis();
    for (int i = 0; i < 1; i++) {
      String salt = randomSalt();
      encrypt(System.currentTimeMillis() + UUID.randomUUID().toString(), salt);
    }
    long end1 = System.currentTimeMillis();
    System.out.println(end1 - start1);

    String salt = randomSalt();
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
