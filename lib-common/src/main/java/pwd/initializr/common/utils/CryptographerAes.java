package pwd.initializr.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-21 22:24
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CryptographerAes {

  public static final String AES = "AES";
  public static final int LEN = 128;

  public static void main(String[] args) {
    String slat = "slat";
    String text1 = "www.dingpengwei@foxmail.com";
    String text2 = "www.xxx.com";
    System.out.println(encrypt(text1,slat));
    System.out.println(encrypt(text2,slat));
  }

  public static String decrypt(String cipherText, String salt) {
    try {
      Cipher cipher = initCipher(Cipher.DECRYPT_MODE,salt);
      byte[] resultByte = cipher.doFinal(cipherText.getBytes("utf-8"));
      String result = new String(resultByte);
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String encrypt(String clearText, String salt) {
    try {
      Cipher cipher = initCipher(Cipher.ENCRYPT_MODE,salt);
      byte[] byteContent = clearText.getBytes("utf-8");
      byte[] resultByte = cipher.doFinal(byteContent);
      String result = new String(resultByte, "utf-8");
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static Cipher initCipher(int mode,String salt) throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
    keyGenerator.init(LEN, new SecureRandom(salt.getBytes()));
    SecretKey secretKey = keyGenerator.generateKey();
    byte[] enCodeFormat = secretKey.getEncoded();
    SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
    Cipher cipher = Cipher.getInstance(AES);
    cipher.init(mode, key);
    return cipher;
  }
}
